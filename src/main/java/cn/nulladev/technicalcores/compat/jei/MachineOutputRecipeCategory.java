package cn.nulladev.technicalcores.compat.jei;

import cn.nulladev.technicalcores.crafting.DefaultCoreOutputRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;

public class MachineOutputRecipeCategory implements IRecipeCategory<DefaultCoreOutputRecipe> {

    private final ResourceLocation bg;
    private final ResourceLocation uid;
    private final ItemStack stack;
    private final String translation;

    private IDrawable background, icon;

    public MachineOutputRecipeCategory(ResourceLocation uid, ItemStack stack, ResourceLocation bg, String translation) {
        this.uid = uid;
        this.bg = bg;
        this.stack = stack;
        this.translation = translation;
    }

    public MachineOutputRecipeCategory init(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(bg, 7, 16, 162, 18 * 5)
                .addPadding(0, 0, 0, 0)
                .build();
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, stack);
        return this;
    }

    @Override
    public RecipeType<DefaultCoreOutputRecipe> getRecipeType() {
        return new RecipeType<>(this.uid, DefaultCoreOutputRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable(translation);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DefaultCoreOutputRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 73, 1).addIngredients(Ingredient.of(recipe.input));
        var outputIngredients = recipe.outputs.keySet().stream().map(Ingredient::of).toList();
        var outputProps = recipe.outputs.values().stream().toList();
        var outputs = new ArrayList<>(outputIngredients);
        var props = new ArrayList<>(outputProps);
        for (int i = 0; i < 27 - outputIngredients.size(); i++) {
            outputs.add(Ingredient.EMPTY);
            props.add(0D);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                double prop = props.get(i * 9 + j);
                builder.addSlot(RecipeIngredientRole.OUTPUT, j * 18 + 1, 36 + 1 + i * 18)
                        .addIngredients(outputs.get(i * 9 + j))
                        .addTooltipCallback(
                                (view, tooltip) -> {
                                    if (prop > 0 && prop < 100)
                                        tooltip.add(Component.translatable("jei.tooltip.output_prop", prop));
                                }
                        );
            }
        }
    }
}

