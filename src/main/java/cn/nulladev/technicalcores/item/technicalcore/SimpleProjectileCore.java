package cn.nulladev.technicalcores.item.technicalcore;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public class SimpleProjectileCore extends BaseCore implements IWandInteraction {
    private final BiFunction<Level, Player, ThrowableItemProjectile> projectileLambda;
    private final SoundEvent sound;

    public SimpleProjectileCore(Properties props, int cooldown, BiFunction<Level, Player, ThrowableItemProjectile> projectileLambda, @Nullable SoundEvent sound) {
        super(props, cooldown);
        this.projectileLambda = projectileLambda;
        this.sound = sound;
    }

    public SimpleProjectileCore(Properties props, int cooldown, BiFunction<Level, Player, ThrowableItemProjectile> projectileLambda) {
        this(props, cooldown, projectileLambda, null);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (!(sound == null)) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        }

        if (!level.isClientSide) {
            var projectile = projectileLambda.apply(level, player);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(projectile);
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
