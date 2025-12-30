package com.kubota6646.votinggacha;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ガチャシステムを管理するクラス
 */
public class GachaManager {
    
    private final VotingGacha plugin;
    private final List<GachaItem> gachaItems;
    private final Random random;
    private int totalWeight;
    
    public GachaManager(VotingGacha plugin) {
        this.plugin = plugin;
        this.gachaItems = new ArrayList<>();
        this.random = new Random();
        loadGachaItems();
    }
    
    /**
     * 設定ファイルからガチャアイテムを読み込む
     */
    private void loadGachaItems() {
        gachaItems.clear();
        totalWeight = 0;
        
        List<?> itemConfigs = plugin.getConfig().getList("gacha-items");
        if (itemConfigs == null || itemConfigs.isEmpty()) {
            plugin.getLogger().warning("ガチャアイテムの設定がありません！");
            return;
        }
        
        for (Object obj : itemConfigs) {
            if (!(obj instanceof ConfigurationSection)) {
                continue;
            }
            
            ConfigurationSection section = (ConfigurationSection) obj;
            
            try {
                String materialName = section.getString("material");
                Material material = Material.valueOf(materialName);
                int amount = section.getInt("amount", 1);
                String name = section.getString("name", "");
                List<String> lore = section.getStringList("lore");
                int weight = section.getInt("weight", 10);
                
                GachaItem gachaItem = new GachaItem(material, amount, name, lore, weight);
                gachaItems.add(gachaItem);
                totalWeight += weight;
                
                plugin.getLogger().info("ガチャアイテムを読み込み: " + materialName + " (重み: " + weight + ")");
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("無効なアイテム設定: " + e.getMessage());
            }
        }
        
        plugin.getLogger().info("合計 " + gachaItems.size() + " 個のガチャアイテムを読み込みました。");
    }
    
    /**
     * ガチャを引く
     */
    public void drawGacha(Player player) {
        if (gachaItems.isEmpty()) {
            player.sendMessage(plugin.getConfigManager().getMessage("no-permission"));
            return;
        }
        
        // インベントリに空きがあるか確認
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(plugin.getConfigManager().getMessage("no-space"));
            return;
        }
        
        // 重み付きランダムでアイテムを選択
        GachaItem selectedItem = selectRandomItem();
        
        if (selectedItem == null) {
            player.sendMessage("§cエラーが発生しました。");
            return;
        }
        
        // アイテムを付与
        ItemStack item = selectedItem.createItemStack();
        player.getInventory().addItem(item);
        
        // メッセージを送信
        String message = plugin.getConfigManager().getMessage(
            "gacha-result",
            "{item}",
            selectedItem.getDisplayName() + " x" + item.getAmount()
        );
        player.sendMessage(message);
    }
    
    /**
     * 重み付きランダムでアイテムを選択
     */
    private GachaItem selectRandomItem() {
        if (gachaItems.isEmpty() || totalWeight <= 0) {
            return null;
        }
        
        int randomWeight = random.nextInt(totalWeight);
        int currentWeight = 0;
        
        for (GachaItem item : gachaItems) {
            currentWeight += item.getWeight();
            if (randomWeight < currentWeight) {
                return item;
            }
        }
        
        // フォールバック（通常は到達しない）
        return gachaItems.get(0);
    }
    
    /**
     * 設定を再読み込み
     */
    public void reload() {
        loadGachaItems();
    }
}
