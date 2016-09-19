package ramcraft.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import ramcraft.RAMCraft;

public class ItemCookedRice extends ItemFood {
	public ItemCookedRice(int val, float saturation, boolean wolf) {

		super(val, saturation, wolf);

		GameRegistry.addSmelting(RAMCraft.seedRice, new ItemStack(this), 0.15f);

	}
}
