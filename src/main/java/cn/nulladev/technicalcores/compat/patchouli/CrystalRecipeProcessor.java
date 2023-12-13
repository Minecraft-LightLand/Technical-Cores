package cn.nulladev.technicalcores.compat.patchouli;

import cn.nulladev.technicalcores.crafting.DefaultCrystalRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class CrystalRecipeProcessor implements IComponentProcessor {
    private DefaultCrystalRecipe _recipe = null;

    @Override
    public void setup(IVariableProvider variables) {
        ResourceLocation id = new ResourceLocation(variables.get("recipe").asString());
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        manager.byKey(id).ifPresent(
                (recipe) -> {
                    if (recipe instanceof DefaultCrystalRecipe crystalRecipe)
                        _recipe = crystalRecipe;
                }
        );

        if (_recipe == null)
            throw new RuntimeException("Could not find recipe ID:" + id);
    }

    @Override
    public IVariable process(String key) {
        if (_recipe == null) {
            return IVariable.empty();
        } else if (key.equals("recipe")) {
            return IVariable.from(_recipe.getResultItem());
        } else if (key.startsWith("item")) {
            int i = Integer.parseInt(String.valueOf(key.charAt(4)));
            int j = Integer.parseInt(String.valueOf(key.charAt(5)));
            Ingredient ing = _recipe.key.get("" + _recipe.pattern[i].charAt(j));
            return IVariable.from(ing == null? ItemStack.EMPTY : ing.getItems());
        } else {
            return IVariable.empty();
        }
    }
}
