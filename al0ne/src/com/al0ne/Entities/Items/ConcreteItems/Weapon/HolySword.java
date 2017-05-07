package com.al0ne.Entities.Items.ConcreteItems.Weapon;

import com.al0ne.Behaviours.Enums.Size;
import com.al0ne.Behaviours.Enums.Material;
import com.al0ne.Entities.Items.Behaviours.Weapons.Weapon;

/**
 * Created by BMW on 14/03/2017.
 */
public class HolySword extends Weapon{
    public HolySword() {
        super("holysword", "Holy sword", "A finely crafted silver sword.",
                "sharp", 2, 5, 1.3, Size.LARGE, Material.STEEL);
    }
}
