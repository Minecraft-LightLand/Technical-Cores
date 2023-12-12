package cn.nulladev.technicalcores.item.technicalcore.custom;

import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MilkCore extends BaseCore implements IWandInteraction {
    public MilkCore(Properties props, int cooldown) {
        super(props, cooldown);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        player.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
