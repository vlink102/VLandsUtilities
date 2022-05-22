package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.TextEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import net.vlands.util.math.YawAndPitch;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class GG extends Effect {

    private final VLandsUtilities plugin;

    public GG(VLandsUtilities plugin) {
        super("gg", "&dGood Game");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        TextEffect textEffect = new TextEffect(effectManager);
        Location location = victim.getLocation().add(0,2,0);

        location.setYaw(killer.getEyeLocation().getYaw());
        location.setPitch(YawAndPitch.Pitch.STRAIGHT);

        textEffect.setLocation(location);
        textEffect.particle = Particle.FLAME;
        textEffect.text = "GG";
        textEffect.duration = 2500;
        textEffect.start();
    }
}
