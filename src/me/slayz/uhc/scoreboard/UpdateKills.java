package me.slayz.uhc.scoreboard;

import me.slayz.uhc.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class UpdateKills {

    public static void update(Player p, int kills){

        Main m = Main.getInstance();

        int i = m.getConfig().getInt("stats."+p.getUniqueId()+".kills");
        i+=kills;

        m.getConfig().set("stats."+p.getUniqueId()+".kills",i);
        m.saveConfig();

        Scoreboard board = p.getScoreboard();
        board.getTeam("kills").setPrefix(ChatColor.translateAlternateColorCodes('&',"&e&lKills: &fXXX".replace("XXX",i+"")));
    }


}
