package com.al0ne.Behaviours.Spells;

import com.al0ne.Behaviours.Player;

import static com.al0ne.Engine.Main.printToLog;

/**
 * Created by BMW on 15/04/2017.
 */
public class LightHeal extends HealingSpell{

    public LightHeal() {
        super("lightheal", "Light heal", "A lesser healing spell", 2);
    }

    @Override
    public boolean isCasted(Player player) {
        player.modifyHealth(+healing);
        printToLog(onCast);
        return true;
    }
}
