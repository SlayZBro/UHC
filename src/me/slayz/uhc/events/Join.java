package me.slayz.uhc.events;

import me.slayz.uhc.Main;
import me.slayz.uhc.commands.StartGame;
import me.slayz.uhc.scoreboard.UHCScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void preJoin(AsyncPlayerPreLoginEvent e){
        Player p = Bukkit.getPlayer(e.getUniqueId());
        if(  StartGame.start && !p.isOp())
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_FULL);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);
        Main m  = Main.getInstance();
        m.getConfig().addDefault("stats."+e.getPlayer().getUniqueId()+".kills",0);
        m.getConfig().addDefault("stats."+e.getPlayer().getUniqueId()+".wins",0);
        m.getConfig().addDefault("stats."+e.getPlayer().getUniqueId()+".deaths",0);
        m.getConfig().addDefault("stats."+e.getPlayer().getUniqueId()+".points",0);
        m.getConfig().set("stats."+e.getPlayer().getUniqueId()+".name",e.getPlayer().getName());

        m.saveConfig();
        teleportPlayer(e.getPlayer(),m);

        UHCScoreBoard.createScoreBoard(e.getPlayer());
    }

    public static void teleportPlayer(Player p, Main m){


        p.teleport(getLocation(m));
    }

    public static Location getLocation(Main m){
        double x = m.getConfig().getDouble("spawn.x");
        double y = m.getConfig().getDouble("spawn.y");
        double z = m.getConfig().getDouble("spawn.z");
        String world = m.getConfig().getString("spawn.world");

        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
