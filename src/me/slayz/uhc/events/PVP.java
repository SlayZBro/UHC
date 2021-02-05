package me.slayz.uhc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVP implements Listener {

    public static boolean pvp = false;


    @EventHandler
    public void pvp(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){

            Player damager = (Player) e.getDamager();
            Player damaged = (Player) e.getEntity();

            if(!pvp)
                e.setCancelled(true);
        }
    }
}
