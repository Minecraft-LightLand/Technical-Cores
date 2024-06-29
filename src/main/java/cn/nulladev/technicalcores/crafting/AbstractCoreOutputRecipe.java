package cn.nulladev.technicalcores.crafting;

import dev.xkmc.l2library.serial.recipe.BaseRecipe;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashMap;

@SerialClass
public class AbstractCoreOutputRecipe<Rec extends AbstractCoreOutputRecipe<Rec>>
		extends BaseRecipe<Rec, AbstractCoreOutputRecipe<?>, SimpleContainer> {

	@SerialClass.SerialField
	public ItemStack input;
	@SerialClass.SerialField
	public HashMap<ItemStack, Double> outputs;

	public AbstractCoreOutputRecipe(ResourceLocation id, RecType<Rec, AbstractCoreOutputRecipe<?>, SimpleContainer> fac) {
		super(id, fac);
	}

	@Override
	public boolean matches(SimpleContainer inv, Level level) {
		return inv.getItem(0).getItem() == input.getItem();
	}

	@Override
	public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess access) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int i, int i1) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess access) {
		return ItemStack.EMPTY;
	}
}
