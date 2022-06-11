package net.vlands.effect;

import net.vlands.VLandsUtilities;
import net.vlands.data.player.PlayerData;
import net.vlands.effect.effects.*;
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
        addEffect(new Angry(plugin));
        addEffect(new BlackFriday(plugin));
        addEffect(new Blood(plugin));
        addEffect(new BloodHelix(plugin));
        addEffect(new Chess(plugin));
        addEffect(new Circle(plugin));
        addEffect(new Cloud(plugin));
        addEffect(new Coal(plugin));
        addEffect(new Coin(plugin));
        addEffect(new Cold(plugin));
        addEffect(new Cookie(plugin));
        addEffect(new Diamond(plugin));
        addEffect(new Disco(plugin));
        addEffect(new DNA(plugin));
        addEffect(new Dragon(plugin));
        addEffect(new Easter(plugin));
        addEffect(new Emerald(plugin));
        addEffect(new Enchanted(plugin));
        addEffect(new Ender(plugin));
        addEffect(new FathersDay(plugin));
        addEffect(new Firework(plugin));
        addEffect(new Fish(plugin));
        addEffect(new Flame(plugin));
        addEffect(new Flower(plugin));
        addEffect(new Gold(plugin));
        addEffect(new Halloween(plugin));
        addEffect(new Happy(plugin));
        addEffect(new Heart(plugin));
        addEffect(new Iron(plugin));
        addEffect(new Lava(plugin));
        addEffect(new Lightning(plugin));
        addEffect(new Meat(plugin));
        addEffect(new MothersDay(plugin));
        addEffect(new NewYear(plugin));
        addEffect(new Note(plugin));
        addEffect(new Nuke(plugin));
        addEffect(new Pastel(plugin));
        addEffect(new Potion(plugin));
        addEffect(new Rainbow(plugin));
        addEffect(new Smoke(plugin));
        addEffect(new Snow(plugin));
        addEffect(new StPatricksDay(plugin));
        addEffect(new Sun(plugin));
        addEffect(new ThanksGiving(plugin));
        addEffect(new Thunder(plugin));
        addEffect(new TNT(plugin));
        addEffect(new Tornado(plugin));
        addEffect(new Treasure(plugin));
        addEffect(new ValentinesDay(plugin));
        addEffect(new Vortex(plugin));
        addEffect(new Warm(plugin));
        addEffect(new Water(plugin));

        /* Untested Effects

        addEffect(new Arc(plugin));
        addEffect(new Atom(plugin));
        addEffect(new Ball(plugin));
        addEffect(new BigBang(plugin));
        addEffect(new Donut(plugin));
        addEffect(new Earth(plugin));
        addEffect(new Explosion(plugin));
        addEffect(new EZ(plugin));
        addEffect(new Fountain(plugin));
        addEffect(new GG(plugin));
        addEffect(new Helix(plugin));
        addEffect(new L(plugin));
        addEffect(new Music(plugin));
        addEffect(new Pyramid(plugin));
        addEffect(new Rain(plugin));
        addEffect(new Shield(plugin));
        addEffect(new Sphere(plugin));
        addEffect(new Star(plugin));
        addEffect(new Warp(plugin));
        addEffect(new Wave(plugin));
        addEffect(new WP(plugin));

        */
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
        if (effect != null) return effect;
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
