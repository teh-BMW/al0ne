package com.al0ne.Behaviours;

import com.al0ne.Behaviours.Enums.Size;
import com.al0ne.Engine.Utility;
import com.al0ne.Behaviours.Enums.Material;

import java.util.ArrayList;

import static com.al0ne.Engine.Main.printToLog;

/**
 * Created by BMW on 02/02/2017.
 *
 * Item interface
 */
public abstract class Item extends Entity {

    //todo: add plural?
    protected double weight;
    protected int size;
    protected ArrayList<String> properties;
    protected ArrayList<String> requiredType;
    protected boolean unique;
    protected boolean canDrop;
    public boolean canTake;
    protected Material material;
    public boolean customName=false;


    public Item(String id, String name, String description, double weight, Size size) {
        super(id, name, description, Utility.getArticle(name)+" "+name.toLowerCase());
        this.weight = weight;
        this.properties = new ArrayList<>();
        this.requiredType = new ArrayList<>();
        this.type='i';
        this.size=Size.toInt(size);
        this.unique = false;
        this.canDrop = true;
        this.material = Material.UNDEFINED;
        this.canTake=true;
    }

    public Item(String id, String name, String description, double weight, Size size, Material material) {
        super(id, name, description,
                Utility.getArticle(Material.stringify(material))+
                        " "+Material.stringify(material)+" "+name.toLowerCase());
        this.weight = weight;
        this.properties = new ArrayList<>();
        this.requiredType = new ArrayList<>();
        this.type='i';
        this.size=Size.toInt(size);
        this.unique = false;
        this.canDrop = true;
        this.material = material;
        this.canTake=true;
    }

    public double getWeight() {
        return weight;
    }

    public void setUndroppable() {
        this.canDrop = false;
    }

    public boolean canDrop(){
        return canDrop;
    }

    public void modifyWeight(double amt) {
        double temp = Math.round((weight+=amt)*100);
        weight = temp/100;
    }


    public boolean usedWith(Item item, Room currentRoom, Player player) {
        for (String s: requiredType){
            if (item.hasProperty(s)){
                used(currentRoom, player);
                return true;
            }
        }
        return false;
    }

    public void setUnique(){
        this.unique=true;
    }

    public boolean isUnique(){
        return unique;
    }



    protected void addProperty(String behaviour){
        properties.add(behaviour);
    }

    public boolean hasProperty(String property){
        for (String s : properties){
            if (s.equals(property)){
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void printLongDescription(Player player, Room room) {
        super.printLongDescription(player, room);
        printToLog("It's "+(Size.toString(this.size))+".");
        String m = Material.stringify(this.material);
        if(!m.equals("undefined")){
            printToLog("It's made of "+(Material.stringify(this.material))+".");
        }
    }

    public boolean canTake() {
        return canTake;
    }

    public void setCanTake(boolean canTake) {
        this.canTake = canTake;
    }

    @Override
    public String getName() {
        if(customName){
            return name.toLowerCase();
        }
        return Material.stringify(this.material)+" "+name.toLowerCase();
    }

    @Override
    public void setShortDescription(String shortDescription) {
        this.customName=true;
        super.setShortDescription(shortDescription);
    }
}
