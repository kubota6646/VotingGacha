package com.kubota6646.votinggacha;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * /votinggacha コマンドの処理
 */
public class VotingGachaCommand implements CommandExecutor, TabCompleter {
    
    private final VotingGacha plugin;
    
    public VotingGachaCommand(VotingGacha plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        
        // 引数なし、またはhelpの場合
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sendHelp(sender);
            return true;
        }
        
        // reloadコマンド
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("votinggacha.admin")) {
                sender.sendMessage(plugin.getConfigManager().getMessage("no-permission"));
                return true;
            }
            
            plugin.reloadConfiguration();
            sender.sendMessage(plugin.getConfigManager().getMessage("reload-success"));
            return true;
        }
        
        // gachaコマンド（テスト用）
        if (args[0].equalsIgnoreCase("gacha")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cこのコマンドはプレイヤーのみ実行できます。");
                return true;
            }
            
            Player player = (Player) sender;
            
            if (!player.hasPermission("votinggacha.gacha")) {
                player.sendMessage(plugin.getConfigManager().getMessage("no-permission"));
                return true;
            }
            
            plugin.getGachaManager().drawGacha(player);
            return true;
        }
        
        // 不明なコマンド
        sendHelp(sender);
        return true;
    }
    
    /**
     * ヘルプメッセージを送信
     */
    private void sendHelp(CommandSender sender) {
        sender.sendMessage(plugin.getConfigManager().getMessage("help-header"));
        sender.sendMessage(plugin.getConfigManager().getMessage("help-gacha"));
        sender.sendMessage(plugin.getConfigManager().getMessage("help-reload"));
        sender.sendMessage(plugin.getConfigManager().getMessage("help-help"));
    }
    
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            List<String> subcommands = Arrays.asList("gacha", "reload", "help");
            
            for (String subcommand : subcommands) {
                if (subcommand.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(subcommand);
                }
            }
        }
        
        return completions;
    }
}
