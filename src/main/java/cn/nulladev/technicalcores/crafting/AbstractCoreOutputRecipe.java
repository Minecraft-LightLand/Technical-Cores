package cn.nulladev.technicalcores.crafting;

import com.lcy0x1.base.BaseRecipe;
import com.lcy0x1.core.util.SerialClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.HashMap;

@SerialClass
public class AbstractCoreOutputRecipe<Rec extends AbstractCoreOutputRecipe<Rec>>
        extends BaseRecipe<Rec, AbstractCoreOutputRecipe<?>, SimpleContainer> {

    @SerialClass.SerialField
    public ItemStack input;
    @SerialClass.SerialField(generic = {ItemStack.class, Double.class})
    public HashMap<ItemStack, Double> outputs;

    public AbstractCoreOutputRecipe(ResourceLocation id, RecType<Rec, AbstractCoreOutputRecipe<?>, SimpleContainer> fac) {
        super(id, fac);
    }

    @Override
    public boolean matches(SimpleContainer inv, Level level) {
        return inv.getItem(0).getItem() == input.getItem();
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }
}
