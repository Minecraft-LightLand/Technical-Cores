package cn.nulladev.technicalcores.item.technicalcore;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public interface IWandInteraction {
    default InteractionResult wandUseOn(UseOnContext ctx) {
        return InteractionResult.PASS;
    }

    default InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    static InteractionResult placeBlock(BlockPlaceContext ctx, Block toPlace) {
        if (!ctx.canPlace()) {
            return InteractionResult.FAIL;
        } else {
            var blockStateToPlace = toPlace.getStateForPlacement(ctx);
            var blockPos = ctx.getClickedPos();
            var level = ctx.getLevel();
            var player = ctx.getPlayer();
            var itemStack = ctx.getItemInHand();

            if (blockStateToPlace == null) {
                return InteractionResult.FAIL;
            } else if (!blockStateToPlace.canSurvive(level, blockPos)) {
                return InteractionResult.FAIL;
            } else if (!level.setBlockAndUpdate(blockPos, blockStateToPlace)) {
                return InteractionResult.FAIL;
            } else {
                var blockStatePlaced = level.getBlockState(blockPos);
                if (blockStatePlaced.is(toPlace)) {
                    blockStatePlaced.getBlock().setPlacedBy(level, blockPos, blockStatePlaced, player, itemStack);
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockPos, itemStack);
                    }
                }

                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);
                var soundtype = blockStatePlaced.getSoundType(level, blockPos, ctx.getPlayer());
                level.playSound(player, blockPos, blockStatePlaced.getSoundType(level, blockPos, ctx.getPlayer()).getPlaceSound(), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
    }

    static InteractionResult replaceBlock(UseOnContext ctx, Block toBeReplaced, Block toReplace) {
        var level = ctx.getLevel();
        var blockPos = ctx.getClickedPos().relative(ctx.getClickedFace(), -1);

        if (level.getBlockState(blockPos).getBlock() == toBeReplaced) {
            level.setBlockAndUpdate(blockPos, toReplace.defaultBlockState());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    static InteractionResult removeBlock(UseOnContext ctx, Block toBeRemoved) {
        return replaceBlock(ctx, toBeRemoved, Blocks.AIR);
    }

    static InteractionResult useBoneMeal(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof BonemealableBlock bonemealableblock) {
            if (bonemealableblock.isValidBonemealTarget(level, blockpos, blockstate, level.isClientSide)) {
                if (level instanceof ServerLevel) {
                    if (bonemealableblock.isBonemealSuccess(level, level.random, blockpos, blockstate)) {
                        bonemealableblock.performBonemeal((ServerLevel) level, level.random, blockpos, blockstate);
                    }
                    level.levelEvent(1505, blockpos, 0);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
