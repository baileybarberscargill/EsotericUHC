package com.baileybarberscargill.esotericuhc.commands;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

import static com.baileybarberscargill.esotericuhc.Main.plugin;
import static com.baileybarberscargill.esotericuhc.Main.tabhearts;

public class HeartsInTabCommand implements CommandExecutor, TabCompleter {

    private boolean enabled;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!sender.hasPermission("uhc.tabhearts")){
            sender.sendMessage(ChatColor.RED + "oops! Looks like you don't have permission to do that!");
            return true;
        }

        if (args.length == 0){
            sender.sendMessage(Main.PREFIX + "Usage: /tabhearts <enable/disable>");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")){

            if (enabled){
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Tab hearts are already enabled.");
                return true;
            }

            /*Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("TabHearts", "health", "Tab Hearts", RenderType.HEARTS);
            obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            for (Player online : Bukkit.getOnlinePlayers()){
                online.setScoreboard(board);
            }*/

            //Running via console because Hearts don't render when rejoining.
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "scoreboard objectives add TabHealth health";
            Bukkit.dispatchCommand(console, command);

            command = "scoreboard objectives setdisplay list TabHealth";
            Bukkit.dispatchCommand(console, command);

            Bukkit.broadcastMessage(Main.PREFIX + "Hearts in tab are now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
            enabled = true;
            tabhearts = true;
        }

        if (args[0].equalsIgnoreCase("disable")){

            if (!enabled){
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Tab hearts are already disabled.");
                return true;
            }

            /*for (Player online : Bukkit.getOnlinePlayers()){
                ScoreboardManager mng = Bukkit.getScoreboardManager();
                online.setScoreboard(mng.getNewScoreboard());
            }*/

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "scoreboard objectives remove TabHealth";
            Bukkit.dispatchCommand(console, command);

            Bukkit.broadcastMessage(Main.PREFIX + "Hearts in tab are now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
            enabled = false;
            tabhearts = false;
        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){

        List<String> toReturn = new ArrayList<String>();
        List<String> list = new ArrayList<String>();

        if (args.length != 1){
            return toReturn;
        }

        if (sender.hasPermission("uhc.tabhearts")){
            list.add("enable");
            list.add("disable");
        }

        //Make sure to only tab complete what starts with what they typed or everything if they didn't type anything
        for (String str : list){
            if (args[args.length - 1].isEmpty() || str.startsWith(args[args.length -1].toLowerCase())) {
                toReturn.add(str);
            }
        }

        return toReturn;
    }

}
