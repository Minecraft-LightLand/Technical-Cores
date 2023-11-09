package cn.nulladev.technicalcores.item.technicalcore.custom;

import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class BoneMealCore extends BaseCore implements IWandInteraction {


    public BoneMealCore(Properties props, int cooldown) {
        super(props, cooldown);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IWandInteraction.useBoneMeal(ctx);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }
}