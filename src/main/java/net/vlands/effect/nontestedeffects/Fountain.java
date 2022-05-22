package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.FountainEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Fountain extends Effect {

    private final VLandsUtilities plugin;

    public Fountain(VLandsUtilities plugin) {
        super("fountain", "&9Fountain");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        FountainEffect fountainEffect = new FountainEffect(effectManager);
        Location location = victim.getLocation();

        fountainEffect.setLocation(location);
        fountainEffect.duration = 2500;
        fountainEffect.start();
    }
}
