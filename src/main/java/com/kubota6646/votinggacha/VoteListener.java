package com.kubota6646.votinggacha;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Votifierの投票イベントを受け取るリスナー
 * Votifierプラグインがインストールされている場合のみ動作します
 */
public class VoteListener implements Listener {
    
    private final VotingGacha plugin;
    
    public VoteListener(VotingGacha plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Votifierの投票イベントを処理
     * リフレクションを使用してVotifierが存在する場合のみ動作
     */
    @EventHandler
    public void onVote(org.bukkit.event.Event event) {
        // Votifierのイベントかどうかをチェック
        if (!event.getClass().getName().contains("VotifierEvent")) {
            return;
        }
        
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
        }
    }
}
