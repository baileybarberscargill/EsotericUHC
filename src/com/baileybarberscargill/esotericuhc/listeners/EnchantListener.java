package com.baileybarberscargill.esotericuhc.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantListener implements Listener {


    @EventHandler
    public void onPlayerEnchant(EnchantItemEvent event){

        Player player = event.getEnchanter();
        ItemStack item = event.getItem();
        if (item.getEnchantments().containsKey(Enchantment.FIRE_ASPECT) || item.getEnchantments().containsKey(Enchantment.ARROW_FIRE)){
            item.removeEnchantment(Enchantment.FIRE_ASPECT);
            item.removeEnchantment(Enchantment.ARROW_FIRE);
            player.sendMessage("flame or fire removed.");
        }

    }

}
