package com.al0ne.ConcreteEntities.Items.ConcreteItems.Coin;

import com.al0ne.AbstractEntities.Enums.Material;

/**
 * Created by BMW on 01/05/2017.
 */
public class BrassCoin extends Coin {
    public BrassCoin() {
        super("bcoin", "Brass coin", "A fairly opaque coin.",
                0.01, Material.BRASS);
        this.value = 1;
    }

}