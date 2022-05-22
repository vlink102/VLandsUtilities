package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.AnimatedBallEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Ball extends Effect {

    private final VLandsUtilities plugin;

    public Ball(VLandsUtilities plugin) {
        super("ball", "&dBall");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        AnimatedBallEffect animatedBallEffect = new AnimatedBallEffect(effectManager);
        Location location = victim.getLocation();

        animatedBallEffect.setLocation(location);
        animatedBallEffect.duration = 1500;
        animatedBallEffect.size = 2;
        animatedBallEffect.start();
    }
}
