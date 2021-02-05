package me.slayz.uhc.commands;

import me.slayz.uhc.Main;
import me.slayz.uhc.events.PVP;
import me.slayz.uhc.randomtp.TeleportUtils;
import me.slayz.uhc.scoreboard.UHCScoreBoard;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class StartGame implements CommandExecutor {

    public static boolean start = false;
    public static ArrayList<Player> players = new ArrayList<>();
    public static boolean dm = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("uhc.commands") && !start){

                for(Player p1 : Bukkit.getOnlinePlayers())
                    if(!p1.isOp())
                        players.add(p1);

                start = true;

                startUHC();
                message();
                border();
                enablePvp();
                dm();

                return true;
            }

            p.sendMessage(ChatColor.RED+"You can't perform this command!");
        }

        return true;
    }

    public void startUHC(){
        Bukkit.broadcastMessage(ChatColor.GREEN+"Game will start in 30 seconds!");
        new BukkitRunnable(){
            int i = 30;
            @Override
            public void run() {

                if(i<=10 && i>0)
                    Bukkit.broadcastMessage(ChatColor.GREEN+"Game will start in XX seconds!".replace("XX",i+""));

                else if(i==0){
                    for(Player p : players){

                        WorldBorder wb = getUHCLocation(Main.getInstance()).getWorld().getWorldBorder();
                        wb.setCenter(getUHCLocation(Main.getInstance()).getX(), getUHCLocation(Main.getInstance()).getZ());
                        wb.setSize(1000);

                        p.teleport(TeleportUtils.findSafeLocation(p));
                        UHCScoreBoard.createScoreBoard(p);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,Integer.MAX_VALUE,1));

                        p.getInventory().addItem(new ItemStack(Material.STONE_SWORD),new ItemStack(Material.STONE_PICKAXE),new ItemStack(Material.STONE_AXE),new ItemStack(Material.STONE_SPADE),new ItemStack(Material.COOKED_BEEF,10));

                        this.cancel();
                    }
                }

                i--;


            }


        }.runTaskTimer(Main.getInstance(),0,20);
    }


    public void enablePvp(){
        new BukkitRunnable(){
            int i=0;
            @Override
            public void run() {
                if(i==5)
                    Bukkit.broadcastMessage(ChatColor.GREEN+"PVP WILL START IN 10 MINUTES!");
                else if(i==10)
                    Bukkit.broadcastMessage(ChatColor.GREEN+"PVP WILL START IN 5 MINUTES!");
                else if(i==14)
                    Bukkit.broadcastMessage(ChatColor.GREEN+"PVP WILL START IN 1 MUNUTE!");
                else if(i==15){
                    Bukkit.broadcastMessage(ChatColor.GREEN+"PVP HAS STARTED!");
                    PVP.pvp=true;
                    for(Player p : Bukkit.getOnlinePlayers())
                        p.removePotionEffect(PotionEffectType.NIGHT_VISION);

                    this.cancel();
                }
                i++;
            }
        }.runTaskTimer(Main.getInstance(),0,20*60);
    }

    public void message(){
        new BukkitRunnable(){

            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c&lTEAMS ARE NOT ALLOWED IN THIS EVENT"));
            }
        }.runTaskTimer(Main.getInstance(),0,20*60*10);
    }

    public void border(){
        new BukkitRunnable(){

            @Override
            public void run() {
                WorldBorder wb = getUHCLocation(Main.getInstance()).getWorld().getWorldBorder();

                if(!dm){
                    wb.setCenter(getUHCLocation(Main.getInstance()).getX(), getUHCLocation(Main.getInstance()).getZ());
                    wb.setSize(wb.getSize()-1);
                }else{
                    wb.reset();
                }
            }
        }.runTaskTimer(Main.getInstance(),0,20*3);
    }

    public void dm(){
        new BukkitRunnable(){
            int i = 40;
            @Override
            public void run(){
                if(i==10)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&4&lDeathMatch: &ewill start in 10 minutes"));
                else if(i==5)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&4&lDeathMatch: &ewill start in 5 minutes"));
                else if(i==1) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&4&lDeathMatch: &ewill start in 1 minute"));
                    dmtimer();
                }
                else if(i==0)
                    this.cancel();

                i--;

            }

        }.runTaskTimer(Main.getInstance(),0,20*60);
    }

    public void dmtimer(){
        new BukkitRunnable(){
            int i=60;
            @Override
            public void run(){
                if(i==15)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&4&lDeathMatch: &ewill start in 15 seconds"));
                else if(i==10)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&4&lDeathMatch: &ewill start in 10 seconds"));
                else if(i<=5 && i>0)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&4&lDeathMatch: &ewill start in XX seconds".replace("XX",i+"")));
                else if(i==0){
                    dm=true;
                    PVP.pvp=false;
                    for(Player p : players)
                        p.teleport(getDMLocation(Main.getInstance()));
                    pvptimer();

                    this.cancel();
                }

                i--;

            }
        }.runTaskTimer(Main.getInstance(),0,20);
    }

    public void pvptimer(){
        new BukkitRunnable(){
            int i=30;
            @Override
            public void run(){
                if(i==30)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c&l[DM]: &ePvp will enable in XX seconds.".replace("XX",i+"")));
                else if(i==10)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c&l[DM]: &ePvp will enable in XX seconds.".replace("XX",i+"")));
                else if(i<=5 && i>0)
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c&l[DM]: &ePvp will enable in XX seconds.".replace("XX",i+"")));
                else if(i==0){
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&c&l[DM]: &ePvp has started!"));
                    PVP.pvp=true;
                    this.cancel();
                }

                i--;

            }
        }.runTaskTimer(Main.getInstance(),0,20);
    }



    public static Location getUHCLocation(Main m){
        double x = m.getConfig().getDouble("uhc.x");
        double y = m.getConfig().getDouble("uhc.y");
        double z = m.getConfig().getDouble("uhc.z");
        String world = m.getConfig().getString("uhc.world");

        return new Location(Bukkit.getWorld(world), x, y, z);

    }

    public static Location getDMLocation(Main m){
        double x = m.getConfig().getDouble("dm.x");
        double y = m.getConfig().getDouble("dm.y");
        double z = m.getConfig().getDouble("dm.z");
        String world = m.getConfig().getString("dm.world");

        return new Location(Bukkit.getWorld(world), x, y, z);

    }
}
