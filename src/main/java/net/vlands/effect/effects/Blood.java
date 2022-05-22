package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Blood extends Effect {

    public Blood(VLandsUtilities plugin) {
        super("blood", "&cBlood");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.REDSTONE, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
        ParticleEffect.BLOCK_DUST.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.2F, 0.2F, 0.2F, 0.1F, 5, location, 20.0D);
    }
}
