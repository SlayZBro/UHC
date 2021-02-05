package me.slayz.uhc.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void move(PlayerMoveEvent e){
        Location l = e.getPlayer().getLocation();

    }
}
