package cn.nulladev.technicalcores.item;

import net.minecraft.world.item.ItemStack;

public interface ICooldownItem {
    String TAG_COOLDOWN = "COOLDOWN";

    int getTotalCooldown(ItemStack item);

    static int readTagCooldown(ItemStack item) {
        return item.getOrCreateTag().getInt(TAG_COOLDOWN);
    }

    static void writeTagCooldown(ItemStack item, int newCooldown) {
        item.getOrCreateTag().putInt(TAG_COOLDOWN, newCooldown);
    }
}
