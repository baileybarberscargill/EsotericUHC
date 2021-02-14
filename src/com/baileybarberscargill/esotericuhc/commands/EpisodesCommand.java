package com.baileybarberscargill.esotericuhc.commands;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.baileybarberscargill.esotericuhc.Main.episodes;

public class EpisodesCommand implements CommandExecutor, TabCompleter {

    private boolean enabled;
    public static int episodeinterval = 20;
    public static int episodeseconds = 0;
    public static int episodeminutes = 0;
    public static int episodenumber = 1;
    public static int timeelapsed = 0;
    public static boolean episodesstarted;
    private int episodeJobID = -1;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("uhc.episodes")) {
            sender.sendMessage(ChatColor.RED + "oops! Looks like you don't have permission to do that!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Main.PREFIX + "Usage: /episodes <enable/disable/start/interval> [time in minutes]");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            if (enabled) {
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Episodes are already enabled (" + episodeinterval + "m)");
                return true;
            }
            enabled = true;
            episodes = true;
            Bukkit.broadcastMessage(Main.PREFIX + "Episodes are now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + " (" + episodeinterval + "m)");
            sender.sendMessage(Main.PREFIX + ChatColor.ITALIC + "Use /episodes interval <time in minutes> to change the interval");
            return true;
        }

        if (args[0].equalsIgnoreCase("disable")) {
            if (!enabled) {
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Episodes are already disabled.");
                return true;
            }
            enabled = false;
            episodeinterval = 20;
            Bukkit.broadcastMessage(Main.PREFIX + "Episodes are now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
            if (episodesstarted) {
                stopEpisodes();
                episodesstarted = false;
                episodes = false;
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("interval")) {
            if (args.length == 1) {
                sender.sendMessage(Main.PREFIX + "Current episode interval: " + episodeinterval + "m");
                return true;
            }
            int oldinterval = episodeinterval;
            episodeinterval = Integer.parseInt(args[1]);
            if (episodeinterval == oldinterval) {
                sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Identical interval." + ChatColor.GRAY + " Episode interval not changed (" + episodeinterval + "m)");
                return true;
            }
            Bukkit.broadcastMessage(Main.PREFIX + "Episodes interval " + ChatColor.GOLD + "changed" + ChatColor.GRAY + " (" + oldinterval + "m -> " + ChatColor.GOLD + episodeinterval + "m" + ChatColor.GRAY + ")");
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {
            if (!enabled){
                sender.sendMessage(Main.PREFIX + ChatColor.RED + "Episodes are disabled.");
                return true;
            }
            episodesstarted = true;
            episodeseconds = 0;
            episodeminutes = 0;
            Bukkit.broadcastMessage(Main.PREFIX + ChatColor.AQUA + "Start of episode " + episodenumber + " (" +episodeminutes + "m elapsed)");
            episodeJobID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    episodeseconds++;
                    if (Bukkit.getOnlinePlayers().isEmpty()){
                        Bukkit.broadcastMessage(Main.PREFIX + ChatColor.RED + "No players online. Disabling episodes.");
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String command = "episodes disable";
                        Bukkit.dispatchCommand(console, command);
                    }
                    if (episodeseconds == 60) {
                        episodeseconds = 0;
                        episodeminutes++;
                        timeelapsed++;
                    }
                    if (episodeminutes == episodeinterval) {
                        Bukkit.broadcastMessage(Main.PREFIX + ChatColor.AQUA + "End of Episode " + episodenumber + " (" + timeelapsed + "m elapsed)");
                        episodenumber++;
                        episodeminutes = 0;
                        for (Player online : Bukkit.getOnlinePlayers()){
                            online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1F);
                        }
                    }
                }
            }, 20L, 20L);
        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> toReturn = new ArrayList<String>();
        List<String> list = new ArrayList<String>();

        if (args.length != 1) {
            return toReturn;
        }

        if (sender.hasPermission("uhc.tabhearts")) {
            list.add("enable");
            list.add("disable");
            list.add("start");
            list.add("interval");
        }

        //Make sure to only tab complete what starts with what they typed or everything if they didn't type anything
        for (String str : list) {
            if (args[args.length - 1].isEmpty() || str.startsWith(args[args.length - 1].toLowerCase())) {
                toReturn.add(str);
            }
        }

        return toReturn;
    }

    public void stopEpisodes() {
        if (episodeJobID != -1) {
            Bukkit.getScheduler().cancelTask(episodeJobID);
            episodeJobID = -1;
            Bukkit.broadcastMessage(Main.PREFIX + ChatColor.RED + "Stopped episode timer.");
        }
    }
}
