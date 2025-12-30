package com.kubota6646.votinggacha;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * ガチャアイテムのデータクラス
 */
public class GachaItem {
    
    private final Material material;
    private final int amount;
    private final String name;
    private final List<String> lore;
    private final int weight;
    
    public GachaItem(Material material, int amount, String name, List<String> lore, int weight) {
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.lore = lore;
        this.weight = weight;
    }
    
    /**
     * ItemStackを作成
     */
    public ItemStack createItemStack() {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        
        if (meta != null) {
            if (name != null && !name.isEmpty()) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }
            
            if (lore != null && !lore.isEmpty()) {
                List<String> coloredLore = new ArrayList<>();
                for (String line : lore) {
                    coloredLore.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                meta.setLore(coloredLore);
            }
            
            item.setItemMeta(meta);
        }
        
        return item;
    }
    
    /**
     * 重みを取得
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * アイテム名を取得（表示用）
     */
    public String getDisplayName() {
        if (name != null && !name.isEmpty()) {
            return ChatColor.translateAlternateColorCodes('&', name);
        }
        return material.name();
    }
}
