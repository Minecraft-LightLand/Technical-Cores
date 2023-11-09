package cn.nulladev.technicalcores.item.technicalcore.custom;

import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class WorldEaterCore extends BaseCore implements IWandInteraction {
    public WorldEaterCore(Properties props, int cooldown) {
        super(props, cooldown);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        int x = (int) player.getX() | 0xF;
        int z = (int) player.getZ() | 0xF;
        for (int i = 1; i <= 16; i++) {
            for (int j = -64; j <= 300; j++) {
                for (int k = 1; k <= 16; k++) {
                    BlockPos blockpos = new BlockPos(x-i, j, z-k);
                    if (level.getBlockState(blockpos).getBlock().getExplosionResistance() <= 15) {
                        level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
