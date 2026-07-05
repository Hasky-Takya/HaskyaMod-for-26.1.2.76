package com.example.examplemod;

import java.util.EnumSet;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class FollowHaskyaOwnerGoal extends Goal {
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
    }
}
