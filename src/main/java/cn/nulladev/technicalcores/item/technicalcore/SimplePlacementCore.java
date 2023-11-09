package cn.nulladev.technicalcores.item.technicalcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class SimplePlacementCore extends BaseCore implements IWandInteraction {
    private final Block block;

    public SimplePlacementCore(Properties props, int cooldown, Block block) {
        super(props, cooldown);
        this.block = block;
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IWandInteraction.placeBlock(new BlockPlaceContext(ctx), block);
    }
}
