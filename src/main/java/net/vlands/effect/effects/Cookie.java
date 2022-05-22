package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Cookie extends Effect {

    public Cookie(VLandsUtilities plugin) {
        super("cookie", "&6C&8o&6o&8k&6i&8e");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKIE, (byte)0), 0.7F, 0.7F, 0.7F, 0.1F, 35, location, 20.0D);
    }
}
