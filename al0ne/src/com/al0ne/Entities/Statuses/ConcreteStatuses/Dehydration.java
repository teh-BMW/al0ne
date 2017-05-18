package com.al0ne.Entities.Statuses.ConcreteStatuses;

import com.al0ne.Behaviours.Player;
import com.al0ne.Behaviours.Status;
import com.al0ne.Behaviours.abstractEntities.WorldCharacter;

import static com.al0ne.Engine.Main.printToLog;

/**
 * Created by BMW on 13/04/2017.
 */
public class Dehydration extends Status {
    public Dehydration() {
        super("dehydration", -1, "You need some water, badly.", "Your body withers from the lack of water.", "Finally some fresh water!");
    }

    @Override
    public boolean resolveStatus(WorldCharacter character) {
        if(!character.modifyHealth(-1) && character instanceof Player){
            ((Player) character).setCauseOfDeath(name);
        }
        printToLog(onTick);
        return false;
    }
}
