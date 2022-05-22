package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.BigBangEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class BigBang extends Effect {

    private final VLandsUtilities plugin;

    public BigBang(VLandsUtilities plugin) {
        super("bigbang", "&cB&6i&eg &cB&6a&en&cg");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        BigBangEffect bigBangEffect = new BigBangEffect(effectManager);
        Location location = victim.getLocation();

        bigBangEffect.setLocation(location);
        bigBangEffect.duration = 2500;
        bigBangEffect.start();
    }
}
