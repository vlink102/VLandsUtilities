package net.vlands.effect;

import net.vlands.VLandsUtilities;
import net.vlands.data.player.PlayerData;
import net.vlands.effect.effects.Angry;
import net.vlands.util.ClassEnumerator;
import org.bukkit.entity.Player;

import java.util.*;

public class EffectsManager {

    private final VLandsUtilities plugin;
    private final Map<String, Effect> effectsMap = new HashMap<>();

    private final Effect defaultEffect = new NoEffect();

    public EffectsManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public void addEffect(Effect effect) {
        this.effectsMap.put(effect.getName(),effect);
    }

    public void registerAllEffects() {
        List<Class<?>> discoveredClasses = ClassEnumerator.getPackageClasses(Angry.class.getPackage());
        discoveredClasses.forEach(clazz -> {
            if (Effect.class.isAssignableFrom(clazz)) {
                try {
                    plugin.getEffectsManager().addEffect(((Effect) clazz.newInstance()));
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Set<Effect> getEffects() {
        return new HashSet<>(this.effectsMap.values());
    }

    public Effect getEffect(Player player){
        PlayerData playerData = this.plugin.getPlayerDataManager().getPlayerData(player);
        return this.getOrDefault(playerData.getKillEffect());
    }

    public Effect getOrDefault(String name) {
        Effect effect = this.effectsMap.get(name);
        if (effect != null)
            return effect;
        return defaultEffect;
    }

    private static class NoEffect extends Effect {

        public NoEffect() {
            super("none", "none");
        }

        @Override
        public void playEffect(Player killer, Player victim) {
            //do nothing
        }
    }

}
