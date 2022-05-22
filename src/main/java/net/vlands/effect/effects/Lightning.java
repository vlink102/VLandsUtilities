package net.vlands.effect.effects;

import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Lightning extends Effect {

    public Lightning(VLandsUtilities plugin) {
        super("lightning", "&eLightning");
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation();
        location.getWorld().strikeLightningEffect(location);
    }
}
