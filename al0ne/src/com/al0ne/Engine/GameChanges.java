package com.al0ne.Engine;

import com.al0ne.Behaviours.*;
import com.al0ne.Behaviours.Pairs.Pair;
import com.al0ne.Behaviours.Pairs.PairWorld;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

import static com.al0ne.Engine.Main.player;
import static com.al0ne.Engine.Main.printToLog;

/**
 * Created by BMW on 13/04/2017.
 */
public class GameChanges {

    public static void save(String s, String path, Game g){
        FileOutputStream fop = null;
        ObjectOutputStream oos = null;
        File file;

        g.setNotes(Main.notes.getText());

        try {
            if (path != null){
                file = new File(path+".save");
            } else{
                file = new File("./"+s+".save");
            }
            fop = new FileOutputStream(file);
            oos = new ObjectOutputStream(fop);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                printToLog("File already exists. Specify a non existing file name.");
                return;
            }

            // get the content in bytes
            oos.writeObject(g);

            oos.flush();
            oos.close();

            printToLog("Saving successful");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fop != null) {
                try {
                    fop.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void load(String s, String path){
        Game loaded = deserializeGame(s, path);
        if (loaded == null){
            printToLog("Failed to load the game.");
            return;
        }

        Main.game = loaded;
        Main.player = loaded.getPlayer();
        Main.turnCounter = loaded.getTurnCount();
        Main.currentRoom = loaded.getRoom();
        Main.notes.setText(loaded.getNotes());

        printToLog("Game loaded successfully.");
        printToLog();
        Main.currentRoom.printRoom();
        Main.currentRoom.printName();


    }

    private static Game deserializeGame(String filename, String path) {

        Game game = null;

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {
            if (path != null){
                fin = new FileInputStream(path);
            } else{
                fin = new FileInputStream(filename);
            }
            ois = new ObjectInputStream(fin);
            game = (Game) ois.readObject();

        } catch (Exception ex) {
            printToLog("File not found");
            return null;
//            ex.printStackTrace();
        } finally {

            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return game;

    }

    public static boolean changeWorld(String s){
        //save old state
        PairWorld oldWorld = Main.game.getWorld(Main.game.getCurrentWorld());
        oldWorld.setPlayer(Main.player);

        switch (s){
            case "alphaworld":
                PairWorld alpha = Main.game.getWorld(s);
                Main.game.setCurrentWorld(s);
                Main.player = Main.game.getPlayer();
//                player.setCurrentRoom(alpha.get);
                Main.currentRoom = Main.player.getCurrentRoom();
                ParseInput.clearScreen();
                return true;
            case "caveworld":
                PairWorld cave = Main.game.getWorld(s);
                Main.game.setCurrentWorld(s);
                Main.player = Main.game.getPlayer();
//                player.setCurrentRoom(alpha.get);
                Main.currentRoom = Main.player.getCurrentRoom();
                ParseInput.clearScreen();
                return true;
            default:
                printToLog("404: world not found");
                return false;
        }
    }

    public static ObservableList<SimpleItem> getInventoryData(){

        ObservableList<SimpleItem> data = FXCollections.observableArrayList();

        if (Main.player.getInventory().size()==0){}
        else {
            for (Pair pair : Main.player.getInventory().values()) {
                Item currentItem = (Item) pair.getEntity();
                SimpleItem s = new SimpleItem(currentItem.getName(), pair.getCount(), currentItem.getWeight()*pair.getCount());
                data.add(s);
            }
        }
        return data;
    }

    public static void attackIfAggro(Player player, Room currentRoom){
        if(currentRoom.hasEnemies()){
            for (Enemy e : currentRoom.getEnemyList()){
                if(e.isAggro() && !e.isSnooze()){
                    e.isAttacked(player, currentRoom);
                } else{
                    if (e.isSnooze()){
                        e.setSnooze(false);
                    }
                }
            }
        }
    }

    public static void handleStatus(Player player){
        if(player.getStatus().size()>0){
            ArrayList<Status> toResolve = new ArrayList<>();
            for (Status status: player.getStatus().values()){
                if(status.resolveStatus(player)){
                    toResolve.add(status);
                }
            }
            for (Status st : toResolve){
                player.getStatus().remove(st.getName());
            }

            for (Status toApply : player.getToApply()){
                player.getStatus().put(toApply.getName(), toApply);
            }

            player.getToApply().clear();
        }
    }

    public static void restartGame(){
        GameChanges.changeWorld(Main.game.getStartingWorld());
        Main.input.setDisable(false);
        Main.game = new Game(0, player.hasNeeds());
        Main.player = Main.game.getPlayer();
        Main.currentRoom = Main.game.getRoom();
        Main.log.setText("");
        printToLog("Game restarted.");
        printToLog();
        Main.player.getCurrentRoom().printName();
        Main.player.getCurrentRoom().printRoom();
    }

}