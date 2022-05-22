package net.vlands.death;

import net.vlands.VLandsUtilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathManager implements Listener {
    private final VLandsUtilities plugin;

    public DeathManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

    }
}
