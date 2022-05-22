package net.vlands.effect.effects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.DiscoBallEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Disco extends Effect {

    private final VLandsUtilities plugin;

    public Disco(VLandsUtilities plugin) {
        super("disco", "&cD&6i&es&ac&2o");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        DiscoBallEffect discoBallEffect = new DiscoBallEffect(effectManager);
        Location location = victim.getLocation();

        discoBallEffect.setLocation(location);
        discoBallEffect.duration = 2500;
        discoBallEffect.start();
    }
}
