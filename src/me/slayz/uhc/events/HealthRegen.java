package me.slayz.uhc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class HealthRegen implements Listener {

    @EventHandler
    public void regen(EntityRegainHealthEvent e){
        if(e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)
            e.setCancelled(true);
    }
}
