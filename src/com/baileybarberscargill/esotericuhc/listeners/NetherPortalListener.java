package com.baileybarberscargill.esotericuhc.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.baileybarberscargill.esotericuhc.Main.nether;

public class NetherPortalListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (nether == false && event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.OBSIDIAN && player.getInventory().getItemInMainHand().getType() == Material.FLINT_AND_STEEL || player.getInventory().getItemInOffHand().getType() == Material.FLINT_AND_STEEL){
            event.setCancelled(true);
        }
    }

}
