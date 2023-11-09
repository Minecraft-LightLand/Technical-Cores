package cn.nulladev.technicalcores.item.technicalcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class SimpleEntityCore extends BaseCore implements IWandInteraction {
    private final BiFunction<Level, Player, Entity> entityLambda;

    public SimpleEntityCore(Item.Properties props, int cooldown, BiFunction<Level, Player, Entity> entityLambda) {
        super(props, cooldown);
        this.entityLambda = entityLambda;
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            var entity = entityLambda.apply(level, player);
            level.addFreshEntity(entity);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    public static PrimedTnt createPrimedTNTEntity(Level level, Player player) {
        double x = player.getX();
        double y = player.getY() + 0.5D;
        double z = player.getZ();
        return new PrimedTnt(level, x, y, z, null);
    }

    public static Villager createBabyVillager(Level level, Player player) {
        double x = player.getX();
        double y = player.getY() + 0.5D;
        double z = player.getZ();
        Villager villager = new Villager(EntityType.VILLAGER, level);
        villager.setPos(new Vec3(x, y, z));
        villager.setAge(-1200 * 20);
        return villager;
    }

}