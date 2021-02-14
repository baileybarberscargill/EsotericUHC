package com.baileybarberscargill.esotericuhc.commands;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.baileybarberscargill.esotericuhc.commands.EpisodesCommand.*;

public class EpisodeCheckCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (episodesstarted) {
            int minutesleft = episodeinterval - episodeminutes -1;
            int secondsleft = 60 - episodeseconds;
            sender.sendMessage(Main.PREFIX + ChatColor.AQUA + "Episode " + episodenumber + " (" + minutesleft + "m, " + secondsleft + "s left)");
            return true;
        }
        sender.sendMessage(Main.PREFIX + ChatColor.RED + "No episode timer is running.");
        return true;
    }
}
