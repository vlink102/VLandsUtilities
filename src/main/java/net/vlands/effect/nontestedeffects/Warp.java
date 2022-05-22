package net.vlands.effect.nontestedeffects;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.WarpEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public class Warp extends Effect {

    private final VLandsUtilities plugin;

    public Warp(VLandsUtilities plugin) {
        super("warp", "&5W&2a&5r&2p");
        this.plugin = plugin;
    }

    @Override
    public void playEffect(Player killer, Player victim) {
        EffectManager effectManager = plugin.getComplexEffectsManager();
        WarpEffect warpEffect = new WarpEffect(effectManager);
        Location victimDeath = victim.getLocation();
        Location location = killer.getLocation();

        warpEffect.setLocation(location);
        warpEffect.duration = 1000;
        warpEffect.particle = Particle.PORTAL;
        killer.getWorld().playSound(killer.getLocation(), Sound.PORTAL_TRAVEL, 1.0F, 1.0F);
        warpEffect.start();

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                killer.teleport(victimDeath);
                killer.getWorld().playSound(killer.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                warpEffect.setLocation(killer.getLocation());
                warpEffect.start();
            }
        }.runTaskLater(plugin, 20L);
    }
}
