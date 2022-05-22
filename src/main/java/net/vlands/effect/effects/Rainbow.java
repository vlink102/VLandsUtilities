package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Rainbow extends Effect {

    public Rainbow(VLandsUtilities plugin) {
        super("rainbow", "&cR&6a&ei&an&2b&bo&3w");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)14), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)1), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)4), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)5), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)3), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)11), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)10), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
    }
}
