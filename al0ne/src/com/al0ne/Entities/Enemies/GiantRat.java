package com.al0ne.Entities.Enemies;

import com.al0ne.Behaviours.Enemy;
import com.al0ne.Entities.Statuses.ConcreteStatuses.BlackDeath;

/**
 * Created by BMW on 07/04/2017.
 */
public class GiantRat extends Enemy{
    public GiantRat() {
        super("Giant rat", "A big, disgusting rat. It looks somehow ill.", "a rat",
                5, 40, 40, 0, 1);
        addInflictedStatus(new BlackDeath(), 5);
    }
}
