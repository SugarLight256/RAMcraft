package ramcraft.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import ramcraft.RAMCraft;

public class ToolToothedSickle extends ItemTool {
	public static final Set<Block> effectiveAgainst = Sets.newHashSet(new Block[] { RAMCraft.blockRicePlant });

	public ToolToothedSickle(ToolMaterial material) {
		super(1.0f, material, effectiveAgainst);
		this.setHarvestLevel("ToothedSickle", 4);
	}

	public boolean canHarvestBlock(Block par1Block) {
		return false;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 1.0F;
	}
}
