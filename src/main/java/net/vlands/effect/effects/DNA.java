package net.vlands.effect.effects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.DnaEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import net.vlands.util.math.YawAndPitch;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class DNA extends Effect {

    private final VLandsUtilities plugin;

    public DNA(VLandsUtilities plugin) {
        super("dna", "&eDNA");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        DnaEffect dnaEffect = new DnaEffect(effectManager);
        Location location = victim.getLocation();

        location.setPitch(YawAndPitch.Pitch.UP);

        dnaEffect.setLocation(location);
        dnaEffect.duration = 1000;
        dnaEffect.start();
    }
}
