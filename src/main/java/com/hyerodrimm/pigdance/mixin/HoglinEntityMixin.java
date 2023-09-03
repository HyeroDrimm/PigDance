package com.hyerodrimm.pigdance.mixin;

import com.hyerodrimm.pigdance.PigDanceMod;
import com.hyerodrimm.pigdance.interfaces.IIsSongPlaying;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Hoglin;
import net.minecraft.entity.mob.HoglinBrain;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoglinEntity.class)
public abstract class HoglinEntityMixin extends AnimalEntity implements IIsSongPlaying {
	private final static double MAX_DISTANCE_FROM_JUKEBOX_TO_DANCE = 3.46;
	private boolean songPlaying;
	@Nullable
	private BlockPos songSource;

	// TODO: remove this?
	public HoglinEntityMixin(EntityType<? extends HoglinEntity> entityType, World world) {
		super((EntityType<? extends AnimalEntity>)entityType, world);
		this.experiencePoints = 5;
	}

	@Override
	public void setNearbySongPlaying(BlockPos songPosition, boolean playing) {
		this.songSource = songPosition;
		this.songPlaying = playing;
	}

	@Inject(at = @At("HEAD"), method = "tickMovement()V")
	public void onTickMovement(CallbackInfo ci) {
		if (this.songSource == null || !this.songSource.isWithinDistance(this.getPos(), MAX_DISTANCE_FROM_JUKEBOX_TO_DANCE) || !this.getWorld().getBlockState(this.songSource).isOf(Blocks.JUKEBOX)) {
			this.songPlaying = false;
			this.songSource = null;
		}
	}

	public boolean isSongPlaying() {
		return this.songPlaying;
	}

/*	@Override
	protected boolean isImmobile() {
		return this.isDead() || this.isSongPlaying();
	}

	@Inject(method = "tryAttack", at = @At("HEAD"))
	public void onTryAttack(Entity target, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		if (this.isSongPlaying()) {
			callbackInfoReturnable.setReturnValue(false);
		}
	}*/
}