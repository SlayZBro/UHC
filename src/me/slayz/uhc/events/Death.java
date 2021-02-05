package me.slayz.uhc.events;

import me.slayz.uhc.Main;
import me.slayz.uhc.chat.EndMessage;
import me.slayz.uhc.commands.StartGame;
import me.slayz.uhc.scoreboard.UpdateKills;
import me.slayz.uhc.scoreboard.UpdatePoints;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Death implements Listener {

    public static HashMap<Player,HashMap<String,Integer>> list = new HashMap<>();
    public static ArrayList<Player> deathList = new ArrayList<>();


    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Main m = Main.getInstance();

        if(e.getEntity() instanceof Player){

            int deaths = m.getConfig().getInt("stats."+e.getEntity().getUniqueId()+".deaths");
            deaths++;


            m.getConfig().set("stats."+e.getEntity().getUniqueId()+".deaths",deaths);
            m.saveConfig();

            if(e.getEntity().getKiller() instanceof Player){
                UpdateKills.update(e.getEntity().getKiller(),1);
                UpdatePoints.update(e.getEntity().getKiller(),2);

                updateStats(e.getEntity().getKiller(),1,2);


            }
            updateStats(e.getEntity(),0,0);


            StartGame.players.remove(e.getEntity());
            deathList.add(e.getEntity());

            checkPlayer(e.getEntity());
            send("survival",e.getEntity());




        }

    }

    public static void checkPlayer(Player p){
        if(StartGame.players.size() == 2) {
            updateStats(p,0,5);
            UpdatePoints.update(p, 5);
        }
        else if(StartGame.players.size() == 1) {
            updateStats(p,0,7);
            updateStats(StartGame.players.get(0),0,10);

            UpdatePoints.update(p, 7);
            UpdatePoints.update(StartGame.players.get(0),10);

            deathList.add(StartGame.players.get(0));
            for(Player p1 : Bukkit.getOnlinePlayers())
                EndMessage.sendMessage(p1);

            StartGame.players.clear();
            deathList.clear();
            list.clear();
            StartGame.start=false;
            StartGame.dm=false;
        }
    }


    public void send(String server,Player p){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        p.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
    }


    public static void updateStats(Player p,int kills, int points){
        HashMap<String,Integer> stats = new HashMap<>();

        if(Death.list.containsKey(p)){
            stats = list.get(p);
            stats.put("kills",stats.get("kills")+kills);
            stats.put("points",stats.get("points")+points);

        }else{
            stats.put("kills",kills);
            stats.put("points",points);
        }
        list.put(p,stats);
    }
}
