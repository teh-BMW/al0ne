package com.al0ne.Entities.Items.ConcreteItems;

import com.al0ne.Behaviours.Player;
import com.al0ne.Behaviours.Room;
import com.al0ne.Engine.Size;
import com.al0ne.Entities.Items.Behaviours.Container;
import com.al0ne.Entities.Items.ConcreteItems.Coin;
import com.al0ne.Entities.Items.ConcreteItems.Weapon.HolySword;
import com.al0ne.Entities.Items.ConcreteItems.Weapon.Knife;

/**
 * Created by BMW on 12/04/2017.
 */
public class Chest extends Container {
    public Chest() {
        super("chest", "wooden chest", "A fairly large wooden chest", "a wooden chest", 10, Size.VLARGE, true);
    }

    @Override
    public boolean used(Room currentRoom, Player player) {
        return false;
    }
}