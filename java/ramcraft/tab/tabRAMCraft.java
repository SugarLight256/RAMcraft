package ramcraft.tab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ramcraft.RAMCraft;

public class tabRAMCraft extends CreativeTabs {
	public tabRAMCraft(String label) {
		super(label);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return RAMCraft.Cooked_rice;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "Rice&Machine Craft";

	}
}
