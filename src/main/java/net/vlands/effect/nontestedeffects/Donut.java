package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.DonutEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Donut extends Effect {

    private final VLandsUtilities plugin;

    public Donut(VLandsUtilities plugin) {
        super("donut", "&eDonut");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        DonutEffect donutEffect = new DonutEffect(effectManager);
        Location location = victim.getLocation();

        donutEffect.setLocation(location);
        donutEffect.duration = 2500;
        donutEffect.start();
    }
}
