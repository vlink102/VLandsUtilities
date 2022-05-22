package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.MusicEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Music extends Effect {

    private final VLandsUtilities plugin;

    public Music(VLandsUtilities plugin) {
        super("music", "&dM&bu&ds&bi&dc");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        MusicEffect musicEffect = new MusicEffect(effectManager);
        Location location = victim.getLocation();

        musicEffect.setLocation(location);
        musicEffect.duration = 2500;
        musicEffect.start();
    }
}
