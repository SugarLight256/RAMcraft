package ramcraft.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ramcraft.RAMCraft;

public class BlockRiceplant extends BlockBush implements IGrowable {
	protected int maxGrowthStage = 7;

	private Block soilBlock;

	@SideOnly(Side.CLIENT)
	protected IIcon[] iIcon;

	public BlockRiceplant(Block soil) {
		soilBlock = soil;
		setTickRandomly(true);
		float f = 0.5F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		setHardness(5F);
		setHarvestLevel("ToothedSickle", 4);
		setStepSound(soundTypeGrass);
		disableStats();
	}

	@Override
	protected boolean canPlaceBlockOn(Block parBlock) {
		return parBlock == soilBlock;
	}

	public void incrementGrowStage(World parWorld, Random parRand, int parX, int parY, int parZ) {
		int growStage = parWorld.getBlockMetadata(parX, parY, parZ) + MathHelper.getRandomIntegerInRange(parRand, 2, 5);

		if (growStage > maxGrowthStage) {
			growStage = maxGrowthStage;
		}

		parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
	}

	@Override
	public Item getItemDropped(int metadata, Random parRand, int parFortune) {
		return metadata == maxGrowthStage ? RAMCraft.cropRiceStraw : null;// 成長しきってないとドロップしない
	}

	@Override
	public int getRenderType() {
		return 1; // クロステクスチャ
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int parSide, int parGrowthStage) {
		return iIcon[parGrowthStage];
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta) {
		ItemStack stack = player.inventory.getCurrentItem();
		return stack.getItem().getHarvestLevel(stack, "ToothedSickle") == 4;// 持っているアイテムが鎌のときだけ
	}

	@Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_) {
		return p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_).canSustainPlant(p_149718_1_, p_149718_2_,
				p_149718_3_ - 1, p_149718_4_, ForgeDirection.UP, this)// デフォルト
				&& p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_) == soilBlock// 畑じゃないとアイテム化(畑踏みつぶし)
				&& p_149718_1_.getBlockMetadata(p_149718_2_, p_149718_3_ - 1, p_149718_4_) > 0;// 畑が湿っていないとアイテム化
	}

	@Override
	public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {// 成長しきったかのbool
		return parWorld.getBlockMetadata(parX, parY, parZ) != 7;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random parRand, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
	}

	@Override
	public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
		incrementGrowStage(parWorld, parRand, parX, parY, parZ);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister parIIconRegister) {
		iIcon = new IIcon[maxGrowthStage + 1];
		for (int i = 0; i < maxGrowthStage + 1; i++) {
			iIcon[i] = parIIconRegister.registerIcon(RAMCraft.MODID + ":" + "riceplant_stage_" + i);
		}
	}
}