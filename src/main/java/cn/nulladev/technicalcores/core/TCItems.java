package cn.nulladev.technicalcores.core;

import cn.nulladev.technicalcores.item.*;
import cn.nulladev.technicalcores.item.technicalcore.*;
import cn.nulladev.technicalcores.item.technicalcore.custom.*;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

@SuppressWarnings("unused")
public class TCItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, TechnicalCores.MODID);

    public static RegistryObject<Item> SPACE_CRYSTAL_BASIC = register("space_crystal_basic", (p) -> new SpaceCrystal(p, 3));
    public static RegistryObject<Item> SPACE_CRYSTAL_ADVANCED = register("space_crystal_advanced", (p) -> new SpaceCrystal(p.rarity(Rarity.RARE), 4));
    public static RegistryObject<Item> SPACE_CRYSTAL_ULTIMATE = register("space_crystal_ultimate", (p) -> new SpaceCrystal(p.rarity(Rarity.UNCOMMON), 5));

    public static RegistryObject<Item> CORE_AMPLIFIER = register("core_amplifier", GenericTCItem::new);
    public static RegistryObject<Item> CORE_STORAGE_BAG = register("core_storage_bag", CoreStorageBag::new);
    public static RegistryObject<Item> INFINITE_FUEL = register("infinite_fuel", InfiniteFuel::new);
    public static RegistryObject<Item> WORLD_INTERACTION_IO = register("world_interaction_io", GenericTCItem::new);
    public static RegistryObject<Item> WORLD_INTERACTION_WAND = register("world_interaction_wand", WorldInteractionWand::new);

    public static RegistryObject<Item> MODIFIER_DARK_BOX = register("modifier_dark_box", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_END_UPDATE = register("modifier_end_update", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_ENDERMITE = register("modifier_endermite", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_FALLING = register("modifier_falling", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_NETHER_PORTAL = register("modifier_nether_portal", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_PISTON_WARM = register("modifier_piston_worm", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_REDSTONE_PULSE = register("modifier_redstone_pulse", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_REDSTONE_PULSE_CONTROLLABLE = register("modifier_redstone_pulse_controllable", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_SHULKER = register("modifier_shulker", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_SNOW_GOLEM = register("modifier_snow_golem", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_VILLAGER = register("modifier_villager", GenericTCItem::new);
    public static RegistryObject<Item> MODIFIER_WITHER = register("modifier_wither", GenericTCItem::new);

    public static RegistryObject<Item> CORE_AZALEA_TREE_FACTORY = register("core_azalea_tree_factory", p -> new SimplePlacementCore(p, 20, Blocks.OAK_WOOD));
    public static RegistryObject<Item> CORE_CACTUS_FARM = register("core_cactus_farm", p -> new SimplePlacementCore(p, 800, Blocks.CACTUS));
    public static RegistryObject<Item> CORE_COBBLESTONE_GENERATOR = register("core_cobblestone_generator", p -> new SimplePlacementCore(p, 30, Blocks.COBBLESTONE));
    public static RegistryObject<Item> CORE_COBBLESTONE_GENERATOR_AUTOMATED = register("core_cobblestone_generator_automated", p -> new SimplePlacementCore(p, 8, Blocks.COBBLESTONE));
    public static RegistryObject<Item> CORE_INFINITE_WATER = register("core_infinite_water", p -> new SimplePlacementCore(p, 5, Blocks.WATER));
    public static RegistryObject<Item> CORE_LAVA_FARM = register("core_lava_farm", p -> new SimplePlacementCore(p, 100, Blocks.LAVA));
    public static RegistryObject<Item> CORE_OBSIDIAN_GENERATOR = register("core_obsidian_generator", p -> new SimplePlacementCore(p, 100, Blocks.OBSIDIAN));
    public static RegistryObject<Item> CORE_OBSIDIAN_GENERATOR_AUTOMATED = register("core_obsidian_generator_automated", p -> new SimplePlacementCore(p, 8, Blocks.OBSIDIAN));
    public static RegistryObject<Item> CORE_STONE_GENERATOR = register("core_stone_generator", p -> new SimplePlacementCore(p, 30, Blocks.STONE));
    public static RegistryObject<Item> CORE_STRING_DUPLICATOR = register("core_string_duplicator", p -> new SimplePlacementCore(p, 8, Blocks.TRIPWIRE));

    public static RegistryObject<Item> CORE_CHICKEN_FARM = register("core_chicken_farm", p -> new SimpleProjectileCore(p, 8, ThrownEgg::new));
    public static RegistryObject<Item> CORE_ENDERMAN_FARM = register("core_enderman_farm", p -> new SimpleProjectileCore(p, 8, ThrownEnderpearl::new));
    public static RegistryObject<Item> CORE_SNOWBALL_FARM = register("core_snowball_farm", p -> new SimpleProjectileCore(p, 8, Snowball::new));

    public static RegistryObject<Item> CORE_COOKED_CHICKEN_FARM = register("core_cooked_chicken_farm", p -> new SimpleFoodCore(p, 20, Foods.COOKED_CHICKEN));
    public static RegistryObject<Item> CORE_HOGLIN_FARM = register("core_hoglin_farm", p -> new SimpleFoodCore(p, 20, Foods.COOKED_PORKCHOP));
    public static RegistryObject<Item> CORE_MOB_GRINDER = register("core_mob_grinder", p -> new SimpleFoodCore(p, 20, Foods.ROTTEN_FLESH));

    public static RegistryObject<Item> CORE_TNT_DUPLICATOR = register("core_tnt_duplicator", p -> new SimpleEntityCore(p, 20, SimpleEntityCore::createPrimedTNTEntity));
    public static RegistryObject<Item> CORE_VILLAGER_BREEDER = register("core_villager_breeder", p -> new SimpleEntityCore(p, 12000, SimpleEntityCore::createBabyVillager));

    public static RegistryObject<Item> CORE_GHAST_FARM = register("core_ghast_farm", p -> new SimpleCore(p, 80));
    public static RegistryObject<Item> CORE_IRON_GOLEM_FARM = register("core_iron_golem_farm", p -> new SimpleCore(p, 160));
    public static RegistryObject<Item> CORE_SHULKER_FARM = register("core_shulker_farm", p -> new SimpleCore(p, 50));
    public static RegistryObject<Item> CORE_ZOMBIFIED_PIGLIN_FARM = register("core_zombified_piglin_farm", p -> new SimpleCore(p, 8));

    public static RegistryObject<Item> CORE_CARPET_RAIL_DUPLICATOR = register("core_carpet_rail_duplicator", p -> new MultiplePlacementCore(p, 8, MultiplePlacementCore.carpet_and_rail));
    public static RegistryObject<Item> CORE_SAND_DUPLICATOR = register("core_sand_duplicator", p -> new MultiplePlacementCore(p, 8, MultiplePlacementCore.falling_blocks));

    public static RegistryObject<Item> CORE_END_FRAME_BREAKER = register("core_end_frame_breaker", p -> new SimpleRemoveCore(p, 20, Blocks.END_PORTAL_FRAME));
    public static RegistryObject<Item> CORE_BEDROCK_BREAKER = register("core_bedrock_breaker", p -> new SimpleRemoveCore(p, 20, Blocks.BEDROCK));

    public static RegistryObject<Item> CORE_BONE_MEAL_FARM = register("core_bone_meal_farm", p -> new BoneMealCore(p, 20));
    public static RegistryObject<Item> CORE_DRAIN = register("core_drain", p -> new DrainCore(p, 200));
    public static RegistryObject<Item> CORE_MELON_FARM = register("core_melon_farm", p -> new MelonCore(p, 50));
    public static RegistryObject<Item> CORE_VILLAGER_CROP_FARM = register("core_villager_crop_farm", p -> new VillagerCropFarm(p, 600));
    public static RegistryObject<Item> CORE_WORLD_EATER = register("core_world_eater", p -> new WorldEaterCore(p, 6000));

    public static <V extends Item> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return ITEM.register(name, () -> sup.apply(new Item.Properties().tab(TCCreativeTab.INSTANCE)));
    }

}
