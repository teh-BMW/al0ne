package com.al0ne.Entities.Items.Behaviours.Wearable;

import com.al0ne.Behaviours.Player;
import com.al0ne.Behaviours.Enums.Size;
import com.al0ne.Engine.Utility;
import com.al0ne.Behaviours.Enums.Material;
import com.al0ne.Entities.Items.Behaviours.Protective;
import com.al0ne.Behaviours.Room;

import static java.lang.Math.max;

/**
 * Created by BMW on 23/03/2017.
 */
public class Shield extends Protective{
    public Shield(String id, String name, String description,
                  double weight, int armor, Material material) {
        super(id, name, description, weight, armor, 10, Size.NORMAL, material);
        this.part="off hand";
    }

    public Shield(Material m) {
        super(Material.stringify(m)+"shield", "Shield",
                Utility.getArticle(Material.stringify(m))+" "
                        +Material.stringify(m)+" shield.", Math.max(m.getWeight()-1, 1),
                max(m.getToughness()-1, 1), 10, Size.NORMAL, m);
        this.part = "off hand";
    }

    @Override
    public int used(Room currentRoom, Player player) {
        return 0;
    }
}
