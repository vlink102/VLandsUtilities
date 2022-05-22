package net.vlands.effect.effects;

import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class NewYear extends Effect {

    public NewYear(VLandsUtilities plugin) {
        super("newyear", "&aNew Year");
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
    }
}
