package com.lcy0x1.core.util;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RecSerializer<R extends Recipe<I>, I extends Container> implements RecipeSerializer<R> {

    public final Class<R> cls;

    public RecSerializer(Class<R> cls) {
        this.cls = cls;
    }

    @Override
    public R fromJson(ResourceLocation id, JsonObject json) {
        return Serializer.from(json, cls,
                ExceptionHandler.get(() -> cls.getConstructor(ResourceLocation.class).newInstance(id)));

    }

    @Override
    public R fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        return Serializer.from(buf, cls,
                ExceptionHandler.get(() -> cls.getConstructor(ResourceLocation.class).newInstance(id)));
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, R recipe) {
        Serializer.to(buf, recipe);
    }

    public R blank() {
        return ExceptionHandler.get(() -> cls.getConstructor(ResourceLocation.class).newInstance(new ResourceLocation("dummy")));
    }

}
