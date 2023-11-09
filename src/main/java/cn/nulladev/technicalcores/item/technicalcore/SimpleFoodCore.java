package cn.nulladev.technicalcores.item.technicalcore;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SimpleFoodCore extends BaseCore implements IWandInteraction {
    private final FoodProperties foodProperties;

    public SimpleFoodCore(Properties props, int cooldown, FoodProperties foodProperties) {
        super(props, cooldown);
        this.foodProperties = foodProperties;
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(this.foodProperties.canAlwaysEat())) {
            player.getFoodData().eat(this.foodProperties.getNutrition(), this.foodProperties.getSaturationModifier());
            for(Pair<MobEffectInstance, Float> pair : this.foodProperties.getEffects()) {
                if (pair.getFirst() != null && player.getRandom().nextFloat() < pair.getSecond()) {
                    player.addEffect(new MobEffectInstance(pair.getFirst()));
                }
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

}