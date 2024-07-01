package cn.nulladev.technicalcores.crafting;

import cn.nulladev.technicalcores.core.TCRecipes;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.resources.ResourceLocation;

@SerialClass
public class DefaultCrystalRecipe extends AbstractCrystalRecipe<DefaultCrystalRecipe> {

	public DefaultCrystalRecipe(ResourceLocation id) {
		super(id, TCRecipes.RS_CRYSTAL_DEFAULT.get());
	}

}
