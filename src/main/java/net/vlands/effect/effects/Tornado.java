package net.vlands.effect.effects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.TornadoEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Tornado extends Effect {

    private final VLandsUtilities plugin;

    public Tornado(VLandsUtilities plugin) {
        super("tornado", "&8Tornado");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        TornadoEffect tornadoEffect = new TornadoEffect(effectManager);
        Location location = victim.getLocation().subtract(0,1,0);

        tornadoEffect.setLocation(location);
        tornadoEffect.duration = 1000;
        tornadoEffect.maxTornadoRadius = 3;
        tornadoEffect.tornadoParticle = Particle.SMOKE_LARGE;
        tornadoEffect.start();
    }
}
