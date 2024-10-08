package cn.nulladev.technicalcores.item;

import cn.nulladev.technicalcores.client.CrystalMenu;
import cn.nulladev.technicalcores.core.TCBlocks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class SpaceCrystal extends Item {

	public final int size;

	public SpaceCrystal(Properties props, int size) {
		super(props);
		this.size = size;
	}

	public static int getSize(ItemStack stack) {
		if (stack.getItem() instanceof SpaceCrystal)
			return ((SpaceCrystal) stack.getItem()).size;
		else
			return 0;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (!world.isClientSide) {
			ItemStack stack = player.getItemInHand(hand);
			MenuProvider container = new SimpleMenuProvider((windowId, playerInventory, playerEntity) ->
					new CrystalMenu(TCBlocks.MT_CRYSTAL.get(), windowId, playerInventory, stack), stack.getDisplayName());
			NetworkHooks.openScreen((ServerPlayer) player, container, buf -> buf.writeBoolean(hand == InteractionHand.MAIN_HAND));
			//player.openMenu(container);
		}
		return InteractionResultHolder.success(player.getItemInHand(hand));
	}

}
