package cn.nulladev.technicalcores.crafting;

import cn.nulladev.technicalcores.client.CrystalMenu;
import dev.xkmc.l2library.serial.recipe.BaseRecipe;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.HashMap;

@SerialClass
public class AbstractCrystalRecipe<Rec extends AbstractCrystalRecipe<Rec>>
		extends BaseRecipe<Rec, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer> {

	@SerialClass.SerialField
	public String[] pattern;
	@SerialClass.SerialField
	public HashMap<String, Ingredient> key;
	@SerialClass.SerialField
	public ItemStack result;

	public AbstractCrystalRecipe(ResourceLocation id, RecType<Rec, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer> fac) {
		super(id, fac);
	}

	@Override
	public boolean matches(CrystalMenu.CrystalContainer inv, Level world) {
		if (inv.getWidth() != pattern.length) {
			return false;
		}
		for (int i = 0; i < inv.getWidth(); i++) {
			for (int j = 0; j < inv.getWidth(); j++) {
				Ingredient ing = key.get("" + pattern[i].charAt(j));
				if (ing == null) {
					ing = Ingredient.EMPTY;
				}
				if (!ing.test(inv.getItem(i * inv.getWidth() + j))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public ItemStack assemble(CrystalMenu.CrystalContainer inv, RegistryAccess access) {
		return result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int r, int c) {
		return false;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess access) {
		return result;
	}

}
