package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.UUID;

public class FuyuMidoriEntity extends PathfinderMob implements HaskyaTameable {
    private SimpleContainer inventory;
    private boolean tamed;
    private UUID ownerUUID;

    public FuyuMidoriEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public SimpleContainer getInventory() {
        if (this.inventory == null) {
            this.inventory = new SimpleContainer(18);
        }
        return this.inventory;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24.0D);
    }

    public static boolean checkFuyuMidoriSpawnRules(EntityType<FuyuMidoriEntity> entityType, ServerLevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return Mob.checkMobSpawnRules(entityType, level, spawnReason, pos, random);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowHaskyaOwnerGoal(this, this, 1.25D, 8.0F, 3.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new PickupNearbyItemsGoal(this, this.getInventory(), stack -> stack.is(HaskyaMod.QINGHUISHI.get()), 1.8D));
        this.goalSelector.addGoal(6, new DeliverInventoryToOwnerGoal(this, this, this.getInventory()));
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        this.getInventory().storeAsItemList(output.list("Inventory", ItemStack.CODEC));
        output.putBoolean("Tamed", this.tamed);
        output.storeNullable("Owner", UUIDUtil.CODEC, this.ownerUUID);
    }

    @Override
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.getInventory().fromItemList(input.listOrEmpty("Inventory", ItemStack.CODEC));
        this.tamed = input.getBooleanOr("Tamed", false);
        this.ownerUUID = input.read("Owner", UUIDUtil.CODEC).orElse(null);
        if (this.tamed) {
            this.setPersistenceRequired();
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.FUYU_MIDORI_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.FUYU_MIDORI_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.FUYU_MIDORI_DEATH.get();
    }

    @Override
    public float getVoicePitch() {
        return 1.0F;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 200;
    }

    @Override
    public boolean isTamed() {
        return this.tamed;
    }

    @Override
    public Player getOwner() {
        if (!this.tamed || this.ownerUUID == null || !(this.level() instanceof ServerLevel serverLevel)) {
            return null;
        }

        Entity entity = serverLevel.getEntity(this.ownerUUID);
        return entity instanceof Player player ? player : null;
    }

    public boolean isOwnedBy(Player player) {
        return this.tamed && this.ownerUUID != null && this.ownerUUID.equals(player.getUUID());
    }

    private void tame(Player player) {
        this.tamed = true;
        this.ownerUUID = player.getUUID();
        this.setPersistenceRequired();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.tamed && super.removeWhenFarAway(distanceToClosestPlayer);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.is(HaskyaMod.ITIGOMILK.get())) {
            return super.mobInteract(player, hand);
        }
        if (this.tamed && !this.isOwnedBy(player)) {
            return this.level().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }

        if (!this.level().isClientSide()) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                ItemStack bucket = new ItemStack(Items.BUCKET);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, bucket);
                } else if (!player.getInventory().add(bucket)) {
                    player.drop(bucket, false);
                }
            }

            if (!this.tamed) {
                if (this.getRandom().nextInt(3) == 0) {
                    this.tame(player);
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.HEART, this.getX(), this.getY() + 1.0D, this.getZ(), 7, 0.4D, 0.5D, 0.4D, 0.0D);
                    }
                } else if (this.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 1.0D, this.getZ(), 7, 0.4D, 0.5D, 0.4D, 0.0D);
                }
            }

            this.heal(6.0F);
            this.playSound(ModSounds.FUYU_MIDORI_HEAL.get(), 1.0F, 1.0F);
//            if (this.level() instanceof ServerLevel serverLevel) {
//                serverLevel.sendParticles(ParticleTypes.HEART, this.getX(), this.getY() + 1.0D, this.getZ(), 5, 0.4D, 0.5D, 0.4D, 0.0D);
//            }
        }

        return this.level().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }
}
