package cn.nulladev.technicalcores.crafting;

import cn.nulladev.technicalcores.core.TCRecipes;
import net.minecraft.world.item.ItemStack;

public class DefaultCoreOutputRecipeBuilder extends AbstractCoreOutputRecipeBuilder<DefaultCoreOutputRecipeBuilder, DefaultCoreOutputRecipe> {
    public DefaultCoreOutputRecipeBuilder(ItemStack input) {
        super(TCRecipes.RS_CORE_OUTPUT_DEFAULT.get(), input);
    }
}
