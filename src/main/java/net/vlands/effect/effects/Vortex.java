package net.vlands.effect.effects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.VortexEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import net.vlands.util.math.YawAndPitch;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Vortex extends Effect {

    private final VLandsUtilities plugin;

    public Vortex(VLandsUtilities plugin) {
        super("vortex", "&8Vortex");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        Location location = victim.getLocation().subtract(0,1,0);
        VortexEffect vortexEffect = new VortexEffect(effectManager);

        location.setPitch(YawAndPitch.Pitch.UP);

        vortexEffect.duration = 2500;
        vortexEffect.setLocation(location);
        vortexEffect.start();
    }
}
