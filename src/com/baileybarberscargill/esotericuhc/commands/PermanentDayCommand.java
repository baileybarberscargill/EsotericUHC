package com.baileybarberscargill.esotericuhc.commands;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

import static com.baileybarberscargill.esotericuhc.Main.permanentday;

public class PermanentDayCommand implements CommandExecutor, TabCompleter {

    private boolean enabled;
    public static int minutes = 0;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!sender.hasPermission("uhc.permaday")){
            sender.sendMessage(ChatColor.RED + "oops! Looks like you don't have permission to do that!");
            return true;
        }

        if (args.length == 0){
            sender.sendMessage(Main.PREFIX + "Usage: /permaday <enable/disable/start> <time in minutes>");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")){

            if (enabled){
                if (args.length == 1) {
                    sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Permanent day is already enabled (" + minutes + "m)");
                    sender.sendMessage(Main.PREFIX + ChatColor.ITALIC + "To enable permaday instantly, do /permaday enable 0");
                    return true;
                }
                int oldminutes = minutes;
                minutes = Integer.parseInt(args[1]);
                if (minutes > 0) {
                    Bukkit.broadcastMessage(Main.PREFIX + "Permanent day activation time " + ChatColor.GOLD + "changed" + ChatColor.GRAY + " (" + oldminutes + "m -> " + ChatColor.GOLD + minutes + "m" + ChatColor.GRAY + ")");
                    sender.sendMessage(Main.PREFIX + ChatColor.ITALIC + "To enable permaday instantly, do /permaday enable 0");
                    return true;
                }
                if (minutes == 0){
                    Bukkit.broadcastMessage(Main.PREFIX + "Permanent day is now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + " now.");
                    Bukkit.getServer().getWorld("UHC").setTime(6000);
                    Bukkit.getServer().getWorld("UHC").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    return true;
                }
                return true;
            }

            if (args.length == 1){
                Bukkit.broadcastMessage(Main.PREFIX + ChatColor.RED + "No time provided. " + ChatColor.GRAY + "Permanent day " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + " now.");
                enabled = true;
                permanentday = true;
                minutes = 0;
                Bukkit.getServer().getWorld("UHC").setTime(6000);
                Bukkit.getServer().getWorld("UHC").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                return true;
            }
            try {
                minutes = Integer.parseInt(args[1]);
                Bukkit.broadcastMessage(Main.PREFIX + "Permanent day is now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + " (" + minutes + ChatColor.GRAY + "m)");
                sender.sendMessage(Main.PREFIX + ChatColor.ITALIC + "To enable permaday instantly, do /permaday enable 0");
                enabled = true;
                permanentday = true;
            }
            catch (NumberFormatException e){
                sender.sendMessage(Main.PREFIX + ChatColor.RED + "Time must be an integer.");
            }
        }

        if (args[0].equalsIgnoreCase("disable")){
            Bukkit.broadcastMessage(Main.PREFIX + "Permanenet day is now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
            Bukkit.broadcastMessage(Main.PREFIX + "Normal day cycle resumed.");
            enabled = false;
            permanentday = false;
            minutes = 0;
            Bukkit.getServer().getWorld("UHC").setTime(0);
            Bukkit.getServer().getWorld("UHC").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        }

        if (args[0].equalsIgnoreCase("start")){
            if (!enabled){
                if (sender instanceof Player){
                    sender.sendMessage(Main.PREFIX + ChatColor.RED + "Permanent day is disabled.");
                    return true;
                }
                else{
                    sender.sendMessage(ChatColor.RED + "Permanent day is disabled.");
                    return true;
                }
            }

            if (minutes == 0){
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Permanent day is already active.");
                return true;
            }
            Bukkit.broadcastMessage(Main.PREFIX + "Permaday will be enabled in " + minutes + " minutes.");
            int ticks = minutes * 1200;
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                public void run() {
                    Bukkit.getServer().getWorld("UHC").setTime(6000);
                    Bukkit.getServer().getWorld("UHC").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    Bukkit.broadcastMessage(Main.PREFIX + ChatColor.GREEN + "Permanent day is now enabled!");
                }
            }, ticks);
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
            list.add("start");
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
