package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

@SuppressWarnings("unused")
public class Circle extends Effect {

    private final VLandsUtilities plugin;

    public Circle(VLandsUtilities plugin) {
        super("circle", "&2Circle");
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation();

        HashMap<Player, Integer> rRadius = new HashMap<>();
        rRadius.put(victim, 0);

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (rRadius.get(victim) != 5) {
                    Location location = victim.getEyeLocation();
                    ParticleEffect.CLOUD.display(0.3F, 0.3F, 0.3F, 0.1F, 100, victim.getLocation(), 100.0D);
                    ParticleEffect.SMOKE_NORMAL.display(0.3F, 0.3F, 0.3F, 0.1F, 100, victim.getLocation(), 100.0D);
                    int n2 = 10 * rRadius.get(victim);
                    for (int n3 = 0; n3 < n2; n3++) {
                        double d = 6.283185307179586D * n3 / n2;
                        double d2 = Math.cos(d) * rRadius.get(victim);
                        double d3 = Math.sin(d) * rRadius.get(victim);
                        location.add(d2, 0.0D, d3);
                        Location location4 = new Location(victim.getWorld(), victim.getLocation().getX(), victim.getLocation().getY(), victim.getLocation().getZ());
                        Location location5 = new Location(victim.getWorld(), victim.getLocation().getX(), victim.getLocation().getY() + 1.0D, victim.getLocation().getZ());
                        Location location6 = new Location(victim.getWorld(), victim.getLocation().getX(), victim.getLocation().getY() + 2.0D, victim.getLocation().getZ());
                        location4.add(d2, 0.0D, d3);
                        location5.add(d2, 0.0D, d3);
                        location6.add(d2, 0.0D, d3);
                        location.subtract(d2, 0.0D, d3);
                        location4.subtract(d2, 0.0D, d3);
                        location5.subtract(d2, 0.0D, d3);
                        location6.subtract(d2, 0.0D, d3);
                    }
                    rRadius.put(victim, rRadius.get(victim) + 1);
                } else {
                    rRadius.put(victim, 0);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }
}
