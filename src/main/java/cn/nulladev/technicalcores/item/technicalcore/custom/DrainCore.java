package cn.nulladev.technicalcores.item.technicalcore.custom;

import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
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
import org.jetbrains.annotations.NotNull;

public class DrainCore extends BaseCore implements IWandInteraction {
	public DrainCore(Properties props, int cooldown) {
		super(props, cooldown);
	}

	@Override
	public boolean hasInteraction(@NotNull ItemStack stack) {
		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
		boolean flag = removeWaterBreadthFirstSearch(level, player.getOnPos());
		if (flag)
			return InteractionResultHolder.success(player.getItemInHand(hand));
		else
			return InteractionResultHolder.pass(player.getItemInHand(hand));
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
		BlockState spongeState = p_56808_.getBlockState(p_56809_);
		return BlockPos.breadthFirstTraversal(p_56809_, 6, 65, (p_277519_, p_277492_) -> {
			for (Direction direction : Direction.values()) {
				p_277492_.accept(p_277519_.relative(direction));
			}

		}, (p_279054_) -> {
			if (p_279054_.equals(p_56809_)) {
				return true;
			} else {
				BlockState blockstate = p_56808_.getBlockState(p_279054_);
				FluidState fluidstate = p_56808_.getFluidState(p_279054_);
				if (!spongeState.canBeHydrated(p_56808_, p_56809_, fluidstate, p_279054_)) {
					return false;
				} else {
					Block block = blockstate.getBlock();
					if (block instanceof BucketPickup) {
						BucketPickup bucketpickup = (BucketPickup) block;
						if (!bucketpickup.pickupBlock(p_56808_, p_279054_, blockstate).isEmpty()) {
							return true;
						}
					}

					if (blockstate.getBlock() instanceof LiquidBlock) {
						p_56808_.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
					} else {
						if (!blockstate.is(Blocks.KELP) && !blockstate.is(Blocks.KELP_PLANT) && !blockstate.is(Blocks.SEAGRASS) && !blockstate.is(Blocks.TALL_SEAGRASS)) {
							return false;
						}

						BlockEntity blockentity = blockstate.hasBlockEntity() ? p_56808_.getBlockEntity(p_279054_) : null;
						Block.dropResources(blockstate, p_56808_, p_279054_, blockentity);
						p_56808_.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
					}

					return true;
				}
			}
		}) > 1;
	}
}
