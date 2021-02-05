package me.slayz.uhc.commands;

import me.slayz.uhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetUHC implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("uhc.commands")){
                Location l = p.getLocation();

                Main m = Main.getInstance();

                m.getConfig().set("uhc.x",l.getX());
                m.getConfig().set("uhc.y",l.getY());
                m.getConfig().set("uhc.z",l.getZ());
                m.getConfig().set("uhc.world",l.getWorld().getName());
                m.saveConfig();

                p.sendMessage(ChatColor.GREEN+"UHC location saved!");

                return true;
            }

            p.sendMessage(ChatColor.RED+"You can't perform this command!");
        }

        return true;
    }
}
