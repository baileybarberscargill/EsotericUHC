package com.baileybarberscargill.esotericuhc.listeners;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static com.baileybarberscargill.esotericuhc.Main.*;

public class PotionListener implements Listener {

    @EventHandler
    public void menuClick(InventoryClickEvent event){
        Material item = event.getCurrentItem().getType();
        String inv = event.getView().getTitle();
        Player player = (Player) event.getWhoClicked();

        if (item.equals(Material.GLOWSTONE_DUST) && inv.equalsIgnoreCase("Brewing Stand") && !tier2pot){
            player.sendMessage(Main.PREFIX + ChatColor.RED + "Tier 2 potions are disabled.");
            event.setCancelled(true);
            }
        if (item.equals(Material.GUNPOWDER) && inv.equalsIgnoreCase("Brewing Stand") && !splashpot){
            player.sendMessage(Main.PREFIX + ChatColor.RED + "Splash potions are disabled.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void mobDeath(EntityDeathEvent event){
        Entity mob = event.getEntity();

        if (mob instanceof Ghast) {
            if (!regenpot) {
                event.getDrops().remove(new ItemStack(Material.GHAST_TEAR));
                event.getDrops().add(new ItemStack(Material.GOLD_INGOT, 1));
            }
        }

        }
    }