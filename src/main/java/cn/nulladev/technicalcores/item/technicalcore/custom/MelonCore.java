package cn.nulladev.technicalcores.item.technicalcore.custom;

import cn.nulladev.technicalcores.item.IContentedItem;
import cn.nulladev.technicalcores.item.technicalcore.ContentedCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MelonCore extends ContentedCore implements IWandInteraction {
    public static final List<Item> melons = List.of(
            Items.PUMPKIN_SEEDS,
            Items.MELON_SEEDS
    );

    public MelonCore(Properties props, int cooldown) {
        super(props, cooldown, melons);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return IContentedItem.hasContent(stack);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = IContentedItem.readTagContent(ctx.getItemInHand());
        if (IContentedItem.readTagContent(core).getItem() == Items.PUMPKIN_SEEDS) {
            return IWandInteraction.placeBlock(new BlockPlaceContext(ctx), Blocks.PUMPKIN);
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        ItemStack core = IContentedItem.readTagContent(player.getItemInHand(hand));
        if (IContentedItem.readTagContent(core).getItem() != Items.MELON_SEEDS) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        int num = player.getRandom().nextIntBetweenInclusive(3, 7);
        boolean flag = false;
        while (num-->0 && player.canEat(false)) {
            flag = true;
            player.getFoodData().eat(2, 0.3F);
        }
        if (flag) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }
}
