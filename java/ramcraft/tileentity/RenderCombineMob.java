package ramcraft.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import ramcraft.RAMCraft;
import ramcraft.mob.EntityCombineMob;

public class RenderCombineMob extends RenderLiving {

	private static final ResourceLocation combineTextures = new ResourceLocation(
			RAMCraft.MODID + ":" + "textures/models/combine.png");

	public RenderCombineMob(ModelBase p_i1262_1_, float p_i1262_2_) {
		super(p_i1262_1_, p_i1262_2_);
	}

	protected ResourceLocation getEntityTexture(EntityCombineMob entity) {
		return combineTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityCombineMob) entity);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
		GL11.glScalef(5f, 5f, 5f);
	}

}
