package com.kubota6646.votinggacha;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * VotingGacha メインクラス
 * 投票するとランダムでアイテムが貰えるプラグイン
 */
public class VotingGacha extends JavaPlugin {
    
    private GachaManager gachaManager;
    private ConfigManager configManager;
    
    @Override
    public void onEnable() {
        // 設定ファイルの読み込み
        saveDefaultConfig();
        
        // マネージャーの初期化
        configManager = new ConfigManager(this);
        gachaManager = new GachaManager(this);
        
        // コマンドの登録
        VotingGachaCommand command = new VotingGachaCommand(this);
        org.bukkit.command.PluginCommand pluginCommand = getCommand("votinggacha");
        if (pluginCommand != null) {
            pluginCommand.setExecutor(command);
            pluginCommand.setTabCompleter(command);
        } else {
            getLogger().severe("コマンド 'votinggacha' の登録に失敗しました。plugin.ymlを確認してください。");
        }
        
        // Votifierのリスナー登録
        if (Bukkit.getPluginManager().getPlugin("Votifier") != null) {
            getServer().getPluginManager().registerEvents(new VoteListener(this), this);
            getLogger().info("Votifierが検出されました。投票リスナーを登録しました。");
        } else {
            getLogger().warning("Votifierが見つかりません。投票機能は動作しません。");
        }
        
        getLogger().info("VotingGacha v" + getDescription().getVersion() + " が有効になりました。");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("VotingGacha が無効になりました。");
    }
    
    /**
     * ガチャマネージャーを取得
     */
    public GachaManager getGachaManager() {
        return gachaManager;
    }
    
    /**
     * 設定マネージャーを取得
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    /**
     * 設定をリロード
     */
    public void reloadConfiguration() {
        reloadConfig();
        configManager.reload();
        gachaManager.reload();
    }
}
