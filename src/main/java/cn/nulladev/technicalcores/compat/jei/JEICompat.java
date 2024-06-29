package cn.nulladev.technicalcores.compat.jei;

import cn.nulladev.technicalcores.core.TCBlocks;
import cn.nulladev.technicalcores.core.TCItems;
import cn.nulladev.technicalcores.core.TCRecipes;
import cn.nulladev.technicalcores.core.TechnicalCores;
import cn.nulladev.technicalcores.crafting.AbstractCoreOutputRecipe;
import cn.nulladev.technicalcores.crafting.AbstractCrystalRecipe;
import cn.nulladev.technicalcores.crafting.DefaultCoreOutputRecipe;
import cn.nulladev.technicalcores.crafting.DefaultCrystalRecipe;
import dev.xkmc.l2library.util.Proxy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

@JeiPlugin
public class JEICompat implements IModPlugin {

	public static JEICompat INSTANCE;

	public static final ResourceLocation UID = new ResourceLocation(TechnicalCores.MODID, "jei_plugin");
	public static final CrystalRecipeCategory BASIC = new CrystalRecipeCategory(
			new ResourceLocation(TechnicalCores.MODID, "crystal_basic"),
			() -> TCItems.SPACE_CRYSTAL_BASIC.get().getDefaultInstance(),
			new ResourceLocation(TechnicalCores.MODID, "textures/gui/container/crystal_3.png"),
			"jei.title.crystal.basic", 3);
	public static final CrystalRecipeCategory ADVANCED = new CrystalRecipeCategory(
			new ResourceLocation(TechnicalCores.MODID, "crystal_advanced"),
			() -> TCItems.SPACE_CRYSTAL_ADVANCED.get().getDefaultInstance(),
			new ResourceLocation(TechnicalCores.MODID, "textures/gui/container/crystal_4.png"),
			"jei.title.crystal.advanced", 4);
	public static final CrystalRecipeCategory ULTIMATE = new CrystalRecipeCategory(
			new ResourceLocation(TechnicalCores.MODID, "crystal_ultimate"),
			() -> TCItems.SPACE_CRYSTAL_ULTIMATE.get().getDefaultInstance(),
			new ResourceLocation(TechnicalCores.MODID, "textures/gui/container/crystal_5.png"),
			"jei.title.crystal.ultimate", 5);

	public static final MachineOutputRecipeCategory MACHINE_OUTPUT = new MachineOutputRecipeCategory(
			new ResourceLocation(TechnicalCores.MODID, "machine_output"),
			() -> new ItemStack(TCBlocks.AUTOMATIC_COLLECTOR.get()),
			new ResourceLocation(TechnicalCores.MODID, "textures/gui/container/core_machine.png"),
			"jei.title.collector"
	);

	public JEICompat() {
		INSTANCE = this;
	}

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
	}


	@Override
	public void registerIngredients(IModIngredientRegistration registration) {
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		registration.addRecipeCategories(BASIC.init(helper));
		registration.addRecipeCategories(ADVANCED.init(helper));
		registration.addRecipeCategories(ULTIMATE.init(helper));
		registration.addRecipeCategories(MACHINE_OUTPUT.init(helper));
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		List<AbstractCrystalRecipe<?>> crystalList = Proxy.getClientWorld().getRecipeManager().getAllRecipesFor(TCRecipes.RT_CRYSTAL.get());
		List<DefaultCrystalRecipe> crystalDef = crystalList.stream().filter(e -> e instanceof DefaultCrystalRecipe).map(e -> (DefaultCrystalRecipe) e).toList();
		List<DefaultCrystalRecipe> basic = crystalDef.stream().filter(e -> e.pattern.length == 3).toList();
		List<DefaultCrystalRecipe> advanced = crystalDef.stream().filter(e -> e.pattern.length == 4).toList();
		List<DefaultCrystalRecipe> ultimate = crystalDef.stream().filter(e -> e.pattern.length == 5).toList();
		List<AbstractCoreOutputRecipe<?>> machineList = Proxy.getClientWorld().getRecipeManager().getAllRecipesFor(TCRecipes.RT_CORE_OUTPUT.get());
		List<DefaultCoreOutputRecipe> machineDef = machineList.stream().filter(e -> e instanceof DefaultCoreOutputRecipe).map(e -> (DefaultCoreOutputRecipe) e).toList();
		registration.addRecipes(BASIC.getRecipeType(), basic);
		registration.addRecipes(ADVANCED.getRecipeType(), advanced);
		registration.addRecipes(ULTIMATE.getRecipeType(), ultimate);
		registration.addRecipes(MACHINE_OUTPUT.getRecipeType(), machineDef);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		//TODO
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(TCItems.SPACE_CRYSTAL_BASIC.get().getDefaultInstance(), BASIC.getRecipeType());
		registration.addRecipeCatalyst(TCItems.SPACE_CRYSTAL_ADVANCED.get().getDefaultInstance(), ADVANCED.getRecipeType());
		registration.addRecipeCatalyst(TCItems.SPACE_CRYSTAL_ULTIMATE.get().getDefaultInstance(), ULTIMATE.getRecipeType());
		registration.addRecipeCatalyst(new ItemStack(TCBlocks.AUTOMATIC_COLLECTOR.get()), MACHINE_OUTPUT.getRecipeType());
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
	}

	@Override
	public void registerAdvanced(IAdvancedRegistration registration) {
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
	}

}

