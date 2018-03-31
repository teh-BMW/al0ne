package com.al0ne.ConcreteEntities.Spells;

import com.al0ne.AbstractEntities.Abstract.Entity;
import com.al0ne.AbstractEntities.Abstract.Item;
import com.al0ne.AbstractEntities.Pairs.Pair;
import com.al0ne.AbstractEntities.Player.Player;
import com.al0ne.Engine.Utility.Utility;
import com.al0ne.ConcreteEntities.Items.ConcreteItems.Coin.SilverCoin;

import static com.al0ne.Engine.Main.printToLog;

/**
 * Created by BMW on 22/04/2017.
 */
public class MidasSpell extends TargetSpell{
    public MidasSpell() {
        super("midas", "Touch of gold", "This spell turns an item to gold", 'i');
    }

    @Override
    public boolean isCasted(Player player, Entity entity) {
        if(entity.getType() != 'i' && entity.getType() != 'w'){
            printToLog("You can only target objects.");
            return false;
        } else{
            Item item = (Item) entity;

            if (item.isUnique()){
                printToLog("Your spell doesn't work on that item.");
                return false;
            } else{
                if(player.hasItemInInventory(entity.getID())){
                    if (player.isWearingItem(entity.getID())){
                        player.unequipItem(entity.getID());
                    }
                    if(!player.getInventory().get(item.getID()).subCount()){
                        player.getInventory().remove(item.getID());
                    }
                    player.recalculateWeight();
                    int goldAmt = Utility.randomNumber(2*(int) (item.getWeight()*10));
                    SilverCoin coin = new SilverCoin();

                    if( player.addAmountItem(new Pair(new SilverCoin(), goldAmt), goldAmt) ){
                        printToLog("You turn the "+item.getName()+" into "+goldAmt+" coins!");
                        return true;
                    } else {
                        int goldDropped = (int) (coin.getWeight()*goldAmt - (player.getMaxWeight() - player.getCurrentWeight()));
                        player.addAmountItem(new Pair(new SilverCoin(), goldAmt-goldDropped), goldAmt-goldDropped);
                        player.getCurrentRoom().addEntity(new SilverCoin(), goldDropped);

                        printToLog("You turn the "+item.getName()+" into gold! "+goldDropped+" coins drop on the floor.");
                        return true;
                    }
                } else{
                    printToLog("You need to be holding that item.");
                    return false;
                }
            }
        }
    }
}