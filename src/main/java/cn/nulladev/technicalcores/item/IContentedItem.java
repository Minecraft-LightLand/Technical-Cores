package cn.nulladev.technicalcores.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;

public interface IContentedItem {
    String TAG_CONTENT = "tag_tc_content";

    static ItemStack readTagContent(ItemStack item) {
        if (item.getOrCreateTag().contains(TAG_CONTENT)) {
            return ItemStack.of(item.getOrCreateTag().getCompound(TAG_CONTENT));
        } else {
            return ItemStack.EMPTY;
        }
    }

    static void writeTagContent(ItemStack item, ItemStack content) {
        CompoundTag tag = new CompoundTag();
        content.save(tag);
        item.getOrCreateTag().put(TAG_CONTENT, tag);
    }

    static boolean hasContent(ItemStack item) {
        return !(readTagContent(item).isEmpty());
    }

    static Optional<ItemStack> removeContent(ItemStack item) {
        if (!hasContent(item)) {
            return Optional.empty();
        } else {
            var content = readTagContent(item);
            writeTagContent(item, ItemStack.EMPTY);
            return Optional.of(content);
        }
    }

    static Block getBlock(ItemStack stack) {
        if (readTagContent(stack).getItem() instanceof BlockItem) {
            return ((BlockItem) (readTagContent(stack).getItem())).getBlock();
        } else {
            return Blocks.AIR;
        }
    }
}
