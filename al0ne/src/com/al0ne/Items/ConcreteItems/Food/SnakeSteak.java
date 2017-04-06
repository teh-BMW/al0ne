package com.al0ne.Items.ConcreteItems.Food;

import com.al0ne.Entities.Behaviours.Player;
import com.al0ne.Entities.Behaviours.Room;
import com.al0ne.Items.Behaviours.Food;

/**
 * Created by BMW on 06/04/2017.
 */
public class SnakeSteak extends Food{
    public SnakeSteak() {
        super("snakesteak","Snake Steak", "Some meat from a snake. Doesn't look too inviting", "snake steak", 0.3);
        addCommand("eat");
    }

    @Override
    public boolean used(Room currentRoom, Player player){
        player.modifyHealth(+1);
        return true;
    }
}