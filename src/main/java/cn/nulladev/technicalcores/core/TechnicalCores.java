package cn.nulladev.technicalcores.core;

import dev.xkmc.l2library.base.L2Registrate;
import net.minecraftforge.fml.common.Mod;

@Mod(TechnicalCores.MODID)
public class TechnicalCores {

	public static final String MODID = "technicalcores";

	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public TechnicalCores() {
		TCBlocks.register();
		TCItems.register();
		TCRecipes.register();
	}

}
