package cn.nulladev.technicalcores.core;

import cn.nulladev.technicalcores.client.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IProxy {
    void setup();
}

class ClientProxy implements IProxy {
    public Level getLevel() {
        return Minecraft.getInstance().level;
    }

    public Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    @Override
    public void setup() {
        MenuScreens.register(TCRegistry.MT_CRYSTAL.get(), CrystalGui::new);
        MenuScreens.register(TCRegistry.MT_CORE_BAG.get(), CoreBagGui::new);
        MenuScreens.register(TCRegistry.MT_COLLECTOR.get(), CollectorGui::new);
    }
}

class ServerProxy implements IProxy {
    @Override
    public void setup() {
    }
}
