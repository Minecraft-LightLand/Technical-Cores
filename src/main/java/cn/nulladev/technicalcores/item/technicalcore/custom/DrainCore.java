package cn.nulladev.technicalcores.item.technicalcore.custom;

import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

public class DrainCore extends BaseCore implements IWandInteraction {
    public DrainCore(Properties props, int cooldown) {
        super(props, cooldown);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        var level = ctx.getLevel();
        var blockPos = ctx.getClickedPos().relative(ctx.getClickedFace(), -1);
        boolean flag = removeWaterBreadthFirstSearch(level, blockPos);

        if (flag)
            return InteractionResult.SUCCESS;
        else
            return InteractionResult.PASS;
    }

    private boolean removeWaterBreadthFirstSearch(Level p_56808_, BlockPos p_56809_) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(p_56809_, 0));
        int i = 0;
        BlockState state = p_56808_.getBlockState(p_56809_);

        while(!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getA();
            int j = tuple.getB();

            for(Direction direction : Direction.values()) {
                BlockPos blockpos1 = blockpos.relative(direction);
                BlockState blockstate = p_56808_.getBlockState(blockpos1);
                FluidState fluidstate = p_56808_.getFluidState(blockpos1);
                Material material = blockstate.getMaterial();
                if (state.canBeHydrated(p_56808_, p_56809_, fluidstate, blockpos1)) {
                    if (blockstate.getBlock() instanceof BucketPickup && !((BucketPickup)blockstate.getBlock()).pickupBlock(p_56808_, blockpos1, blockstate).isEmpty()) {
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof LiquidBlock) {
                        p_56808_.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (material == Material.WATER_PLANT || material == Material.REPLACEABLE_WATER_PLANT) {
                        BlockEntity blockentity = blockstate.hasBlockEntity() ? p_56808_.getBlockEntity(blockpos1) : null;
                        Block.dropResources(blockstate, p_56808_, blockpos1, blockentity);
                        p_56808_.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    }
                }
            }

            if (i > 64) {
                break;
            }
        }

        return i > 0;
    }
}
