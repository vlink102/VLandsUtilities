package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Flower extends Effect {

    public Flower(VLandsUtilities plugin) {
        super("flower", "&dFlower");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.RED_MUSHROOM, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DOUBLE_PLANT, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DOUBLE_PLANT, (byte)4), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.SAPLING, (byte)4), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.SAPLING, (byte)1), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.YELLOW_FLOWER, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DOUBLE_PLANT, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DOUBLE_PLANT, (byte)4), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.SAPLING, (byte)5), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DOUBLE_PLANT, (byte)4), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.SAPLING, (byte)5), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.YELLOW_FLOWER, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DOUBLE_PLANT, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.YELLOW_FLOWER, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.RED_MUSHROOM, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.YELLOW_FLOWER, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.SAPLING, (byte)5), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.RED_MUSHROOM, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
    }
}
