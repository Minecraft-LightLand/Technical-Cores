package cn.nulladev.technicalcores.client;

import cn.nulladev.technicalcores.core.TCRegistry;
import cn.nulladev.technicalcores.core.TechnicalCores;
import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import com.lcy0x1.base.menu.BaseContainerMenu;
import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class CollectorMenu extends BaseContainerMenu<CollectorMenu> {

    public static final int SIZE = 28;

    public static final SpriteManager CORE_MACHINE = new SpriteManager(TechnicalCores.MODID, "core_machine");

    public CollectorMenu(int windowId, Inventory inventory, BlockPos pos, Level level) {
        super(TCRegistry.MT_COLLECTOR.get(), windowId, inventory, CORE_MACHINE, menu -> level.getBlockEntity(pos, TCRegistry.BE_COLLECTOR.get()).get().getContainer(), false);
        this.addSlot("core", stack -> stack.getItem() instanceof BaseCore);
        this.addSlot("grid", stack -> false);
    }

}
