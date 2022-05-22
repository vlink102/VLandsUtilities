package net.vlands.effect;

import net.vlands.VLandsUtilities;
import net.vlands.data.player.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EffectsManager {

    private VLandsUtilities plugin;
    private Map<String, Effect> effectsMap = new HashMap<>();

    private Effect defaultEffect = new NoEffect();

    public EffectsManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public void addEffect(Effect effect) {
        this.effectsMap.put(effect.getName(),effect);
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
        public void playEffect(Player player, Player target) {
            //do nothing
        }
    }

}
