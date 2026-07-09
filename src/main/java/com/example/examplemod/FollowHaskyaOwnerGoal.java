package com.example.examplemod;

import java.util.EnumSet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class FollowHaskyaOwnerGoal extends Goal {
    private static final int TELEPORT_DISTANCE = 32;
    private static final int TELEPORT_ATTEMPTS = 10;
    private static final int TELEPORT_HORIZONTAL_RANGE = 3;
    private static final int TELEPORT_VERTICAL_RANGE = 2;

    private final PathfinderMob mob;
    private final HaskyaTameable tameable;
    private final double speedModifier;
    private final float startDistance;
    private final float stopDistance;
    private Player owner;

    public FollowHaskyaOwnerGoal(PathfinderMob mob, HaskyaTameable tameable, double speedModifier, float startDistance, float stopDistance) {
        this.mob = mob;
        this.tameable = tameable;
        this.speedModifier = speedModifier;
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    private boolean tryTeleportNearOwner() {
        BlockPos pos = this.owner.blockPosition();

        for (int i = 0; i < TELEPORT_ATTEMPTS; i++) {

            int dx = this.mob.getRandom().nextInt(-TELEPORT_HORIZONTAL_RANGE, TELEPORT_HORIZONTAL_RANGE + 1);
            int dz = this.mob.getRandom().nextInt(-TELEPORT_HORIZONTAL_RANGE, TELEPORT_HORIZONTAL_RANGE + 1);

            if (Math.abs(dx) < 2 && Math.abs(dz) < 2) {
                continue;
            }

            for (int dy = TELEPORT_VERTICAL_RANGE; dy >= -TELEPORT_VERTICAL_RANGE; dy--) {
                BlockPos target = pos.offset(dx, dy, dz);

                if (this.canTeleportTo(target)) {
                    this.mob.teleportTo(
                            target.getX() + 0.5,
                            target.getY(),
                            target.getZ() + 0.5
                    );
                    this.mob.getNavigation().stop();
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canTeleportTo(BlockPos pos) {
        Level level = this.mob.level();
        BlockPos below = pos.below();
        BlockPos head = pos.above();
        BlockState belowState = level.getBlockState(below);
        BlockState feetState = level.getBlockState(pos);
        BlockState headState = level.getBlockState(head);

        if (!level.getWorldBorder().isWithinBounds(pos)) {
            return false;
        }

        if (this.isDangerousTeleportBlock(belowState) || this.isDangerousTeleportBlock(feetState) || this.isDangerousTeleportBlock(headState)) {
            return false;
        }

        if (!belowState.isFaceSturdy(level, below, Direction.UP)) {
            return false;
        }

        if (!feetState.getFluidState().isEmpty() || !headState.getFluidState().isEmpty()) {
            return false;
        }

        if (!feetState.getCollisionShape(level, pos).isEmpty() || !headState.getCollisionShape(level, head).isEmpty()) {
            return false;
        }

        Vec3 offset = Vec3.atLowerCornerOf(pos.subtract(this.mob.blockPosition()));
        return level.noCollision(this.mob, this.mob.getBoundingBox().move(offset));
    }

    private boolean isDangerousTeleportBlock(BlockState state) {
        return state.is(Blocks.LAVA)
                || state.is(Blocks.FIRE)
                || state.is(Blocks.SOUL_FIRE)
                || state.is(Blocks.CAMPFIRE)
                || state.is(Blocks.SOUL_CAMPFIRE)
                || state.is(Blocks.MAGMA_BLOCK)
                || state.is(Blocks.CACTUS)
                || state.is(Blocks.SWEET_BERRY_BUSH)
                || state.is(Blocks.POWDER_SNOW);
    }

    @Override
    public boolean canUse() {
        if (!this.tameable.isTamed()) {
            return false;
        }

        this.owner = this.tameable.getOwner();
        return this.owner != null && this.mob.distanceToSqr(this.owner) > this.startDistance * this.startDistance;
    }

    @Override
    public boolean canContinueToUse() {
        return this.owner != null
                && this.owner.isAlive()
                && this.mob.distanceToSqr(this.owner) > this.stopDistance * this.stopDistance;
    }

    @Override
    public void stop() {
        this.owner = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.owner == null) {
            return;
        }

        this.mob.getLookControl().setLookAt(this.owner, 10.0F, this.mob.getMaxHeadXRot());
        this.mob.getNavigation().moveTo(this.owner, this.speedModifier);

        double distanceSqr = this.mob.distanceToSqr(this.owner);
        if (!this.mob.isLeashed() && distanceSqr > TELEPORT_DISTANCE * TELEPORT_DISTANCE) {
            tryTeleportNearOwner();
            return;
        }
    }
}
