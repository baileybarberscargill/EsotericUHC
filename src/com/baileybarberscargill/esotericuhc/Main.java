package com.baileybarberscargill.esotericuhc;

import com.baileybarberscargill.esotericuhc.commands.*;
import com.baileybarberscargill.esotericuhc.listeners.*;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Iterator;

public class Main extends JavaPlugin {

    public static final String PREFIX = "§8[§cUHC§8]§7 ";

    public static Main plugin;

    //Declare variables here
    //public static boolean VARIABLE; (Import using: import static com.baileybarberscargill.PLUGIN.Main.VARIABLE;)
    public static boolean goldenheads;
    public static boolean absorption;
    public static boolean nether;
    public static boolean deathlightning;
    public static boolean permanentday;
    public static boolean tabhearts;
    public static boolean healthbelow;
    public static boolean horses;
    public static boolean tier2pot;
    public static boolean splashpot;
    public static boolean regenpot;
    public static boolean epdamage;
    public static boolean episodes;

    @Override
    public void onEnable(){
        System.out.print(ChatColor.GREEN + "EsotericUHC enabled");
        plugin = this;

        //Register Commands
        getCommand("goldenheads").setExecutor(new GoldenHeadsCommand());
        getCommand("absorption").setExecutor(new AbsorptionCommand());
        getCommand("features").setExecutor(new FeaturesCommand());
        getCommand("deathlightning").setExecutor(new DeathLightningCommand());
        getCommand("nether").setExecutor(new NetherCommand());
        getCommand("permaday").setExecutor(new PermanentDayCommand());
        getCommand("tabhearts").setExecutor(new HeartsInTabCommand());
        getCommand("healthbelow").setExecutor(new HealthBelowCommand());
        getCommand("horses").setExecutor(new HorsesCommand());
        getCommand("potion").setExecutor(new PotionsCommand());
        getCommand("enderpearldamage").setExecutor(new EnderPearlDamageCommand());
        getCommand("episodes").setExecutor(new EpisodesCommand());
        getCommand("episode").setExecutor(new EpisodeCheckCommand());

        //Register Listeners
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerEatListener(), this);
        getServer().getPluginManager().registerEvents(new NetherPortalListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerHorseListener(), this);
        getServer().getPluginManager().registerEvents(new PotionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new TamedWolfListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);

        //Register Recipes
        addGoldenHeads();

        //Register default settings
        Bukkit.getServer().setSpawnRadius(1);

        //Turn off natural regeneration
        Bukkit.getServer().getWorld("UHC").setGameRule(GameRule.NATURAL_REGENERATION, false);
        Bukkit.getServer().getWorld("UHC_nether").setGameRule(GameRule.NATURAL_REGENERATION, false);
        Bukkit.getServer().getWorld("UHC_the_end").setGameRule(GameRule.NATURAL_REGENERATION,false);

    }

    @Override
    public void onDisable(){
        System.out.print(ChatColor.RED + "EsotericUHC disabled");
        Bukkit.getServer().clearRecipes();
        plugin = null;
    }


    //Custom Recipes

    public void addGoldenHeads(){

        ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Golden Head");
        meta.setLore(Arrays.asList(ChatColor.DARK_AQUA + "Consuming the blood of a", ChatColor.DARK_AQUA +"fallen player strengthens the blood."));
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey((Plugin) this, "golden_head");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("GGG","GHG","GGG");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('H', Material.PLAYER_HEAD);

        Bukkit.getServer().addRecipe(recipe);
    }

}
