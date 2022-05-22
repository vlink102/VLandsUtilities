package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Note extends Effect {

    public Note(VLandsUtilities plugin) {
        super("note", "&aNote");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.NOTE.display(0.5F, 0.5F, 0.5F, 1.0F, 12, location, 20.0D);
    }
}
