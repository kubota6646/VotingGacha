package com.kubota6646.votinggacha;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * 設定ファイルを管理するクラス
 */
public class ConfigManager {
    
    private final VotingGacha plugin;
    private FileConfiguration messagesConfig;
    private File messagesFile;
    
    public ConfigManager(VotingGacha plugin) {
        this.plugin = plugin;
        loadMessagesConfig();
    }
    
    /**
     * messages.ymlを読み込む
     */
    private void loadMessagesConfig() {
        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        
        // ファイルが存在しない場合は作成
        if (!messagesFile.exists()) {
            try {
                // データフォルダが存在しない場合は作成
                if (!plugin.getDataFolder().exists()) {
                    plugin.getDataFolder().mkdirs();
                }
                
                // リソースからコピー
                InputStream inputStream = plugin.getResource("messages.yml");
                if (inputStream != null) {
                    Files.copy(inputStream, messagesFile.toPath());
                    inputStream.close();
                }
            } catch (IOException e) {
                plugin.getLogger().warning("messages.ymlの作成に失敗しました: " + e.getMessage());
            }
        }
        
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
    
    /**
     * メッセージを取得
     */
    public String getMessage(String key) {
        // 旧形式（messages.キー名）から新形式への変換
        String[] parts = key.split("-", 2);
        String newKey;
        
        if (key.equals("vote-received")) {
            newKey = "vote.received";
        } else if (key.equals("gacha-result")) {
            newKey = "vote.result";
        } else if (key.equals("no-space")) {
            newKey = "vote.no-space";
        } else if (key.equals("reload-success")) {
            newKey = "command.reload-success";
        } else if (key.equals("no-permission")) {
            newKey = "command.no-permission";
        } else if (key.equals("help-header")) {
            newKey = "help.header";
        } else if (key.equals("help-gacha")) {
            newKey = "help.gacha";
        } else if (key.equals("help-reload")) {
            newKey = "help.reload";
        } else if (key.equals("help-help")) {
            newKey = "help.help";
        } else {
            newKey = key;
        }
        
        String message = messagesConfig.getString(newKey, "");
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    /**
     * メッセージを取得（置換付き）
     */
    public String getMessage(String key, String placeholder, String value) {
        String message = getMessage(key);
        return message.replace(placeholder, value);
    }
    
    /**
     * 設定を再読み込み
     */
    public void reload() {
        plugin.reloadConfig();
        loadMessagesConfig();
    }
}
