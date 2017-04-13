package com.al0ne.Entities.Items.Behaviours;

import com.al0ne.Engine.Size;
import com.al0ne.Entities.Items.Behaviours.Wearable.Wearable;

/**
 * Created by BMW on 23/03/2017.
 */
public abstract class Protective extends Wearable {
    protected int armor;

    public Protective(String id, String name, String description, String shortDescription, double weight, int armor, Size size) {
        super(id, name, description, shortDescription, weight, size);
        this.armor=armor;
    }

    public int getArmor() {
        return armor;
    }
}