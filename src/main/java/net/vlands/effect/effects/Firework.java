package net.vlands.effect.effects;

import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Firework extends Effect {

    public Firework(VLandsUtilities plugin) {
        super("firework", "&cF&7i&cr&7e&cw&7o&cr&7k");
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        location.getWorld().spawnEntity(location, EntityType.FIREWORK);}
}
