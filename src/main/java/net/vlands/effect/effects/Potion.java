package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Potion extends Effect {

    public Potion(VLandsUtilities plugin) {
        super("potion", "&5Potion");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.SPELL_MOB.display(0.4F, 0.4F, 0.4F, 1.0F, 100, victim.getLocation(), 100.0D);
    }
}
