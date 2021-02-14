package com.baileybarberscargill.esotericuhc.commands;

import com.baileybarberscargill.esotericuhc.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.baileybarberscargill.esotericuhc.Main.*;
import static com.baileybarberscargill.esotericuhc.commands.PermanentDayCommand.minutes;
import static com.baileybarberscargill.esotericuhc.commands.EpisodesCommand.episodeinterval;

public class FeaturesCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        sender.sendMessage(Main.PREFIX + "§cList of features:");
        sender.sendMessage(absorption ? "§a§l| §fAbsorption is on" : "§c| §7Absorption is off");
        sender.sendMessage(deathlightning ? "§a§l| §fDeath lightning is on" : "§c| §7Death lightning is off");
        sender.sendMessage(epdamage ? "§a§l| §fEnderpearl damage is on" : "§c| §7Enderpearl damage is off");
        sender.sendMessage(episodes ? "§a§l| §fEpisodes are on (§7" + episodeinterval + "m§f)" : "§c| §7Episodes are off");
        sender.sendMessage(goldenheads ? "§a§l| §fGolden heads are on" : "§c| §7Golden heads are off");
        sender.sendMessage(healthbelow ? "§a§l| §fHealth below players is on" : "§c| §7Health below players is off");
        sender.sendMessage(horses ? "§a§l| §fHorses are on" : "§c| §7Horses are off");
        sender.sendMessage(nether ? "§a§l| §fThe Nether is on" : "§c| §7The Nether is off");
        sender.sendMessage(permanentday ? "§a§l| §fPermanent day is on (§7" + minutes + "m§f)" : "§c| §7Permanent day is off");
        sender.sendMessage(regenpot ? "§a§l| §fRegeneration potions are on" : "§c| §7Regeneration potions are off");
        sender.sendMessage(splashpot ? "§a§l| §fSplash potions are on" : "§c| §7Splash potions are off");
        sender.sendMessage(tier2pot ? "§a§l| §fTier 2 potions are on" : "§c| §7Tier 2 potions are off");
        sender.sendMessage(tabhearts ? "§a§l| §fTab hearts are on" : "§c| §7Tab hearts are off");
        return true;

    }

}
