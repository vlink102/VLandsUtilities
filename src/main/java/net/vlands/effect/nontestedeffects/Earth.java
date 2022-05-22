package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.EarthEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Earth extends Effect {

    private final VLandsUtilities plugin;

    public Earth(VLandsUtilities plugin) {
        super("earth", "&aE&ba&ar&bt&ah");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        EarthEffect earthEffect = new EarthEffect(effectManager);
        Location location = victim.getLocation();

        earthEffect.setLocation(location);
        earthEffect.duration = 2500;
        earthEffect.start();
    }
}
