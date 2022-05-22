package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.WaveEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import net.vlands.util.math.YawAndPitch;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public class Wave extends Effect {

    private final VLandsUtilities plugin;

    public Wave(VLandsUtilities plugin) {
        super("wave", "&9W&ba&9v&be");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        WaveEffect waveEffect = new WaveEffect(effectManager);
        Location location = victim.getLocation();

        location.setYaw(killer.getEyeLocation().getYaw());
        location.setPitch(YawAndPitch.Pitch.STRAIGHT);

        waveEffect.setLocation(location);
        waveEffect.velocity = location.getDirection();
        waveEffect.duration = 2500;
        waveEffect.period = 5;
        waveEffect.iterations = 30;
        waveEffect.start();
    }
}
