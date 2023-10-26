package cn.nulladev.technicalcores.core;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TechnicalCores.MODID)
public class TechnicalCores {

    public static final String MODID = "technicalcores";

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public TechnicalCores() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        TCRegistry.INSTANCE.registerEvents();
    }

    public void setup(final FMLClientSetupEvent event) {
        proxy.setup();
    }

}
