package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.StarEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Star extends Effect {

    private final VLandsUtilities plugin;

    public Star(VLandsUtilities plugin) {
        super("star", "&6S&et&6a&er");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        StarEffect starEffect = new StarEffect(effectManager);
        Location location = victim.getLocation();

        starEffect.setLocation(location);
        starEffect.duration = 2500;
        starEffect.start();
    }
}
