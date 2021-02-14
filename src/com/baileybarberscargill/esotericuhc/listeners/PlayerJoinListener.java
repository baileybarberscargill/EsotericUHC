package com.baileybarberscargill.esotericuhc.listeners;

import com.baileybarberscargill.esotericuhc.commands.HeartsInTabCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

import static com.baileybarberscargill.esotericuhc.Main.tabhearts;
import static com.baileybarberscargill.esotericuhc.Main.healthbelow;

public class PlayerJoinListener extends HeartsInTabCommand implements Listener {



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        new BukkitRunnable(){
            public void run(){

                for (Player online : Bukkit.getOnlinePlayers()){
                    //Using commands in HeartsInTabCommand because hearts dont render on rejoin
                    //updateScoreboard(online);
                }
            }

        }.runTaskLater(Main.plugin, 10);

    }

    public void updateScoreboard(Player player){

        if (tabhearts){
            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("TabHearts", "health", "Tab Hearts", RenderType.HEARTS);
            obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            for (Player online : Bukkit.getOnlinePlayers()){
                online.setScoreboard(board);
            }
        }

        if (healthbelow){
            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("HealthBelow", "health", "§c♥", RenderType.INTEGER);
            obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
            for (Player online : Bukkit.getOnlinePlayers()){
                online.setScoreboard(board);
            }
        }

    }
}
