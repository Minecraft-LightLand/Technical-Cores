package cn.nulladev.technicalcores.core;

import cn.nulladev.technicalcores.client.CrystalMenu;
import cn.nulladev.technicalcores.crafting.*;
import com.lcy0x1.base.BaseRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TCRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TechnicalCores.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, TechnicalCores.MODID);

    public static final RegistryObject<RecipeType<AbstractCrystalRecipe<?>>> RT_CRYSTAL = RECIPE_TYPE.register("crystal", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<AbstractCoreOutputRecipe<?>>> RT_CORE_OUTPUT = RECIPE_TYPE.register("core_output", () -> new RecipeType<>() {});

    public static final RegistryObject<BaseRecipe.RecType<DefaultCrystalRecipe, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer>> RS_CRYSTAL_DEFAULT = RECIPE.register("crystal_default", () -> new BaseRecipe.RecType<>(DefaultCrystalRecipe.class, RT_CRYSTAL));
    public static final RegistryObject<BaseRecipe.RecType<DefaultCoreOutputRecipe, AbstractCoreOutputRecipe<?>, SimpleContainer>> RS_CORE_OUTPUT_DEFAULT = RECIPE.register("core_output_default", () -> new BaseRecipe.RecType<>(DefaultCoreOutputRecipe.class, RT_CORE_OUTPUT));

    public static final RegistryObject<RecipeSerializer<WandAddCoreRecipe>> RS_WAND_ADD_CORE = RECIPE.register("wand_add_core", () -> WandAddCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<WandRemoveCoreRecipe>> RS_WAND_REMOVE_CORE = RECIPE.register("wand_remove_core", () -> WandRemoveCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<CoreAddContentRecipe>> RS_CORE_ADD_CONTENT = RECIPE.register("core_add_content", () -> CoreAddContentRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<CoreRemoveContentRecipe>> RS_CORE_REMOVE_CONTENT = RECIPE.register("core_remove_content", () -> CoreRemoveContentRecipe.SERIALIZER);
}
