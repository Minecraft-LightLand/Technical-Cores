package cn.nulladev.technicalcores.crafting;

import com.lcy0x1.base.BaseRecipe;
import com.lcy0x1.base.BaseRecipeBuilder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

public class AbstractCoreOutputRecipeBuilder<B extends AbstractCoreOutputRecipeBuilder<B, R>, R extends AbstractCoreOutputRecipe<R>>
        extends BaseRecipeBuilder<B, R, AbstractCoreOutputRecipe<?>, SimpleContainer> {
    public AbstractCoreOutputRecipeBuilder(BaseRecipe.RecType<R, AbstractCoreOutputRecipe<?>, SimpleContainer> type, ItemStack input) {
        super(type);
        recipe.input = input;
        recipe.outputs = new HashMap<>();
    }

    public B setInput(ItemStack input) {
        recipe.input = input;
        return getThis();
    }

    public B addOutput(ItemStack output, double possibility) {
        recipe.outputs.put(output, possibility);
        return getThis();
    }
}
