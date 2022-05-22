package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public class Nuke extends Effect {

    private final VLandsUtilities plugin;

    public Nuke(VLandsUtilities plugin) {
        super("nuke", "&4Nuke");
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation();

        BukkitTask task = new BukkitRunnable() {
            Double t = 0.7853981633974483D;

            @Override
            public void run() {
                this.t += 0.3141592653589793D;
                for (double d1 = 0.0D; d1 <= 6.283185307179586D; d1 += 0.09817477042468103D) {
                    double d2 = this.t * Math.cos(d1);
                    double d3 = 2.0D * Math.exp(-0.1D * this.t) * Math.sin(this.t) + 1.5D;
                    double d4 = this.t * Math.sin(d1);
                    location.add(d2, d3, d4);
                    ParticleEffect.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.0F, 1, location, 100.0D);
                    location.subtract(d2, d3, d4);
                    d2 = this.t * Math.cos(d1 += 0.04908738521234052D);
                    d3 = 2.0D * Math.exp(-0.1D * this.t) * Math.sin(this.t) + 1.5D;
                    d4 = this.t * Math.sin(d1);
                    location.add(d2, d3, d4);
                    ParticleEffect.FLAME.display(0.0F, 0.0F, 0.0F, 0.0F, 1, location, 100.0D);
                    location.subtract(d2, d3, d4);
                }
                if (this.t > 10.0D) cancel();
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}
