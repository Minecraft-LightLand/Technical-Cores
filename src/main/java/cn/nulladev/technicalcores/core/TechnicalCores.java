package cn.nulladev.technicalcores.core;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.l2library.base.L2Registrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;

@Mod(TechnicalCores.MODID)
public class TechnicalCores {

	public static final String MODID = "technicalcores";

	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final RegistryEntry<CreativeModeTab> TAB = REGISTRATE.buildModCreativeTab(MODID, "Technical Cores",
			(e) -> e.icon(TCItems.WORLD_INTERACTION_WAND::asStack));

	public TechnicalCores() {
		TCBlocks.register();
		TCItems.register();
		TCRecipes.register();

		REGISTRATE.addDataGenerator(ProviderType.LANG, TCLangData::onLangGen);
	}

}
