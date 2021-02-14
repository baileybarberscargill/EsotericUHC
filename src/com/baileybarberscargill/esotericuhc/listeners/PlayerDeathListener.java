package com.baileybarberscargill.esotericuhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static com.baileybarberscargill.esotericuhc.Main.deathlightning;
import static com.baileybarberscargill.esotericuhc.Main.goldenheads;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();

        //Golden heads drop head
        if (goldenheads) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(player.getPlayer());
            head.setItemMeta(meta);

            event.getDrops().add(head);
        }

        if (deathlightning) {
            Location loc = player.getLocation();
            player.getWorld().strikeLightningEffect(loc);
        }
    }

}
