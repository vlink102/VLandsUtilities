package net.vlands.effect.effects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.DragonEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Dragon extends Effect {

    private final VLandsUtilities plugin;

    public Dragon(VLandsUtilities plugin) {
        super("dragon", "&3Dragon");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        DragonEffect dragonEffect = new DragonEffect(effectManager);
        Location location = victim.getLocation();

        location.setDirection(killer.getEyeLocation().getDirection());

        dragonEffect.setLocation(location);
        dragonEffect.duration = 1500;
        dragonEffect.start();
    }
}
