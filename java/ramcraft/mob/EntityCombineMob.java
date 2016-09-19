package ramcraft.mob;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCombineMob extends EntityLiving {

	public EntityCombineMob(World world) {
		super(world);
		this.setSize(4.9f, 4.9f);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.removeTask(new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(53.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(100);
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
	public void onLivingUpdate() {
		// TODO 自動生成されたメソッド・スタブ
		super.onLivingUpdate();
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
						(float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
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
