package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.ShieldEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import net.vlands.util.math.VectorModification;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Shield extends Effect {

    private final VLandsUtilities plugin;

    public Shield(VLandsUtilities plugin) {
        super("shield", "&bS&6h&bi&6e&bl&bd");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        ShieldEffect shieldEffect = new ShieldEffect(effectManager);
        Location location = victim.getLocation();

        location.setDirection(VectorModification.inverseVector(killer.getEyeLocation().getDirection()));
        shieldEffect.setLocation(location);
        shieldEffect.duration = 1000;
        shieldEffect.start();
    }
}
