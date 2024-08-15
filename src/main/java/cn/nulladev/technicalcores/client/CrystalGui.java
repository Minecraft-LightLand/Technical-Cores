package cn.nulladev.technicalcores.client;

import dev.xkmc.l2library.base.menu.base.BaseContainerScreen;
import dev.xkmc.l2library.base.menu.base.SpriteManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

public class CrystalGui extends BaseContainerScreen<CrystalMenu> {
	public final CrystalMenu menu;

	public CrystalGui(CrystalMenu menu, Inventory plInv, Component title) {
		super(menu, plInv, title);
		this.menu = menu;
	}

	@Override
	public void render(GuiGraphics p_98418_, int p_98419_, int p_98420_, float p_98421_) {
		this.renderBackground(p_98418_);
		super.render(p_98418_, p_98419_, p_98420_, p_98421_);
		this.renderTooltip(p_98418_, p_98419_, p_98420_);
	}

	@Override
	protected void renderBg(GuiGraphics matrix, float p_97788_, int p_97789_, int p_97790_) {
		SpriteManager sm = menu.sprite;
		var sr = sm.get().getRenderer(this);
		sr.start(matrix);
	}

	@Override
	protected void slotClicked(Slot slot, int index, int key, ClickType type) {
		if (slot != null && slot.index == 36 + menu.getSize() * menu.getSize() && slot.hasItem()) {
			this.onClose();
		} else {
			super.slotClicked(slot, index, key, type);
		}
	}
}
