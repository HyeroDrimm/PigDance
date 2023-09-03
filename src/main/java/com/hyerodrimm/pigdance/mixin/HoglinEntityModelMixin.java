package com.hyerodrimm.pigdance.mixin;

import com.hyerodrimm.pigdance.PigDanceMod;
import com.hyerodrimm.pigdance.interfaces.IIsSongPlaying;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.HoglinEntityModel;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HoglinEntityModel.class)
public abstract class HoglinEntityModelMixin <T extends MobEntity>
        extends AnimalModel<T> {
    @Shadow
    private final ModelPart head;

    public HoglinEntityModelMixin(ModelPart root) {
        super(true, 8.0f, 6.0f, 1.9f, 2.0f, 24.0f);
        this.head = root.getChild(EntityModelPartNames.HEAD);
    }

    @Inject( method = "setAngles*", at = @At("TAIL"))
    public void setAngles(T mobEntity, float f, float g, float h, float i, float j, CallbackInfo callbackInfo){
        boolean isDancing = ((IIsSongPlaying)mobEntity).isSongPlaying();
        if (isDancing){
            int age = mobEntity.age;
            PigDanceMod.LOGGER.info(String.valueOf(age));
            this.head.pitch = MathHelper.cos(age * MathHelper.PI / 10 * 1.416f)/3f;
        }
    }
}
