package com.al0ne.Engine.Physics;

/**
 * Created by BMW on 09/07/2017.
 */
public class PotionBehaviour extends Behaviour{
    public PotionBehaviour() {
        super("potion");
    }

    @Override
    public int isInteractedWith(Behaviour b) {
        return 0;
    }
}
