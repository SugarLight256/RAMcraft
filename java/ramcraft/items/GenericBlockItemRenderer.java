package ramcraft.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import ramcraft.tiles.TileEntityThreshBlock;

public class GenericBlockItemRenderer implements IItemRenderer {

	private TileEntity tile;
	// private TileEntitySpecialRenderer renderer;

	public GenericBlockItemRenderer(TileEntity tile, TileEntitySpecialRenderer renderer) {
		this.tile = tile;
		// this.renderer = renderer;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		if (tile instanceof TileEntityThreshBlock) {
			GL11.glTranslated(0, 0, 0);
		}
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0, 0, 0, 0);
		GL11.glPopMatrix();

	}

}
