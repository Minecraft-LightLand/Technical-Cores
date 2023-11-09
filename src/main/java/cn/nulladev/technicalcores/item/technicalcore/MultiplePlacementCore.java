package cn.nulladev.technicalcores.item.technicalcore;

import cn.nulladev.technicalcores.item.IContentedItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultiplePlacementCore extends ContentedCore implements IWandInteraction {

    public MultiplePlacementCore(Properties props, int cooldown, List<Item> validItems) {
        super(props, cooldown, validItems);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return IContentedItem.hasContent(stack);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = IContentedItem.readTagContent(ctx.getItemInHand());
        if (IContentedItem.hasContent(core)) {
            Block b = IContentedItem.getBlock(core);
            return IWandInteraction.placeBlock(new BlockPlaceContext(ctx), b);
        } else {
            return InteractionResult.PASS;
        }
    }

    public static final List<Item> carpet_and_rail = List.of(
            Items.BLACK_CARPET,
            Items.BLUE_CARPET,
            Items.BROWN_CARPET,
            Items.CYAN_CARPET,
            Items.GRAY_CARPET,
            Items.GREEN_CARPET,
            Items.LIGHT_BLUE_CARPET,
            Items.LIGHT_GRAY_CARPET,
            Items.LIME_CARPET,
            Items.MAGENTA_CARPET,
            Items.ORANGE_CARPET,
            Items.PINK_CARPET,
            Items.PURPLE_CARPET,
            Items.RED_CARPET,
            Items.WHITE_CARPET,
            Items.YELLOW_CARPET,
            Items.RAIL,
            Items.ACTIVATOR_RAIL,
            Items.DETECTOR_RAIL,
            Items.POWERED_RAIL
    );

    public static final List<Item> falling_blocks = List.of(
            Items.BLACK_CONCRETE_POWDER,
            Items.BLUE_CONCRETE_POWDER,
            Items.BROWN_CONCRETE_POWDER,
            Items.CYAN_CONCRETE_POWDER,
            Items.GRAY_CONCRETE_POWDER,
            Items.GREEN_CONCRETE_POWDER,
            Items.LIGHT_BLUE_CONCRETE_POWDER,
            Items.LIGHT_GRAY_CONCRETE_POWDER,
            Items.LIME_CONCRETE_POWDER,
            Items.MAGENTA_CONCRETE_POWDER,
            Items.ORANGE_CONCRETE_POWDER,
            Items.PINK_CONCRETE_POWDER,
            Items.PURPLE_CONCRETE_POWDER,
            Items.RED_CONCRETE_POWDER,
            Items.WHITE_CONCRETE_POWDER,
            Items.YELLOW_CONCRETE_POWDER,
            Items.SAND,
            Items.RED_SAND,
            Items.GRAVEL,
            Items.DRAGON_EGG,
            Items.ANVIL,
            Items.CHIPPED_ANVIL,
            Items.DAMAGED_ANVIL
    );

}
