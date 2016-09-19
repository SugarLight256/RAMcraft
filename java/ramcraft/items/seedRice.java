package ramcraft.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class seedRice extends ItemSeeds implements IPlantable {

	private Block plantBlock;// 植物
	private Block soilBlock;// 地面

	public seedRice(Block plant, Block soil) {
		super(plant, soil);
		plantBlock = plant;
		soilBlock = soil;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {
		if (side != 1) {
			return false;
		} else if (player.canPlayerEdit(x, y, z, side, itemStack)
				&& player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
			if (world.getBlock(x, y, z) == soilBlock && world.isAirBlock(x, y + 1, z)// ココだけ編集した(==soilBlockのとこ)
					&& world.getBlockMetadata(x, y, z) > 0) {// 濡れてなきゃダメ

				world.setBlock(x, y + 1, z, this.plantBlock);
				--itemStack.stackSize;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
