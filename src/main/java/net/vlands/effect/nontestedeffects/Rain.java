package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.CloudEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Rain extends Effect {

    private final VLandsUtilities plugin;

    public Rain(VLandsUtilities plugin) {
        super("rain", "&bRain");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        CloudEffect cloudEffect = new CloudEffect(effectManager);
        Location location = victim.getLocation();

        cloudEffect.setLocation(location);
        cloudEffect.duration = 2500;
        cloudEffect.start();
    }
}
