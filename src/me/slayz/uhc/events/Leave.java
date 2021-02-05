package me.slayz.uhc.events;

import me.slayz.uhc.Main;
import me.slayz.uhc.commands.StartGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

    @EventHandler
    public void leave(PlayerQuitEvent e){
        e.setQuitMessage(null);
        Main m = Main.getInstance();
        int deaths = m.getConfig().getInt("stats."+e.getPlayer().getUniqueId()+".deaths");
        deaths++;


        m.getConfig().set("stats."+e.getPlayer().getUniqueId()+".deaths",deaths);
        m.saveConfig();

        StartGame.players.remove(e.getPlayer());
        Death.checkPlayer(e.getPlayer());

    }
}
