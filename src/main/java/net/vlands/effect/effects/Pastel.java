package net.vlands.effect.effects;

import com.avaje.ebean.validation.Past;
import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Pastel extends Effect {

    public Pastel(VLandsUtilities plugin) {
        super("pastel", "&dP&6a&3s&2t&5e&9l");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS_PANE, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS_PANE, (byte)14), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS_PANE, (byte)6), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.REDSTONE.display(0.3F, 0.3F, 0.3F, 1.0F, 3, location, 20.0D);
    }
}
