package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.ExplodeEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Explosion extends Effect {

    private final VLandsUtilities plugin;

    public Explosion(VLandsUtilities plugin) {
        super("explosion", "&cExplosion");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        ExplodeEffect explodeEffect = new ExplodeEffect(effectManager);
        Location location = victim.getLocation();

        explodeEffect.setLocation(location);
        explodeEffect.start();
    }
}
