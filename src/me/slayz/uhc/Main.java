package me.slayz.uhc;

import me.slayz.uhc.commands.*;
import me.slayz.uhc.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        setInstance(this);

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"UHC Core has been enabled!");

        setupCommands();
        setupEvents();

        getConfig().options().copyDefaults(true);
        saveConfig();


    }


    public void setupCommands(){
        getCommand("setlobbyworld").setExecutor(new SetSpawn());
        getCommand("setplayerworld").setExecutor(new SetUHC());
        getCommand("setdmworld").setExecutor(new SetDM());
        getCommand("startuhc").setExecutor(new StartGame());

    }

    public void setupEvents(){
        getServer().getPluginManager().registerEvents(new Join(),this);
        getServer().getPluginManager().registerEvents(new PVP(),this);
        getServer().getPluginManager().registerEvents(new CreatePortal(),this);
        getServer().getPluginManager().registerEvents(new Death(),this);
        getServer().getPluginManager().registerEvents(new HealthRegen(),this);
        getServer().getPluginManager().registerEvents(new Leave(),this);
        getServer().getPluginManager().registerEvents(new PlayerMove(),this);
    }



    public void setInstance(Main m){
        this.instance = m;
    }

    public static Main getInstance(){return instance;}
}
