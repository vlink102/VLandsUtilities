package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public class BloodHelix extends Effect {

    private final VLandsUtilities plugin;

    public BloodHelix(VLandsUtilities plugin) {
        super("&4Blood Helix", "bloodhelix");
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        BukkitTask task = new BukkitRunnable() {
            Double phi = 0.0D;
            final Location location = victim.getLocation();
            public void run() {
                phi += 0.39269908169872414D;
                for (double d = 0.0D; d <= 6.283185307179586D; d += 0.19634954084936207D) {
                    for (double d2 = 0.0D; d2 <= 1.0D; d2++) {
                        double d3 = 0.4D * (6.283185307179586D - d) * 0.5D * Math.cos(d + phi + d2 * Math.PI);
                        double d4 = 0.5D * d;
                        double d5 = 0.4D * (6.283185307179586D - d) * 0.5D * Math.sin(d + phi + d2 * Math.PI);
                        location.add(d3, d4, d5);
                        ParticleEffect.REDSTONE.display(0.0F, 0.0F, 0.0F, 0.0F, 1, location, 100.0D);
                        location.subtract(d3, d4, d5);
                    }
                }
                if (this.phi > 9.42477796076938D) cancel();
            }
        }.runTaskTimer(plugin,0L, 3L);
    }
}
