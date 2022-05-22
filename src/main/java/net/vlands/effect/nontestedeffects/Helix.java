package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.HelixEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Helix extends Effect {

    private final VLandsUtilities plugin;

    public Helix(VLandsUtilities plugin) {
        super("helix", "&bHelix");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        HelixEffect helixEffect = new HelixEffect(effectManager);
        Location location = victim.getLocation();

        helixEffect.setLocation(location);
        helixEffect.duration = 1000;
        helixEffect.particle = Particle.DRAGON_BREATH;
        helixEffect.start();
    }
}
