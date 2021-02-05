package me.slayz.uhc.scoreboard;

import me.slayz.uhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;


public class UpdatePoints {

    public static void update(Player p, int point){

        Main m = Main.getInstance();

        int i = m.getConfig().getInt("stats."+p.getUniqueId()+".points");
        i+=point;

        m.getConfig().set("stats."+p.getUniqueId()+".points",i);
        m.saveConfig();
        Scoreboard board = p.getScoreboard();
        board.getTeam("points").setPrefix(ChatColor.translateAlternateColorCodes('&',"&9&lPoints: &fXXX".replace("XXX",i+"")));




    }
}
