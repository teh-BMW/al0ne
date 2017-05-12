package com.al0ne.Behaviours;

import com.al0ne.Behaviours.Pairs.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * placeholder class for attackable npcs
 */
public abstract class WorldCharacter extends Entity{

    //Maps ItemID, Pair
    protected HashMap<String, Pair> inventory;


    //current and max Health of the player
    protected int currentHealth;
    protected int maxHealth;

    //fighting stats
    protected int attack;
    protected int dexterity;

    //base armor
    protected int armor;
    protected int damage;

    //statuses
    protected HashMap<String, Status> status;

    //various
    protected boolean alive;

    public WorldCharacter(String id, String name, String longDescription, String shortDescription,
                          int maxHealth, int attack, int dexterity, int armor, int damage) {
        super(id, name, longDescription, shortDescription);
        this.inventory = new HashMap<>();

        this.alive = true;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.dexterity = dexterity;
        this.armor = armor;
        this.damage = damage;

        this.status = new HashMap<>();
    }


    public int getCurrentHealth() {
        return currentHealth;
    }

    //this returns true if the character is alive
    public boolean isAlive(){
        return alive;
    }

    //this functions return the required value
    public int getAttack() {
        return attack;
    }
    public int getDexterity() {

        return dexterity;
    }
    public int getMaxHealth() {
        return maxHealth;
    }


    //this function sets health to the amount given
    //it binds it to maxHealth and 0
    public void setHealth(int amount) {
        if(currentHealth+amount > maxHealth){
            currentHealth=maxHealth;
        } else if(currentHealth+amount < 0){
            currentHealth=0;
            alive=false;
        }
    }

    //this function modifies health with the amount given
    //it binds it to maxHealth and 0
    //returns true if the player is still alive
    public boolean modifyHealth(int health) {
        if (this.currentHealth + health <= maxHealth){
            this.currentHealth += health;
            if (this.currentHealth<=0){
                this.currentHealth=0;
                alive = false;
                return false;
            }
            return true;
        } else{
            return true;
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getArmorLevel(){
        return armor;
    }


    //this function checks if the character has an item in the inventory
    //we check for ITEMID EQUALITY
    //if there is no item, it returns false
    public boolean hasItemInInventory(String item){
        for (String s : inventory.keySet()){
            if (s.equals(item)){
                return true;
            }
        }
        return false;
    }

    //this function tries to get an item from the inventory
    //if there is no such item, it returns null
    public Pair getItemPair(String itemID){
        if(hasItemInInventory(itemID)){
            return inventory.get(itemID);
        } else {
            return null;
        }
    }

    //returns the statuses
    public HashMap<String, Status> getStatus() {
        return status;
    }

    //adds a status to the statuses, it refreshes the duration
    public boolean putStatus(Status s) {
        for (Status st : status.values()){
            if(s.getName().equals(st.getName())){
                //refresh on reapply of status
                st.duration = s.maxDuration;
                return false;
            }
        }
        s.setDuration(s.maxDuration);
        status.put(s.getName(), s);
        return true;
    }

    //this removes a status if the name matches
    public boolean removeStatus(String statusName) {

        if (status.get(statusName) != null){
            status.remove(statusName);
        }
        return true;
    }

    //returns true if the player has the status s
    public boolean hasStatus(String s) {
        return status.get(s) != null;
    }

    public void handleStatuses(){
        if(status.size()>0){
            ArrayList<Status> toRemove = new ArrayList<>();
            ArrayList<Status> toAdd = new ArrayList<>();
            for (Status status: status.values()){
                if(status.resolveStatus(this)){
                    toAdd.addAll(status.getToApply());
                    toRemove.add(status);
                }
            }
            for (Status st : toRemove){
                status.remove(st.getName());
            }

            for (Status toApply : toAdd){
                status.put(toApply.getName(), toApply);
            }
        }
    }

    public boolean simpleAddItem(Item item, Integer amount){
        if (hasItemInInventory(item.getID())){
            Pair fromInventory = inventory.get(item.getID());
            fromInventory.modifyCount(amount);
            return true;
        } else {
            inventory.put(item.getID(), new Pair(item, amount));
            return true;
        }
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int used(Room currentRoom, Player player) {
        return 0;
    }
}
