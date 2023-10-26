package cn.nulladev.technicalcores.item;

import cn.nulladev.technicalcores.core.TCItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class InfiniteFuel extends Item {
    public InfiniteFuel(Properties props) {
        super(props.stacksTo(1));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == TCItems.INFINITE_FUEL.get())
            event.setBurnTime(67);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack itemStack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(TCItems.INFINITE_FUEL.get());
    }

}
