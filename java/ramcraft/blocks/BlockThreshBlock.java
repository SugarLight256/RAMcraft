package ramcraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ramcraft.tiles.TileEntityThreshBlock;

public class BlockThreshBlock extends BlockContainer {

	public BlockThreshBlock() {
		super(Material.wood);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		super.onBlockPlacedBy(world, x, y, z, p_149689_5_, p_149689_6_);
		int dir = MathHelper.floor_double((p_149689_5_.rotationYaw * 4F) / 360F + 0.5D) & 3;

		((TileEntityThreshBlock) world.getTileEntity(x, y, z)).setDir(dir);

	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {// 透過?
		return false;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityThreshBlock();
	}

}
