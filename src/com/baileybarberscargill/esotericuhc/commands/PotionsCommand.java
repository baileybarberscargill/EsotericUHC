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

import static com.baileybarberscargill.esotericuhc.Main.tier2pot;
import static com.baileybarberscargill.esotericuhc.Main.splashpot;
import static com.baileybarberscargill.esotericuhc.Main.regenpot;

public class PotionsCommand implements CommandExecutor, TabCompleter {

    private boolean tier2, splash, regen;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!sender.hasPermission("uhc.potions")){
            sender.sendMessage(ChatColor.RED + "oops! Looks like you don't have permission to do that!");
            return true;
        }

        if (args.length == 0){
            sender.sendMessage(Main.PREFIX + "Usage: /potions <enable/disable> <tier2/splash/regen>");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")){
            if (args.length == 1){
                sender.sendMessage(Main.PREFIX + "Usage: /potions <enable/disable> <tier2/splash/regen>");
                return true;
            }
            if (args[1].equalsIgnoreCase("tier2")){
                if (tier2){
                    sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Tier 2 potions are already enabled.");
                    return true;
                }
                tier2pot = true;
                tier2 = true;
                Bukkit.broadcastMessage(Main.PREFIX + "Tier 2 potions are now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
            }
            if (args[1].equalsIgnoreCase("splash")){
                if (splash){
                    sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Splash potions are already enabled.");
                    return true;
                }
                splashpot = true;
                splash = true;
                Bukkit.broadcastMessage(Main.PREFIX + "Splash potions are now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
            }
            if(args[1].equalsIgnoreCase("regen")){
                if (regen) {
                    sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Regeneration potions are already enabled");
                    return true;
                }
                regenpot = true;
                regen = true;
                Bukkit.broadcastMessage(Main.PREFIX + "Regen potions are now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
            }
        }
        if (args[0].equalsIgnoreCase("disable")){
            if (args.length == 1){
                sender.sendMessage(Main.PREFIX + "Usage: /potions <enable/disable> <tier2/splash/regen>");
                return true;
            }
            if (args[1].equalsIgnoreCase("tier2")){
                if (!tier2){
                    sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Tier 2 potions are already disabled.");
                    return true;
                }
                tier2pot = false;
                tier2 = false;
                Bukkit.broadcastMessage(Main.PREFIX + "Tier 2 potions are now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
            }
            if (args[1].equalsIgnoreCase("splash")){
                if (!splash){
                    sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Splash potions are already disabled.");
                    return true;
                }
                splashpot = false;
                splash = false;
                Bukkit.broadcastMessage(Main.PREFIX + "Splash potions are now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
            }
            if(args[1].equalsIgnoreCase("regen")){
                if (regen) {
                    sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Regeneration potions are already enabled");
                    return true;
                }
                regenpot = false;
                regen = false;
                Bukkit.broadcastMessage(Main.PREFIX + "Regen potions are now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
            }
        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){

        List<String> toReturn = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        List<String> list2 = new ArrayList<>();

        if (args.length != 1){
            return toReturn;
        }

        if (sender.hasPermission("uhc.potions")){
            if (args.length == 1) {
                list.add("enable");
                list.add("disable");
            }
        }

        //Make sure to only tab complete what starts with what they typed or everything if they didn't type anything
        for (String str : list) {
            if (args[args.length - 1].isEmpty() || str.startsWith(args[args.length - 1].toLowerCase())) {
                toReturn.add(str);
            }
        }

        return toReturn;
    }

}
