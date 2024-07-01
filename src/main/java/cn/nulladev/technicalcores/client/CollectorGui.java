package cn.nulladev.technicalcores.client;

import dev.xkmc.l2library.base.menu.base.BaseContainerScreen;
import dev.xkmc.l2library.base.menu.base.SpriteManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CollectorGui extends BaseContainerScreen<CollectorMenu> {

	public final CollectorMenu menu;

	public CollectorGui(CollectorMenu menu, Inventory plInv, Component title) {
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

}
