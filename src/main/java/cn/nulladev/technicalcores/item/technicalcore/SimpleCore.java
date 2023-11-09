package cn.nulladev.technicalcores.item.technicalcore;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SimpleCore extends BaseCore {

    public SimpleCore(Properties props, int cooldown) {
        super(props, cooldown);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return false;
    }
}
