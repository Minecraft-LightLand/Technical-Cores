package cn.nulladev.technicalcores.core;

import cn.nulladev.technicalcores.block.CollectorBE;
import cn.nulladev.technicalcores.client.CoreBagMenu;
import cn.nulladev.technicalcores.client.CollectorMenu;
import cn.nulladev.technicalcores.client.CrystalMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TCRegistry {

    public static final TCRegistry INSTANCE = new TCRegistry();

    public static final DeferredRegister<BlockEntityType<?>> BE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TechnicalCores.MODID);
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TechnicalCores.MODID);

    public static final RegistryObject<BlockEntityType<CollectorBE>> BE_COLLECTOR = BE.register("collector", () -> BlockEntityType.Builder.of(CollectorBE::new, TCBlocks.AUTOMATIC_COLLECTOR.get()).build(null));

    public static final RegistryObject<MenuType<CrystalMenu>> MT_CRYSTAL = MENU.register("space_crystal", () -> IForgeMenuType.create(CrystalMenu::fromNetwork));
    public static final RegistryObject<MenuType<CoreBagMenu>> MT_CORE_BAG = MENU.register("core_bag", () -> IForgeMenuType.create(CoreBagMenu::fromNetwork));
    public static final RegistryObject<MenuType<CollectorMenu>> MT_COLLECTOR = MENU.register("collector", () -> IForgeMenuType.create((windowId, inv, data) -> new CollectorMenu(windowId, inv, data.readBlockPos(), Minecraft.getInstance().level)));

    public void registerEvents() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(INSTANCE);
        TCBlocks.BLOCK.register(modBus);
        TCItems.ITEM.register(modBus);
        TCRecipes.RECIPE.register(modBus);
        TCRecipes.RECIPE_TYPE.register(modBus);
        BE.register(modBus);
        MENU.register(modBus);
    }

}
