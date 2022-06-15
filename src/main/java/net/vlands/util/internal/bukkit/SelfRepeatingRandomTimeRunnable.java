package net.vlands.util.internal.bukkit;

import lombok.AllArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

// lol thanks conclure, probably never going to use this
@AllArgsConstructor
public class SelfRepeatingRandomTimeRunnable extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final Runnable wrappedRunnable;
    private final int minTime;
    private final int maxTime;

    @Override
    public void run() {
        wrappedRunnable.run();
        int delay = ThreadLocalRandom.current().nextInt(minTime, maxTime);
        new SelfRepeatingRandomTimeRunnable(plugin, wrappedRunnable, minTime, maxTime).runTaskLater(plugin, delay);
    }

}