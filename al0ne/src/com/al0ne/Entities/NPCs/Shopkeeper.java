package com.al0ne.Entities.NPCs;

import com.al0ne.Entities.NPC;
import com.al0ne.Entities.Player;
import com.al0ne.Items.Item;
import com.al0ne.Items.Pair;
import com.al0ne.Items.Pricepair;

import java.util.HashMap;

/**
 * Created by BMW on 14/03/2017.
 */
public class Shopkeeper extends NPC {
    private HashMap<String, Pricepair> inventory;
    private String list;

    public Shopkeeper(String id, String name, String description, String shortDescription, String intro) {
        super(id, name, description,shortDescription, intro);
        inventory = new HashMap<>();
        list="Items: ";
        isShopkeeper=true;
    }

    public HashMap<String, Pricepair> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item, int price) {
        this.inventory.put(item.getID(), new Pricepair(item, price));
        list+=item.getName()+" - "+price+" coins    ";
        addSubject("items", list);
    }

    public boolean hasItem(String item) {
        for (Pricepair p : inventory.values()){
            Item currentItem = p.getItem();
            //maybe need to use different check
            if (currentItem.getName().toLowerCase().equals(item.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public Pricepair getItem(String item) {
        for (Pricepair p : inventory.values()){
            Item currentItem = p.getItem();
            //maybe need to use different check
            if (currentItem.getName().toLowerCase().equals(item.toLowerCase())){
                return p;
            }
        }
        return null;
    }

    public void buy(Player player, String toBuy){
        if (hasItem(toBuy)){
            Pricepair item = getItem(toBuy);
            if (player.hasEnoughMoney(-item.getPrice())){
                Pair pairCoin = player.getInventory().get("coin");
                pairCoin.setCount(pairCoin.getCount()-item.getPrice());

                Pair pairInv = player.getItemPair(item.getItem().getID());
                if(pairInv != null){
                    pairInv.setCount(pairInv.getCount()+1);
                } else {
                    if (!player.simpleAddItem(item.getItem(), 1)){
                        player.getCurrentRoom().addEntity(item.getItem(), 1);
                    }
                }
                System.out.println("\"There you go!\"");
                System.out.println("You received 1 "+item.getItem().getName());

            } else{
                System.out.println("\"You don't have enough money\"");
            }
        } else{
            System.out.println("\"I don't seem to have that item with me.\"");
        }
    }
}