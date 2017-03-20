package com.al0ne.Entities;

import com.al0ne.Room;

import java.util.ArrayList;

/**
 * Created by BMW on 02/02/2017.
 *
 * Item interface
 */
public abstract class Entity {

    protected String name;
    protected String ID;
    protected String longDescription;
    protected String shortDescription;


    /**
    * i: item
    * p: prop
    * n: npc
    * e: enemy
    * */
    protected char type;

    protected ArrayList<String> requiredCommand;


    public Entity(String id, String name, String longDescription, String shortDescription) {
        this.ID = id;
        this.name = name;
        this.longDescription = longDescription;
        this.shortDescription=shortDescription;
        this.requiredCommand=new ArrayList<>();
        addCommand("examine");
    }


    public abstract boolean used(Room currentRoom, Player player);



    public char getType() {
        return type;
    }


    public String getName() {
        return name.toLowerCase();
    }

    public String getID(){
        return ID;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void printLongDescription() {
        System.out.println(longDescription);
    }

    public void printShortDescription() {
        System.out.println(shortDescription);
    }


    public ArrayList<String> getRequiredCommand() {
        return requiredCommand;
    }

    public void addCommand(String cmd){
        requiredCommand.add(cmd);
    }

}