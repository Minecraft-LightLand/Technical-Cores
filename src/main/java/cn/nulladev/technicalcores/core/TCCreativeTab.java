package cn.nulladev.technicalcores.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TCCreativeTab extends CreativeModeTab {

    public static final TCCreativeTab INSTANCE = new TCCreativeTab();

    public TCCreativeTab() {
        super(TechnicalCores.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(TCItems.WORLD_INTERACTION_WAND.get());
    }
}
