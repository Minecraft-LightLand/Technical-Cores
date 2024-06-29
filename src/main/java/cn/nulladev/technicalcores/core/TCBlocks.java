package cn.nulladev.technicalcores.core;

import cn.nulladev.technicalcores.block.AutomaticCollector;
import cn.nulladev.technicalcores.block.CollectorBE;
import cn.nulladev.technicalcores.client.*;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TCBlocks {

	public static final RegistryEntry<AutomaticCollector> AUTOMATIC_COLLECTOR = TechnicalCores.REGISTRATE
			.block("automatic_collector", p -> new AutomaticCollector(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)
					.requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL)))
			.simpleItem()
			.tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.register();


	public static final RegistryEntry<BlockEntityType<CollectorBE>> BE_COLLECTOR = TechnicalCores.REGISTRATE
			.blockEntity("collector", CollectorBE::new).validBlock(AUTOMATIC_COLLECTOR).register();

	public static final RegistryEntry<MenuType<CrystalMenu>> MT_CRYSTAL = TechnicalCores.REGISTRATE
			.menu("space_crystal", CrystalMenu::fromNetwork, () -> CrystalGui::new).register();
	public static final RegistryEntry<MenuType<CoreBagMenu>> MT_CORE_BAG = TechnicalCores.REGISTRATE
			.menu("core_bag", CoreBagMenu::fromNetwork, () -> CoreBagGui::new).register();
	public static final RegistryEntry<MenuType<CollectorMenu>> MT_COLLECTOR = TechnicalCores.REGISTRATE
			.menu("collector", CollectorMenu::fromNetwork, () -> CollectorGui::new).register();


	public static void register() {
	}

}
