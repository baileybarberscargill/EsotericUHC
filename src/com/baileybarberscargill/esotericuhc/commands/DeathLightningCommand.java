package com.baileybarberscargill.esotericuhc.commands;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import static com.baileybarberscargill.esotericuhc.Main.deathlightning;

public class DeathLightningCommand implements CommandExecutor, TabCompleter {

    private boolean enabled;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!sender.hasPermission("uhc.deathlightning")){
            sender.sendMessage(ChatColor.RED + "oops! Looks like you don't have permission to do that!");
            return true;
        }

        if (args.length == 0){
            sender.sendMessage(Main.PREFIX + "Usage: /deathlightning <enable|disable>");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")){
            if (enabled){
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Death lightning is already enabled.");
                return true;
            }

            Bukkit.broadcastMessage(Main.PREFIX + "Death lightning is now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
            enabled = true;
            deathlightning = true;
        }

        if (args[0].equalsIgnoreCase("disable")){
            if (!enabled){
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Death lightning is already disabled.");
                return true;
            }
            Bukkit.broadcastMessage(Main.PREFIX + "Death lightning is now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
            enabled = false;
            deathlightning = false;
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

        if (sender.hasPermission("uhc.deathlightning")){
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
