package com.kubota6646.votinggacha;

import org.bukkit.ChatColor;

/**
 * 設定ファイルを管理するクラス
 */
public class ConfigManager {
    
    private final VotingGacha plugin;
    
    public ConfigManager(VotingGacha plugin) {
        this.plugin = plugin;
    }
    
    /**
     * メッセージを取得
     */
    public String getMessage(String key) {
        String message = plugin.getConfig().getString("messages." + key, "");
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
    }
}
