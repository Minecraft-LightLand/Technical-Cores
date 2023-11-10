package cn.nulladev.technicalcores.item.technicalcore;

import cn.nulladev.technicalcores.item.ICooldownItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseCore extends Item implements ICooldownItem {

    public int totalCooldown;

    public BaseCore(Properties props, int cooldown) {
        super(props.stacksTo(1));
        this.setTotalCooldown(cooldown);
    }

    public void setTotalCooldown(int cd) {
        this.totalCooldown = cd;
    }

    @Override
    public int getTotalCooldown(ItemStack stack) {
        return this.totalCooldown;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        int currentCoolingDown = ICooldownItem.readTagCooldown(stack);
        if (currentCoolingDown > 0) {
            ICooldownItem.writeTagCooldown(stack, currentCoolingDown - 1);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return ICooldownItem.readTagCooldown(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13F - 13F * ICooldownItem.readTagCooldown(stack) / totalCooldown);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, (this.totalCooldown - ICooldownItem.readTagCooldown(stack)) / (float) totalCooldown);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("technicalcores.misc.total_cd", totalCooldown));
        if (ICooldownItem.readTagCooldown(stack) > 0)
            list.add(Component.translatable("technicalcores.misc.cd2", ICooldownItem.readTagCooldown(stack)).withStyle(ChatFormatting.RED));
        else
            list.add(Component.translatable("technicalcores.misc.cd1").withStyle(ChatFormatting.GREEN));
    }

    public abstract boolean hasInteraction(@NotNull ItemStack stack);

}
