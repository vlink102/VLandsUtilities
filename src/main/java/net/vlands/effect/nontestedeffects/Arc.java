package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.ArcEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import net.vlands.util.math.YawAndPitch;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Arc extends Effect {

    private final VLandsUtilities plugin;

    public Arc(VLandsUtilities plugin) {
        super("arc", "&eArc");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        ArcEffect arcEffect = new ArcEffect(effectManager);
        Location location = victim.getLocation();

        location.setPitch(YawAndPitch.Pitch.UP);

        arcEffect.setLocation(location);
        arcEffect.duration = 2500;
        arcEffect.start();
    }
}
