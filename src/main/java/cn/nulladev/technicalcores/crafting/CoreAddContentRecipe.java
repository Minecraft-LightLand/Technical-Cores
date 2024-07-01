package cn.nulladev.technicalcores.crafting;

import cn.nulladev.technicalcores.item.IContentedItem;
import cn.nulladev.technicalcores.item.technicalcore.ContentedCore;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;

public class CoreAddContentRecipe extends CustomRecipe {

	public static final RecipeSerializer<CoreAddContentRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(CoreAddContentRecipe::new);

	public CoreAddContentRecipe(ResourceLocation rl, CraftingBookCategory category) {
		super(rl, category);
	}

	@Override
	public boolean matches(CraftingContainer inv, Level level) {
		ContentedCore core = null;
		int coreIndex = -1;
		for (int i = 0; i < inv.getMaxStackSize(); i++) {
			ItemStack stack = inv.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof ContentedCore && !IContentedItem.hasContent(stack)) {
					if (core != null) {
						return false;
					} else {
						core = (ContentedCore) stack.getItem();
						coreIndex = i;
					}
				}
			}
		}
		if (core != null) {
			boolean foundContent = false;
			for (int i = 0; i < inv.getMaxStackSize(); i++) {
				if (i == coreIndex)
					continue;
				ItemStack stack = inv.getItem(i);
				if (!stack.isEmpty()) {
					if (foundContent) {
						return false;
					} else if (core.isContentValid(stack)) {
						foundContent = true;
					}
				}
			}
			return foundContent;
		} else {
			return false;
		}
	}

	@Override
	public ItemStack assemble(CraftingContainer inv, RegistryAccess access) {
		ItemStack core = ItemStack.EMPTY;
		ItemStack content = ItemStack.EMPTY;

		for (int i = 0; i < inv.getMaxStackSize(); i++) {
			ItemStack stack = inv.getItem(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() instanceof ContentedCore) {
					core = stack;
				} else {
					content = stack;
				}
			}
		}

		if (core.isEmpty() || content.isEmpty()) {
			return ItemStack.EMPTY;
		}

		ItemStack core_with_content = core.copy();
		ItemStack content_size_1 = content.copy();
		content_size_1.setCount(1);
		IContentedItem.writeTagContent(core_with_content, content_size_1);

		return core_with_content;
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
