package me.slayz.uhc.scoreboard;

import me.slayz.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import org.bukkit.scoreboard.*;

public class UHCScoreBoard{

    public static void createScoreBoard(Player p){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective(  ChatColor.translateAlternateColorCodes('&',"&4&lTaleUHC"),"test");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);


        Score blank2 = obj.getScore("    ");
        blank2.setScore(5);

        int i = Main.getInstance().getConfig().getInt("stats."+p.getUniqueId()+".kills");
        Team coins = board.registerNewTeam("kills");
        coins.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        coins.setPrefix(ChatColor.translateAlternateColorCodes('&',"&e&lKills: &fXXX".replace("XXX",i+"")));
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(4);


        int a = Main.getInstance().getConfig().getInt("stats."+p.getUniqueId()+".points");
        Team claim = board.registerNewTeam("points");
        claim.addEntry(ChatColor.BLACK + "" + ChatColor.YELLOW);
        claim.setPrefix(ChatColor.translateAlternateColorCodes('&',"&9&lPoints: &fXXX".replace("XXX",a+"")));
        obj.getScore(ChatColor.BLACK + "" + ChatColor.YELLOW).setScore(3);

        Score blank = obj.getScore(" ");
        blank.setScore(2);


        Score onlineName = obj.getScore(ChatColor.translateAlternateColorCodes('&',"&7Talecraft.co.il"));
        onlineName.setScore(1);

        p.setScoreboard(board);
    }
}
