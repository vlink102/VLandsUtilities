package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.CircleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Sphere extends Effect {

    private final VLandsUtilities plugin;

    public Sphere(VLandsUtilities plugin) {
        super("sphere", "&aSphere");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        CircleEffect circleEffect = new CircleEffect(effectManager);
        Location location = victim.getLocation();

        circleEffect.setLocation(location);
        circleEffect.wholeCircle = true;
        circleEffect.period = 2;
        circleEffect.duration = 2500;
        circleEffect.start();
    }
}
