package com.baileybarberscargill.esotericuhc.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityTameEvent;

public class TamedWolfListener implements Listener {

    @EventHandler
    public void onWolfTame(EntityTameEvent event){
        Entity entity = event.getEntity();
        String player = event.getOwner().getName();

        if (entity instanceof Wolf){
            entity.setCustomName(player + "'s Wolf");
        }
    }

    @EventHandler
    public void onWolfBreed(EntityBreedEvent event){

        Entity parent = event.getMother();
        Entity child = event.getEntity();

        String parentName = parent.getCustomName();

        if (event instanceof Wolf){
            child.setCustomName("bb");
        }
    }

}
