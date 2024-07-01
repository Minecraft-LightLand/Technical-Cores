package cn.nulladev.technicalcores.crafting;

import cn.nulladev.technicalcores.item.IContentedItem;
import cn.nulladev.technicalcores.item.technicalcore.ContentedCore;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class CoreRemoveContentRecipe extends CustomRecipe {

	public static final RecipeSerializer<CoreRemoveContentRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(CoreRemoveContentRecipe::new);

	public CoreRemoveContentRecipe(ResourceLocation rl, CraftingBookCategory category) {
		super(rl, category);
	}

	@Override
	public boolean matches(CraftingContainer inv, Level level) {
		boolean foundCore = false;

		for (int i = 0; i < inv.getMaxStackSize(); i++) {
			ItemStack stack = inv.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof ContentedCore && IContentedItem.hasContent(stack)) {
					if (foundCore)
						return false;
					else
						foundCore = true;
				} else {
					return false;
				}
			}
		}
		return foundCore;
	}

	@Override
	public ItemStack assemble(CraftingContainer inv, RegistryAccess access) {
		ItemStack core = ItemStack.EMPTY;
		for (int i = 0; i < inv.getMaxStackSize(); i++) {
			ItemStack stack = inv.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof ContentedCore) {
					core = stack;
				}
			}
		}
		ItemStack core_empty = core.copy();
		core_empty.setCount(1);
		IContentedItem.writeTagContent(core_empty, ItemStack.EMPTY);

		return core_empty;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(@Nonnull CraftingContainer inv) {
		NonNullList<ItemStack> ret = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
		for (int i = 0; i < ret.size(); i++) {
			ItemStack stack = inv.getItem(i);
			if (stack.getItem() instanceof ContentedCore) {
				ret.set(i, IContentedItem.readTagContent(stack));
			}
		}
		return ret;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

}
