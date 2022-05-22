package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.PyramidEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Pyramid extends Effect {

    private final VLandsUtilities plugin;

    public Pyramid(VLandsUtilities plugin) {
        super("music", "&dM&bu&ds&bi&dc");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        PyramidEffect pyramidEffect = new PyramidEffect(effectManager);
        Location location = victim.getLocation();

        pyramidEffect.setLocation(location);
        pyramidEffect.duration = 2500;
        pyramidEffect.start();
    }
}
