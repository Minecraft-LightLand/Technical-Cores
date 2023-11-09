package cn.nulladev.technicalcores.item.technicalcore;

import cn.nulladev.technicalcores.item.IContentedItem;
import cn.nulladev.technicalcores.item.ICooldownItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ContentedCore extends BaseCore implements IContentedItem {
    protected final List<Item> validItems;

    public ContentedCore(Properties props, int cooldown, List<Item> validItems) {
        super(props, cooldown);
        this.validItems = validItems;
    }

    public boolean isContentValid(ItemStack content) {
        return validItems.contains(content.getItem());
    }

    @Override
    public Component getName(ItemStack stack) {
        if (IContentedItem.hasContent(stack)) {
            MutableComponent component = super.getName(stack).plainCopy();
            component.append("(");
            component.append(IContentedItem.readTagContent(stack).getDisplayName().plainCopy().withStyle(ChatFormatting.YELLOW));
            component.append(")");
            return component;
        } else {
            return super.getName(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("technicalcores.misc.total_cd", totalCooldown));
        if (!IContentedItem.hasContent(stack))
            list.add(Component.translatable("technicalcores.misc.miss_content").withStyle(ChatFormatting.RED));
        else if (ICooldownItem.readTagCooldown(stack) > 0)
            list.add(Component.translatable("technicalcores.misc.cd2", ICooldownItem.readTagCooldown(stack)).withStyle(ChatFormatting.RED));
        else
            list.add(Component.translatable("technicalcores.misc.cd1").withStyle(ChatFormatting.GREEN));
    }
}
