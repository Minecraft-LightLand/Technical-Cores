package cn.nulladev.technicalcores.item;

import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class WorldInteractionWand extends Item implements IContentedItem {

    public WorldInteractionWand(Properties props) {
        super(props.stacksTo(1));
    }

    public int getTotalCooldown(ItemStack stack) {
        if (IContentedItem.hasContent(stack)) {
            ItemStack content = IContentedItem.readTagContent(stack);
            if (content.getItem() instanceof BaseCore core) {
                return core.getTotalCooldown(content);
            }
        }
        return -1;
    }

    public int getCurrentCooldown(ItemStack stack) {
        if (IContentedItem.hasContent(stack)) {
            ItemStack content = IContentedItem.readTagContent(stack);
            return ICooldownItem.readTagCooldown(content);
        }
        return -1;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (this.getCurrentCooldown(stack) > 0) {
            ItemStack content = IContentedItem.readTagContent(stack);
            ICooldownItem.writeTagCooldown(content, ICooldownItem.readTagCooldown(content) - 1);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getTotalCooldown(stack) > 0 && getCurrentCooldown(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13F - 13F * getCurrentCooldown(stack) / getTotalCooldown(stack));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, 1F * (getTotalCooldown(stack) - getCurrentCooldown(stack)) / getTotalCooldown(stack));
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (IContentedItem.hasContent(stack)) {
            MutableComponent component = super.getName(stack).plainCopy();
            component.append("(");
            component.append(IContentedItem.readTagContent(stack).getDisplayName().plainCopy().withStyle(ChatFormatting.GREEN));
            component.append(")");
            return component;
        } else {
            return super.getName(stack);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        ItemStack wand = ctx.getItemInHand();
        if (!IContentedItem.hasContent(wand)) {
            return InteractionResult.PASS;
        }

        ItemStack content = IContentedItem.readTagContent(wand);
        if (content.getItem() instanceof BaseCore core) {
            if (!core.hasInteraction(content)) {
                return InteractionResult.PASS;
            }

            if (ICooldownItem.readTagCooldown(content) > 0 && ctx.getPlayer() != null && !ctx.getPlayer().isCreative()) {
                return InteractionResult.PASS;
            }

            if (core instanceof IWandInteraction wi) {
                InteractionResult result = wi.wandUseOn(ctx);
                if (result != InteractionResult.PASS)
                    ICooldownItem.writeTagCooldown(content, core.totalCooldown);
                return result;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack wand = player.getItemInHand(hand);
        if (!IContentedItem.hasContent(wand)) {
            return InteractionResultHolder.pass(wand);
        }

        ItemStack content = IContentedItem.readTagContent(wand);
        if (content.getItem() instanceof BaseCore core) {
            if (!core.hasInteraction(content)) {
                return InteractionResultHolder.pass(wand);
            }

            if (ICooldownItem.readTagCooldown(content) > 0 && !player.isCreative()) {
                return InteractionResultHolder.pass(wand);
            }

            if (core instanceof IWandInteraction wi) {
                InteractionResultHolder<ItemStack> result = wi.wandUse(level, player, hand);
                if (result.getResult() != InteractionResult.PASS)
                    ICooldownItem.writeTagCooldown(content, core.totalCooldown);
                return result;
            }
        }
        return InteractionResultHolder.pass(wand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        if (!IContentedItem.hasContent(stack))
            list.add(Component.translatable("technicalcores.misc.miss_core").withStyle(ChatFormatting.RED));
        else if (getCurrentCooldown(stack) > 0)
            list.add(Component.translatable("technicalcores.misc.cd2", getCurrentCooldown(stack)).withStyle(ChatFormatting.RED));
        else
            list.add(Component.translatable("technicalcores.misc.cd1").withStyle(ChatFormatting.GREEN));
    }

}
