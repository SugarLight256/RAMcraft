package ramcraft.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import ramcraft.RAMCraft;
import ramcraft.models.ThreshBlockModel;
import ramcraft.tiles.TileEntityThreshBlock;

public class RenderThreshBlock extends TileEntitySpecialRenderer {

	private ThreshBlockModel model;

	private ResourceLocation texture = new ResourceLocation(RAMCraft.MODID + ":" + "textures/models/ThreshBlock.png");

	public RenderThreshBlock() {
		this.model = new ThreshBlockModel();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {

		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5, y + 1.5f, z + 0.5);
		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(((TileEntityThreshBlock) tile).getDir() * (90F) - 90f, 0, 1, 0);
		this.bindTexture(texture);
		this.model.render((Entity) null, 0, 0.1f, 0, 0, 0, 0.0625f);

		GL11.glPushMatrix();
		this.model.renderModel(0.0625f);
		GL11.glPopMatrix();

		GL11.glPopMatrix();
	}

}
