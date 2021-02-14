package com.baileybarberscargill.esotericuhc.listeners;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.baileybarberscargill.esotericuhc.Main.absorption;

public class PlayerEatListener implements Listener {
    @EventHandler
    public void onPlayerConsumeItem(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();

        //Remove absorption from the player if it is disabled
        if (absorption == false && event.getItem().getType() == Material.GOLDEN_APPLE){

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                public void run() {
                    player.removePotionEffect(PotionEffectType.ABSORPTION);
                }
            }, 1L);
        }

        //Heal the player 4 hearts if they eat a golden head
        if (event.getItem().getType() == Material.GOLDEN_APPLE && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Golden Head")){
            player.getActivePotionEffects().clear();
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
        }

    }

}
