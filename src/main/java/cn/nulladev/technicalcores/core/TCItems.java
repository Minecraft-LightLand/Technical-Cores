package cn.nulladev.technicalcores.core;

import cn.nulladev.technicalcores.item.*;
import cn.nulladev.technicalcores.item.technicalcore.*;
import cn.nulladev.technicalcores.item.technicalcore.custom.*;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class TCItems {

	public static ItemEntry<SpaceCrystal> SPACE_CRYSTAL_BASIC = register("space_crystal_basic", (p) -> new SpaceCrystal(p, 3)).lang("Space Crystal: Basic").register();
	public static ItemEntry<SpaceCrystal> SPACE_CRYSTAL_ADVANCED = register("space_crystal_advanced", (p) -> new SpaceCrystal(p.rarity(Rarity.RARE), 4)).register();
	public static ItemEntry<SpaceCrystal> SPACE_CRYSTAL_ULTIMATE = register("space_crystal_ultimate", (p) -> new SpaceCrystal(p.rarity(Rarity.UNCOMMON), 5)).register();

	public static ItemEntry<GenericTCItem> CORE_AMPLIFIER = register("core_amplifier", GenericTCItem::new).register();
	public static ItemEntry<CoreStorageBag> CORE_STORAGE_BAG = register("core_storage_bag", CoreStorageBag::new).register();
	public static ItemEntry<InfiniteFuel> INFINITE_FUEL = register("infinite_fuel", InfiniteFuel::new).register();
	public static ItemEntry<GenericTCItem> WORLD_INTERACTION_IO = register("world_interaction_io", GenericTCItem::new).register();
	public static ItemEntry<WorldInteractionWand> WORLD_INTERACTION_WAND = register("world_interaction_wand", WorldInteractionWand::new).register();

	public static ItemEntry<GenericTCItem> MODIFIER_DARK_BOX = register("modifier_dark_box", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_END_UPDATE = register("modifier_end_update", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_ENDERMITE = register("modifier_endermite", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_FALLING = register("modifier_falling", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_IRON_GOLEM = register("modifier_iron_golem", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_NETHER_PORTAL = register("modifier_nether_portal", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_PISTON_WARM = register("modifier_piston_worm", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_REDSTONE_PULSE = register("modifier_redstone_pulse", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_REDSTONE_PULSE_CONTROLLABLE = register("modifier_redstone_pulse_controllable", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_SHULKER = register("modifier_shulker", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_SNOW_GOLEM = register("modifier_snow_golem", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_VILLAGER = register("modifier_villager", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_WHITE_BOX = register("modifier_white_box", GenericTCItem::new).register();
	public static ItemEntry<GenericTCItem> MODIFIER_WITHER = register("modifier_wither", GenericTCItem::new).register();

	public static ItemEntry<SimplePlacementCore> CORE_AZALEA_TREE_FACTORY = register("core_azalea_tree_factory", p -> new SimplePlacementCore(p, 20, Blocks.OAK_WOOD)).register();
	public static ItemEntry<SimplePlacementCore> CORE_CACTUS_FARM = register("core_cactus_farm", p -> new SimplePlacementCore(p, 800, Blocks.CACTUS)).register();
	public static ItemEntry<SimplePlacementCore> CORE_COBBLESTONE_GENERATOR = register("core_cobblestone_generator", p -> new SimplePlacementCore(p, 30, Blocks.COBBLESTONE)).register();
	public static ItemEntry<SimplePlacementCore> CORE_COBBLESTONE_GENERATOR_AUTOMATED = register("core_cobblestone_generator_automated", p -> new SimplePlacementCore(p, 8, Blocks.COBBLESTONE)).register();
	public static ItemEntry<SimplePlacementCore> CORE_INFINITE_WATER = register("core_infinite_water", p -> new SimplePlacementCore(p, 5, Blocks.WATER)).register();
	public static ItemEntry<SimplePlacementCore> CORE_LAVA_FARM = register("core_lava_farm", p -> new SimplePlacementCore(p, 100, Blocks.LAVA)).register();
	public static ItemEntry<SimplePlacementCore> CORE_OBSIDIAN_GENERATOR = register("core_obsidian_generator", p -> new SimplePlacementCore(p, 100, Blocks.OBSIDIAN)).register();
	public static ItemEntry<SimplePlacementCore> CORE_OBSIDIAN_GENERATOR_AUTOMATED = register("core_obsidian_generator_automated", p -> new SimplePlacementCore(p, 8, Blocks.OBSIDIAN)).register();
	public static ItemEntry<SimplePlacementCore> CORE_STONE_GENERATOR = register("core_stone_generator", p -> new SimplePlacementCore(p, 30, Blocks.STONE)).register();
	public static ItemEntry<SimplePlacementCore> CORE_STRING_DUPLICATOR = register("core_string_duplicator", p -> new SimplePlacementCore(p, 8, Blocks.TRIPWIRE)).register();

	public static ItemEntry<SimpleProjectileCore> CORE_CHICKEN_FARM = register("core_chicken_farm", p -> new SimpleProjectileCore(p, 8, ThrownEgg::new)).register();
	public static ItemEntry<SimpleProjectileCore> CORE_ENDERMAN_FARM = register("core_enderman_farm", p -> new SimpleProjectileCore(p, 8, ThrownEnderpearl::new)).register();
	public static ItemEntry<SimpleProjectileCore> CORE_SNOWBALL_FARM = register("core_snowball_farm", p -> new SimpleProjectileCore(p, 8, Snowball::new)).register();

	public static ItemEntry<SimpleFoodCore> CORE_COOKED_CHICKEN_FARM = register("core_cooked_chicken_farm", p -> new SimpleFoodCore(p, 32, Foods.COOKED_CHICKEN)).register();
	public static ItemEntry<SimpleFoodCore> CORE_GLOW_BERRIES_FARM = register("core_glow_berries_farm", p -> new SimpleFoodCore(p, 32, Foods.GLOW_BERRIES)).register();
	public static ItemEntry<SimpleFoodCore> CORE_GUARDIAN_FARM = register("core_guardian_farm", p -> new SimpleFoodCore(p, 32, Foods.COD)).register();
	public static ItemEntry<SimpleFoodCore> CORE_HOGLIN_FARM = register("core_hoglin_farm", p -> new SimpleFoodCore(p, 32, Foods.COOKED_PORKCHOP)).register();
	public static ItemEntry<SimpleFoodCore> CORE_INFINITE_MUSHROOM_STEW = register("core_infinite_mushroom_stew", p -> new SimpleFoodCore(p, 32, Foods.MUSHROOM_STEW)).register();
	public static ItemEntry<SimpleFoodCore> CORE_MOB_GRINDER = register("core_mob_grinder", p -> new SimpleFoodCore(p, 32, Foods.ROTTEN_FLESH)).register();

	public static ItemEntry<SimpleEntityCore> CORE_TNT_DUPLICATOR = register("core_tnt_duplicator", p -> new SimpleEntityCore(p, 20, SimpleEntityCore::createPrimedTNTEntity)).register();
	public static ItemEntry<SimpleEntityCore> CORE_VILLAGER_BREEDER = register("core_villager_breeder", p -> new SimpleEntityCore(p, 12000, SimpleEntityCore::createBabyVillager)).register();

	public static ItemEntry<SimpleCore> CORE_GHAST_FARM = register("core_ghast_farm", p -> new SimpleCore(p, 80)).register();
	public static ItemEntry<SimpleCore> CORE_IRON_GOLEM_FARM = register("core_iron_golem_farm", p -> new SimpleCore(p, 160)).register();
	public static ItemEntry<SimpleCore> CORE_SHULKER_FARM = register("core_shulker_farm", p -> new SimpleCore(p, 50)).register();
	public static ItemEntry<SimpleCore> CORE_SLIME_FARM = register("core_slime_farm", p -> new SimpleCore(p, 8)).register();
	public static ItemEntry<SimpleCore> CORE_ZOMBIFIED_PIGLIN_FARM = register("core_zombified_piglin_farm", p -> new SimpleCore(p, 8)).register();

	public static ItemEntry<MultiplePlacementCore> CORE_CARPET_RAIL_DUPLICATOR = register("core_carpet_rail_duplicator", p -> new MultiplePlacementCore(p, 8, MultiplePlacementCore.carpet_and_rail)).register();
	public static ItemEntry<MultiplePlacementCore> CORE_MULTI_BLOCK_CROP_FARM = register("core_multi_block_crop_farm", p -> new MultiplePlacementCore(p, 20, MultiplePlacementCore.multi_block_crops)).register();
	public static ItemEntry<MultiplePlacementCore> CORE_SAND_DUPLICATOR = register("core_sand_duplicator", p -> new MultiplePlacementCore(p, 8, MultiplePlacementCore.falling_blocks)).register();

	public static ItemEntry<SimpleRemoveCore> CORE_END_FRAME_BREAKER = register("core_end_frame_breaker", p -> new SimpleRemoveCore(p, 20, Blocks.END_PORTAL_FRAME)).register();
	public static ItemEntry<SimpleRemoveCore> CORE_BEDROCK_BREAKER = register("core_bedrock_breaker", p -> new SimpleRemoveCore(p, 20, Blocks.BEDROCK)).register();

	public static ItemEntry<BoneMealCore> CORE_BONE_MEAL_FARM = register("core_bone_meal_farm", p -> new BoneMealCore(p, 20)).register();
	public static ItemEntry<DrainCore> CORE_DRAIN = register("core_drain", p -> new DrainCore(p, 200)).register();
	public static ItemEntry<MilkCore> CORE_INFINITE_MILK = register("core_infinite_milk", p -> new MilkCore(p, 32)).register();
	public static ItemEntry<MelonCore> CORE_MELON_FARM = register("core_melon_farm", p -> new MelonCore(p, 50)).register();
	public static ItemEntry<VillagerCropFarmCore> CORE_VILLAGER_CROP_FARM = register("core_villager_crop_farm", p -> new VillagerCropFarmCore(p, 600)).register();
	public static ItemEntry<WorldEaterCore> CORE_WORLD_EATER = register("core_world_eater", p -> new WorldEaterCore(p, 6000)).register();

	public static <V extends Item> ItemBuilder<V, ?> register(String name, NonNullFunction<Item.Properties, V> sup) {
		return TechnicalCores.REGISTRATE.item(name, sup);
	}

	public static void register() {
	}

}
