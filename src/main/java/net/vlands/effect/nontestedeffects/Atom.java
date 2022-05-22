package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.ArcEffect;
import de.slikey.effectlib.effect.AtomEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import net.vlands.util.math.YawAndPitch;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Atom extends Effect {

    private final VLandsUtilities plugin;

    public Atom(VLandsUtilities plugin) {
        super("atom", "&bA&dt&bo&dm");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        AtomEffect atomEffect = new AtomEffect(effectManager);
        Location location = victim.getLocation();

        atomEffect.setLocation(location);
        atomEffect.duration = 2500;
        atomEffect.start();

    }
}
