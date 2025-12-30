package com.kubota6646.votinggacha;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

/**
 * Votifier/NuVotifierの投票イベントを受け取るリスナー
 * Votifier または NuVotifier プラグインがインストールされている場合のみ動作します
 */
public class VoteListener implements Listener, EventExecutor {
    
    private final VotingGacha plugin;
    
    public VoteListener(VotingGacha plugin) {
        this.plugin = plugin;
    }
    
    /**
     * イベントハンドラーの登録
     * リフレクションを使用して動的にイベントを登録
     */
    public void register() {
        // Votifier のイベントクラスを登録
        try {
            Class<?> votifierEventClass = Class.forName("com.vexsoftware.votifier.model.VotifierEvent");
            plugin.getServer().getPluginManager().registerEvent(
                votifierEventClass.asSubclass(Event.class),
                this,
                EventPriority.NORMAL,
                this,
                plugin
            );
            plugin.getLogger().info("Votifierイベントリスナーを登録しました。");
        } catch (ClassNotFoundException e) {
            // Votifier が見つからない場合は無視
        }
        
        // NuVotifier のイベントクラスを登録
        try {
            Class<?> nuVotifierEventClass = Class.forName("com.vexsoftware.votifier.model.VotifierEvent");
            // NuVotifier も同じイベントクラスを使用するため、すでに登録済み
        } catch (ClassNotFoundException e) {
            // NuVotifier が見つからない場合は無視
        }
    }
    
    /**
     * イベント実行メソッド
     * EventExecutor インターフェースの実装
     */
    @Override
    public void execute(Listener listener, Event event) throws EventException {
        handleVoteEvent(event);
    }
    
    /**
     * Votifier/NuVotifierの投票イベントを処理
     */
    private void handleVoteEvent(Event event) {
        try {
            // リフレクションでVoteオブジェクトとプレイヤー名を取得
            Object vote = event.getClass().getMethod("getVote").invoke(event);
            String playerName = (String) vote.getClass().getMethod("getUsername").invoke(vote);
            
            // プレイヤーがオンラインか確認
            Player player = Bukkit.getPlayerExact(playerName);
            
            if (player == null || !player.isOnline()) {
                plugin.getLogger().info("プレイヤー " + playerName + " が投票しましたが、オフラインです。");
                return;
            }
            
            // 投票受信メッセージ
            player.sendMessage(plugin.getConfigManager().getMessage("vote-received"));
            
            // ガチャを引く
            plugin.getGachaManager().drawGacha(player);
            
            plugin.getLogger().info("プレイヤー " + playerName + " が投票し、ガチャを引きました。");
            
        } catch (Exception e) {
            plugin.getLogger().warning("投票イベントの処理中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
