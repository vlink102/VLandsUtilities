package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Chess extends Effect {

    public Chess(VLandsUtilities plugin) {
        super("chess", "&8C&fh&8e&fs&8s");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.QUARTZ_BLOCK, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COAL_BLOCK, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
    }
}
