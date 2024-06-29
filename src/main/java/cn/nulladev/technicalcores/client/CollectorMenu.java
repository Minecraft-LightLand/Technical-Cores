package cn.nulladev.technicalcores.client;

import cn.nulladev.technicalcores.core.TechnicalCores;
import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import dev.xkmc.l2library.base.menu.base.BaseContainerMenu;
import dev.xkmc.l2library.base.menu.base.SpriteManager;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Predicate;

public class CollectorMenu extends BaseContainerMenu<CollectorMenu> {

	public static final int SIZE = 28;

	public static final SpriteManager CORE_MACHINE = new SpriteManager(TechnicalCores.MODID, "core_machine");

	public static CollectorMenu fromNetwork(MenuType<CollectorMenu> type, int windowId, Inventory inv) {
		return new CollectorMenu(type, windowId, inv, new SimpleContainer(CollectorMenu.SIZE), e -> true);
	}

	private final Predicate<Player> sup;

	public CollectorMenu(MenuType<CollectorMenu> type, int windowId, Inventory inventory, SimpleContainer cont, Predicate<Player> sup) {
		super(type, windowId, inventory, CORE_MACHINE, menu -> cont, false);
		this.sup = sup;
		this.addSlot("core", stack -> stack.getItem() instanceof BaseCore);
		this.addSlot("grid", stack -> false);
	}

	@Override
	public boolean stillValid(Player player) {
		return sup.test(player);
	}

}
