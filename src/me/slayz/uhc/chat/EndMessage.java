package me.slayz.uhc.chat;

import me.slayz.uhc.events.Death;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class EndMessage {

    private final static int CENTER_PX = 154;


    public static void sendMessage(Player p){
        ArrayList<Player> players = reverseArrayList(Death.deathList);


        if(players.size()!=0) {
            sendCenteredMessage(p, ChatColor.GREEN + "-----------------------------------");
            sendCenteredMessage(p, ChatColor.GOLD + "" + ChatColor.BOLD + "UHC IS OVER!");
            p.sendMessage(" ");


            if (players.size() == 1)
                sendCenteredMessage(p, ChatColor.YELLOW + "" + ChatColor.BOLD + "1st Place " + ChatColor.GRAY + "- " +
                        players.get(0).getName() + " - " + ChatColor.YELLOW + Death.list.get(players.get(0)).get("points")+" Points ("+Death.list.get(players.get(0)).get("kills")+" Kills)");
            else if (players.size() == 2) {
                sendCenteredMessage(p, ChatColor.YELLOW + "" + ChatColor.BOLD + "1st Place " + ChatColor.GRAY + "- " +
                        players.get(0).getName() + " - " + ChatColor.YELLOW + Death.list.get(players.get(0)).get("points")+" Points ("+Death.list.get(players.get(0)).get("kills")+" Kills)");

                sendCenteredMessage(p, ChatColor.YELLOW + "" + ChatColor.BOLD + "2nd Place " + ChatColor.GRAY + "- " +
                        players.get(1).getName() + " - " + ChatColor.YELLOW + Death.list.get(players.get(1)).get("points")+" Points ("+Death.list.get(players.get(1)).get("kills")+" Kills)");
            } else {
                sendCenteredMessage(p, ChatColor.YELLOW + "" + ChatColor.BOLD + "1st Place " + ChatColor.GRAY + "- " +
                        players.get(0).getName() + " - " + ChatColor.YELLOW + Death.list.get(players.get(0)).get("points")+" Points ("+Death.list.get(players.get(0)).get("kills")+" Kills)");

                sendCenteredMessage(p, ChatColor.YELLOW + "" + ChatColor.BOLD + "2nd Place " + ChatColor.GRAY + "- " +
                        players.get(1).getName() + " - " + ChatColor.YELLOW + Death.list.get(players.get(1)).get("points")+" Points ("+Death.list.get(players.get(1)).get("kills")+" Kills)");

                sendCenteredMessage(p, ChatColor.YELLOW + "" + ChatColor.BOLD + "3rd Place " + ChatColor.GRAY + "- " +
                        players.get(2).getName() + " - " + ChatColor.YELLOW + (Death.list.get(players.get(2)).get("points")/2)+" Points ("+Death.list.get(players.get(2)).get("kills")+" Kills)");
            }


            p.sendMessage("    ");
            if (players.contains(p)) {
                sendCenteredMessage(p, ChatColor.YELLOW + "Your place: " + ChatColor.GRAY + " (Position #" + position(players, p)+")");
                p.sendMessage("     ");

            }

            sendCenteredMessage(p, ChatColor.GREEN + "-----------------------------------");

        }
    }

    public static void sendCenteredMessage(Player player, String message){
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public static ArrayList<Player> reverseArrayList(ArrayList<Player> alist){
        // Arraylist for storing reversed elements
        ArrayList<Player> revArrayList = new ArrayList<Player>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }

    public static int position(ArrayList<Player> a,Player p){
        for (int i = 0; i<a.size(); i++) {
            if(a.get(i)==p){
                return (i+1);
            }
        }
        return -1;

    }
}
