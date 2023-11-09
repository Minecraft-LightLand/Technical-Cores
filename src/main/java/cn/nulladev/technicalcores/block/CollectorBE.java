package cn.nulladev.technicalcores.block;

import cn.nulladev.technicalcores.client.CollectorMenu;
import cn.nulladev.technicalcores.core.TCRecipes;
import cn.nulladev.technicalcores.core.TCRegistry;
import cn.nulladev.technicalcores.item.IContentedItem;
import cn.nulladev.technicalcores.item.ICooldownItem;
import cn.nulladev.technicalcores.item.technicalcore.BaseCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.IntStream;

public class CollectorBE extends BaseContainerBlockEntity implements WorldlyContainer {

    protected SimpleContainer container = new SimpleContainer(CollectorMenu.SIZE);

    protected static final String TAG_ITEMS = "CollectorItems";

    public static BlockEntityTicker<CollectorBE> ticker = (level, pos, state, blockEntity) -> blockEntity.serverTick(level, pos, state, blockEntity);

    private void serverTick(Level level, BlockPos pos, BlockState state, CollectorBE be) {
        ItemStack coreStack = be.getItem(0);
        if (coreStack.getItem() instanceof ICooldownItem) {
            if (ICooldownItem.readTagCooldown(coreStack) > 0) {
                ICooldownItem.writeTagCooldown(coreStack, ICooldownItem.readTagCooldown(coreStack) - 1);
            } else {
                level.getRecipeManager().getRecipeFor(TCRecipes.RT_CORE_OUTPUT.get(), this.container, level).ifPresent(
                    (recipe) -> {
                        System.out.println(recipe.input.getItem());
                        if (coreStack.getItem() == recipe.input.getItem() &&
                                (IContentedItem.readTagContent(recipe.input).isEmpty() || IContentedItem.readTagContent(coreStack).getItem() == IContentedItem.readTagContent(recipe.input).getItem())) {
                            for (var entry : recipe.outputs.entrySet()) {
                                var stack = entry.getKey();
                                var possibility = entry.getValue();
                                if (Math.random() * 100 <= possibility) {
                                    if (container.canAddItem(stack)){
                                        container.addItem(stack);
                                    }
                                }
                            }
                            if (coreStack.getItem() instanceof BaseCore core) {
                                ICooldownItem.writeTagCooldown(coreStack, core.totalCooldown);
                            }
                        }
                    }
                );
            }
        }
    }

    public CollectorBE(BlockPos pos, BlockState blockState) {
        super(TCRegistry.BE_COLLECTOR.get(), pos, blockState);
    }

    public SimpleContainer getContainer() {
        return this.container;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.collector");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inv) {
        return new CollectorMenu(id, inv, this.worldPosition, this.level);
    }

    @Override
    public int getContainerSize() {
        return this.container.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return this.container.isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return this.container.getItem(index);
    }

    @Override
    public ItemStack removeItem(int index, int num) {
        this.setChanged();
        return this.container.removeItem(index, num);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return this.container.removeItemNoUpdate(index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.setChanged();
        this.container.setItem(index, stack);
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return this.level.getBlockEntity(this.worldPosition) == this;
    }

    @Override
    public void clearContent() {
        this.setChanged();
        this.container.clearContent();
    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(TAG_ITEMS, container.createTag());
    }

    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Items")) {
            container.fromTag(tag.getList(TAG_ITEMS, 10));
        }

    }

    @Override
    public int[] getSlotsForFace(Direction direc) {
        if (direc == Direction.UP) {
            return new int[]{0};
        } else {
            return IntStream.rangeClosed(1, 27).toArray();
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction direc) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direc) {
        return !(stack.getItem() instanceof BaseCore);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 0)
            return stack.getItem() instanceof BaseCore;
        else
            return false;
    }

}
