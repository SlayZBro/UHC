package me.slayz.uhc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class CreatePortal implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityCreatePortal(PortalCreateEvent e) {
        e.setCancelled(true);
    }
}
