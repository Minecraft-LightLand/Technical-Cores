package cn.nulladev.technicalcores.item.technicalcore.custom;

import cn.nulladev.technicalcores.item.IContentedItem;
import cn.nulladev.technicalcores.item.technicalcore.ContentedCore;
import cn.nulladev.technicalcores.item.technicalcore.IWandInteraction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VillagerCropFarm extends ContentedCore implements IWandInteraction {
    public static final List<Item> crops = List.of(
            Items.WHEAT_SEEDS,
            Items.POTATO,
            Items.CARROT,
            Items.BEETROOT_SEEDS
    );

    public VillagerCropFarm(Properties props, int cooldown) {
        super(props, cooldown, crops);
    }

    @Override
    public boolean hasInteraction(@NotNull ItemStack stack) {
        return IContentedItem.hasContent(stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        boolean flag = false;

        ItemStack core = IContentedItem.readTagContent(player.getItemInHand(hand));
        Item content = IContentedItem.readTagContent(core).getItem();
        if (content == Items.POTATO) {
            if (player.canEat(false)) {
                player.getFoodData().eat(1, 0.3F);
                flag = true;
            }
        } else if (content == Items.CARROT) {
            if (player.canEat(false)) {
                player.getFoodData().eat(3, 0.6F);
                flag = true;
            }
        } else if (content == Items.BEETROOT_SEEDS) {
            if (player.canEat(false)) {
                player.getFoodData().eat(1, 0.6F);
                flag = true;
            }
        } else if (content == Items.WHEAT_SEEDS) {
        if (player.canEat(false)) {
            if (player.getRandom().nextFloat() < 1F/3)
                player.getFoodData().eat(5, 0.6F);
            flag = true;
        }
    }

        if (flag) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }
}
