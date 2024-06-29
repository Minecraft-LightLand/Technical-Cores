package cn.nulladev.technicalcores.core;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class TCLangData {

	public static void onLangGen(RegistrateLangProvider pvd) {
		pvd.add("container.collector", "Automatic Collector");
		pvd.add("technicalcores.misc.total_cd", "CD, %dticks");
		pvd.add("technicalcores.misc.cd1", "Currently available");
		pvd.add("technicalcores.misc.cd2", "Available in %dticks");
		pvd.add("technicalcores.misc.miss_content", "Content Missing");
		pvd.add("technicalcores.misc.miss_core", "Concept Core Missing");
		pvd.add("jei.title.crystal.basic", "Space Crystal, Basic");
		pvd.add("jei.title.crystal.advanced", "Space Crystal, Advanced");
		pvd.add("jei.title.crystal.ultimate", "Space Crystal, Ultimate");
		pvd.add("jei.title.collector", "Automatic collector");
		pvd.add("jei.tooltip.output_prop", "Probability: %.1f%%");
		pvd.add("pachouli_books.technicalcores.name", "Thinking in technical minecraft");
		pvd.add("pachouli_books.technicalcores.landing_text", "If you learn too much about technical minecraft, you will lose your humanity. -By hsds");
	}

}
