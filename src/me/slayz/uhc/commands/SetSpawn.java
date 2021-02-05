package me.slayz.uhc.commands;


import me.slayz.uhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("uhc.commands")){
                Location l = p.getLocation();

                Main m = Main.getInstance();

                m.getConfig().set("spawn.x",l.getX());
                m.getConfig().set("spawn.y",l.getY());
                m.getConfig().set("spawn.z",l.getZ());
                m.getConfig().set("spawn.world",l.getWorld().getName());
                m.saveConfig();

                p.sendMessage(ChatColor.GREEN+"Spawned location saved!");

                return true;
            }

            p.sendMessage(ChatColor.RED+"You can't perform this command!");
        }

        return true;
    }

}
