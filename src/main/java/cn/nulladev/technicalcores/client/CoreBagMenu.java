package cn.nulladev.technicalcores.client;

import cn.nulladev.technicalcores.core.TechnicalCores;
import cn.nulladev.technicalcores.item.CoreStorageBag;
import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import dev.xkmc.l2library.base.menu.base.BaseContainerMenu;
import dev.xkmc.l2library.base.menu.base.SpriteManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class CoreBagMenu extends BaseContainerMenu<CoreBagMenu> {

	public static class CoreBagContainer extends BaseContainer<CoreBagMenu> {

		public CoreBagContainer(CoreBagMenu menu, ItemStack bag) {
			super(CoreStorageBag.SIZE, menu);
			ListTag list = CoreStorageBag.getListTag(bag);
			for (int i = 0; i < Math.min(CoreStorageBag.SIZE, list.size()); i++) {
				this.setItem(i, ItemStack.of(list.getCompound(i)));
			}
		}

		@Override
		public boolean canPlaceItem(int index, ItemStack stack) {
			return stack.getItem() instanceof BaseCore;
		}
	}

	public static final SpriteManager CORE_BAG = new SpriteManager(TechnicalCores.MODID, "core_bag");

	private final ItemStack bag;

	public static CoreBagMenu fromNetwork(MenuType<CoreBagMenu> type, int windowId, Inventory inv, FriendlyByteBuf buf) {
		InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		return new CoreBagMenu(type, windowId, inv, inv.player.getItemInHand(hand));
	}

	public CoreBagMenu(MenuType<CoreBagMenu> type, int windowId, Inventory inventory, ItemStack bag) {
		super(type, windowId, inventory, CORE_BAG, menu -> new CoreBagContainer(menu, bag), false);
		this.bag = bag;
		this.addSlot("grid", stack -> stack.getItem() instanceof BaseCore);
		if (!inventory.player.level().isClientSide()) {
			ListTag tag = CoreStorageBag.getListTag(bag);
			for (int i = 0; i < tag.size(); i++) {
				this.container.setItem(i, ItemStack.of((CompoundTag) tag.get(i)));
			}
		}
	}

	@Override
	protected boolean shouldLock(Inventory inv, int slot) {
		return slot == inv.selected && inv.getItem(slot).getItem() instanceof CoreStorageBag;
	}

	@Override
	public void removed(Player player) {
		if (!player.level().isClientSide) {
			ListTag list = new ListTag();
			for (int i = 0; i < this.container.getContainerSize(); i++) {
				list.add(i, this.container.getItem(i).save(new CompoundTag()));
			}
			CoreStorageBag.setListTag(this.bag, list);
		}
		super.removed(player);
	}
}
