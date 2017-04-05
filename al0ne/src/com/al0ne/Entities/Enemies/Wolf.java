package com.al0ne.Entities.Enemies;

import com.al0ne.Entities.Behaviours.Enemy;
import com.al0ne.Items.ConcreteItems.Food.Apple;
import com.al0ne.Items.ConcreteItems.Coin;

/**
 * Created by BMW on 13/03/2017.
 */
public class Wolf extends Enemy {
    public Wolf() {
        super("wolf", "wolf", "a fierce wolf", "This wolf looks really ferocious and battle hardened.");
        addItemLoot(new Coin(), 20, 50);
        addItemLoot(new Apple(), 1, 100);
        addResistance("fists");
        setStats(10, 2, 40, 1, 40);
    }
}
