package com.al0ne.Entities.Items.Behaviours;

import com.al0ne.Behaviours.Enums.Command;
import com.al0ne.Behaviours.Enums.Material;
import com.al0ne.Behaviours.Player;
import com.al0ne.Behaviours.Item;
import com.al0ne.Behaviours.Room;
import com.al0ne.Behaviours.Enums.Size;
import com.al0ne.Engine.Physics.Behaviours.FoodBehaviour;
import com.al0ne.Engine.Physics.Behaviours.WaterBehaviour;
import com.al0ne.Entities.Statuses.ConcreteStatuses.Thirst;

import static com.al0ne.Engine.Main.printToLog;

/**
 * Created by BMW on 23/03/2017.
 */
public class Drinkable extends Item{
    public Drinkable(String name, String description, double weight, Size size) {
        super(name, name, description, weight, size, Material.GLASS, null);
        addCommand(Command.DRINK);
        addProperty(new FoodBehaviour());
        addProperty(new WaterBehaviour());
    }

    @Override
    public int used(Room currentRoom, Player player) {

        if(player.hasNeeds()) return 0;

        if (player.hasStatus("dehydrated")){
            player.removeStatus("dehydrated");
            printToLog("Aaaah, something to drink! Just what you needed.");
        } else{
            Thirst thirst = (Thirst) player.getStatus().get("thirst");
            thirst.setDuration(Thirst.THIRST_CLOCK);
        }
        return 1;
    }
}
