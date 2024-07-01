package cn.nulladev.technicalcores.crafting;

import cn.nulladev.technicalcores.core.TCRecipes;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.resources.ResourceLocation;

@SerialClass
public class DefaultCoreOutputRecipe extends AbstractCoreOutputRecipe<DefaultCoreOutputRecipe> {

	public DefaultCoreOutputRecipe(ResourceLocation id) {
		super(id, TCRecipes.RS_CORE_OUTPUT_DEFAULT.get());
	}

}
