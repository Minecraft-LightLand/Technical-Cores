package cn.nulladev.technicalcores.block;

import cn.nulladev.technicalcores.core.TCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class AutomaticCollector extends BaseEntityBlock {

	public AutomaticCollector(Properties props) {
		super(props);
	}

	public RenderShape getRenderShape(BlockState p_60550_) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
		return new CollectorBE(TCBlocks.BE_COLLECTOR.get(), pos, blockState);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (!level.isClientSide()) {
			return createTickerHelper(type, TCBlocks.BE_COLLECTOR.get(), CollectorBE.ticker);
		}
		return null;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
		if (!level.isClientSide) {
			if (level.getBlockEntity(pos) instanceof CollectorBE) {
				MenuProvider menu = (MenuProvider) level.getBlockEntity(pos);
				NetworkHooks.openScreen((ServerPlayer) player, menu, buf -> buf.writeBlockPos(pos));
				//player.openMenu((MenuProvider)level.getBlockEntity(pos));
				return InteractionResult.CONSUME;
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void onRemove(BlockState p_51538_, Level p_51539_, BlockPos p_51540_, BlockState p_51541_, boolean p_51542_) {
		if (!p_51538_.is(p_51541_.getBlock())) {
			BlockEntity blockentity = p_51539_.getBlockEntity(p_51540_);
			if (blockentity instanceof Container) {
				Containers.dropContents(p_51539_, p_51540_, (Container) blockentity);
				p_51539_.updateNeighbourForOutputSignal(p_51540_, this);
			}
			super.onRemove(p_51538_, p_51539_, p_51540_, p_51541_, p_51542_);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_49058_) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState p_49065_, Level p_49066_, BlockPos p_49067_) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_49066_.getBlockEntity(p_49067_));
	}


}
