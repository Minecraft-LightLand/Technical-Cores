package cn.nulladev.technicalcores.core;

import cn.nulladev.technicalcores.client.CrystalMenu;
import cn.nulladev.technicalcores.crafting.*;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.xkmc.l2library.serial.recipe.BaseRecipe;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

public class TCRecipes {

	public static final RegistryEntry<RecipeType<AbstractCrystalRecipe<?>>> RT_CRYSTAL = TechnicalCores.REGISTRATE.recipe("crystal");
	public static final RegistryEntry<RecipeType<AbstractCoreOutputRecipe<?>>> RT_CORE_OUTPUT = TechnicalCores.REGISTRATE.recipe("core_output");

	public static final RegistryEntry<BaseRecipe.RecType<DefaultCrystalRecipe, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer>> RS_CRYSTAL_DEFAULT =
			reg("crystal_default", () -> new BaseRecipe.RecType<>(DefaultCrystalRecipe.class, RT_CRYSTAL));
	public static final RegistryEntry<BaseRecipe.RecType<DefaultCoreOutputRecipe, AbstractCoreOutputRecipe<?>, SimpleContainer>> RS_CORE_OUTPUT_DEFAULT =
			reg("core_output_default", () -> new BaseRecipe.RecType<>(DefaultCoreOutputRecipe.class, RT_CORE_OUTPUT));
	public static final RegistryEntry<RecipeSerializer<WandAddCoreRecipe>> RS_WAND_ADD_CORE =
			reg("wand_add_core", () -> WandAddCoreRecipe.SERIALIZER);
	public static final RegistryEntry<RecipeSerializer<WandRemoveCoreRecipe>> RS_WAND_REMOVE_CORE =
			reg("wand_remove_core", () -> WandRemoveCoreRecipe.SERIALIZER);
	public static final RegistryEntry<RecipeSerializer<CoreAddContentRecipe>> RS_CORE_ADD_CONTENT =
			reg("core_add_content", () -> CoreAddContentRecipe.SERIALIZER);
	public static final RegistryEntry<RecipeSerializer<CoreRemoveContentRecipe>> RS_CORE_REMOVE_CONTENT =
			reg("core_remove_content", () -> CoreRemoveContentRecipe.SERIALIZER);

	private static <A extends RecipeSerializer<?>> RegistryEntry<A> reg(String id, NonNullSupplier<A> sup) {
		return TechnicalCores.REGISTRATE.simple(id, ForgeRegistries.Keys.RECIPE_SERIALIZERS, sup);
	}

	public static void register() {
	}

}
