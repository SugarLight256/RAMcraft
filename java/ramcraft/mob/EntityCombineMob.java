package ramcraft.mob;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCombineMob extends EntityLiving {

	private double rideX = 0.5, rideY = 0, rideZ = -0.8;

	public EntityCombineMob(World world) {
		super(world);
		this.setSize(2f, 2f);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.removeTask(new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
	}

	@Override
	public void updateRiderPosition() {
		double theta = Math.PI * (180 + this.rotationYaw) / 180f;
		if (this.riddenByEntity != null) {
			updateRiderPositionWithOffset(rideX * Math.cos(theta) - rideZ * Math.sin(theta), rideY, // 回転の線形変換
					rideX * Math.sin(theta) + rideZ * Math.cos(theta));
		}
	}

	// change ride pos
	private void updateRiderPositionWithOffset(double x, double y, double z) {
		this.riddenByEntity.setPosition(this.posX + x,
				this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + y, this.posZ + z);
	}

	@Override
	public void onLivingUpdate() {
		if (this.newPosRotationIncrements > 0) {
			double d0 = this.posX + (this.newPosX - this.posX) / this.newPosRotationIncrements;
			double d1 = this.posY + (this.newPosY - this.posY) / this.newPosRotationIncrements;
			double d2 = this.posZ + (this.newPosZ - this.posZ) / this.newPosRotationIncrements;
			double d3 = 0;// MathHelper.wrapAngleTo180_double(this.newRotationYaw
							// - this.rotationYaw);
			this.rotationYaw = (float) (this.rotationYaw + d3 / this.newPosRotationIncrements);
			this.rotationPitch = (float) (this.rotationPitch
					+ (this.newRotationPitch - this.rotationPitch) / this.newPosRotationIncrements);
			--this.newPosRotationIncrements;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		} else if (!this.isClientWorld()) {
			this.motionX *= 0.98D;
			this.motionY *= 0.98D;
			this.motionZ *= 0.98D;
		}

		if (Math.abs(this.motionX) < 0.005D) {
			this.motionX = 0.0D;
		}

		if (Math.abs(this.motionY) < 0.005D) {
			this.motionY = 0.0D;
		}

		if (Math.abs(this.motionZ) < 0.005D) {
			this.motionZ = 0.0D;
		}

		this.worldObj.theProfiler.startSection("ai");

		if (this.isMovementBlocked()) {
			this.isJumping = false;
			this.moveStrafing = 0.0F;
			this.moveForward = 0.0F;
			this.randomYawVelocity = 0.0F;
		} else if (this.isClientWorld()) {
			if (this.isAIEnabled()) {
				this.worldObj.theProfiler.startSection("newAi");
				this.updateAITasks();
				this.worldObj.theProfiler.endSection();
			} else {
				this.worldObj.theProfiler.startSection("oldAi");
				this.updateEntityActionState();
				this.worldObj.theProfiler.endSection();
				// this.rotationYawHead = this.rotationYaw;
			}
		}

		this.worldObj.theProfiler.endSection();

		this.worldObj.theProfiler.startSection("travel");
		this.moveStrafing *= 0.98F;
		this.moveForward *= 0.98F;
		this.randomYawVelocity *= 0.9F;
		this.moveEntityWithHeading(this.moveStrafing, this.moveForward);
		this.worldObj.theProfiler.endSection();
		this.worldObj.theProfiler.startSection("push");

		this.worldObj.theProfiler.endSection();
	}

	@Override
	protected boolean interact(EntityPlayer player) {
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
				&& this.riddenByEntity != player) {
			return true;
		} else {
			if (!this.worldObj.isRemote) {
				player.mountEntity(this);
			}

			return true;
		}
	}

	@Override
	public void moveEntityWithHeading(float p_70612_1_, float moveForward) {
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
			this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
			this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			p_70612_1_ = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5F;
			moveForward = ((EntityLivingBase) this.riddenByEntity).moveForward;

			if (moveForward <= 0.0F) {
				moveForward *= 0.25F;
			}

			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (!this.worldObj.isRemote) {
				this.setAIMoveSpeed(
						(float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()
								* 10);
				super.moveEntityWithHeading(p_70612_1_, moveForward);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

			if (f4 > 1.0F) {
				f4 = 1.0F;
			}

			this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		} else {
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(p_70612_1_, moveForward);
		}
	}

	@Override
	public boolean isAIEnabled() {
		return false;
	}
}
