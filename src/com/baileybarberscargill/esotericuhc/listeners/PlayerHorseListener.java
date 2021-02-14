package com.baileybarberscargill.esotericuhc.listeners;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.baileybarberscargill.esotericuhc.Main.horses;

public class PlayerHorseListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (horses == false && event.getRightClicked() instanceof Horse || event.getRightClicked() instanceof Donkey || event.getRightClicked() instanceof Mule){
            event.setCancelled(true);
            player.sendMessage(Main.PREFIX + ChatColor.RED + "Horses, Donkeys and Mules are disabled!");
        }

    }
}
