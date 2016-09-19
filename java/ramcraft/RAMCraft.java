package ramcraft;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;
import ramcraft.blocks.BlockRiceplant;
import ramcraft.blocks.BlockThreshBlock;
import ramcraft.items.GenericBlockItemRenderer;
import ramcraft.items.ItemCookedRice;
import ramcraft.items.OriginalSpawnEggs;
import ramcraft.items.ToolToothedSickle;
import ramcraft.items.seedRice;
import ramcraft.mob.EntityCombineMob;
import ramcraft.models.CombineModel;
import ramcraft.tab.tabRAMCraft;
import ramcraft.tileentity.RenderCombineMob;
import ramcraft.tileentity.RenderThreshBlock;
import ramcraft.tiles.TileEntityThreshBlock;

@Mod(modid = RAMCraft.MODID, version = RAMCraft.VERSION)
public class RAMCraft {
	public static final String MODID = "RAMCraft";
	public static final String VERSION = "0.1";

	// tabs
	public static final CreativeTabs tabRAMCraft = new tabRAMCraft("RAMCraft_tab");

	// materials
	public static ToolMaterial superWood = EnumHelper.addToolMaterial("superWood", 4, 59, 40, 0.0f, 0);
	public static ToolMaterial superStone = EnumHelper.addToolMaterial("superStone", 4, 131, 80, 0.0f, 0);
	public static ToolMaterial superIron = EnumHelper.addToolMaterial("superIron", 4, 250, 120, 0.0f, 0);
	public static ToolMaterial superGold = EnumHelper.addToolMaterial("superGold", 4, 32, 240, 0.0f, 0);
	public static ToolMaterial superDiamond = EnumHelper.addToolMaterial("superDiamon", 4, 1561, 160, 0.0f, 0);

	// crops
	public static Block blockRicePlant = new BlockRiceplant(Blocks.farmland);
	public static Item cropRiceStraw = new Item();// riceStraw -(脱穀thrash)>
													// straw&unhulledRice
	public static Item straw = new Item();//
	public static Item chaff = new Item();// もみ殻
	public static Item unhulledRice = new Item();// unhulledRice
													// -(もみすりshelling)>
													// chaff&brownRice
	public static Item brownRice = new Item();// 玄米
	public static Item rice = new Item();// お米
	public static Item seedRice = new seedRice(blockRicePlant, Blocks.farmland);// 種モミ
	public static Item riceFlour = new Item();// 米粉

	// Foods
	public static Item Cooked_rice = new ItemCookedRice(8, 1.0f, false);

	// Tools
	public static Item ToolWoodenToothedSickle = new ToolToothedSickle(superWood);
	public static Item ToolStoneToothedSickle = new ToolToothedSickle(superStone);
	public static Item ToolIronToothedSickle = new ToolToothedSickle(superIron);
	public static Item ToolGoldToothedSickle = new ToolToothedSickle(superGold);
	public static Item ToolDiamondToothedSickle = new ToolToothedSickle(superDiamond);

	// Eggs
	public static Item combineEgg = new OriginalSpawnEggs(0xFF0000, 0xFFFFFF);

	// Block Machines
	public static Block ThreshBlock = new BlockThreshBlock();

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		// itemInit
		InitItems(cropRiceStraw, "Rice Straw", "rice_straw", tabRAMCraft);
		InitItems(straw, "Straw", "straw", tabRAMCraft);
		InitItems(chaff, "Chaff", "chaff", tabRAMCraft);
		InitItems(unhulledRice, "Unhulled Rice", "unhulled_rice", tabRAMCraft);
		InitItems(brownRice, "BrownRice", "brown_rice", tabRAMCraft);
		InitItems(rice, "Rice", "rice", tabRAMCraft);
		InitItems(seedRice, "seedRice", "seed_rice", tabRAMCraft);
		InitItems(riceFlour, "Rice Flour", "rice_flour", tabRAMCraft);
		//
		InitItems(Cooked_rice, "Cooked Rice", "cooked_rice", tabRAMCraft);
		//
		InitItems(combineEgg, "Combine Spawn Egg", "spawn_egg", tabRAMCraft);

		// toolInit
		InitItems(ToolWoodenToothedSickle, "Wooden Thoothed Sickle", "wooden_sickle", tabRAMCraft);
		InitItems(ToolStoneToothedSickle, "Stone Thoothed Sickle", "stone_sickle", tabRAMCraft);
		InitItems(ToolIronToothedSickle, "Iron Thoothed Sickle", "iron_sickle", tabRAMCraft);
		InitItems(ToolGoldToothedSickle, "Gold Thoothed Sickle", "gold_sickle", tabRAMCraft);
		InitItems(ToolDiamondToothedSickle, "Diamond Thoothed Sickle", "diamond_sickle", tabRAMCraft);

		// blockInit
		InitBlocks(blockRicePlant, "RicePlant", "riceplant_stage_0", null, 0.5625F);
		InitBlocks(ThreshBlock, "Thresh Block", "ThreshBlock_render", tabRAMCraft, 0.5625F);

		// MobInit
		EntityRegistry.registerModEntity(EntityCombineMob.class, "SampleEntity", 0, this, 250, 1, false);

		// renderer Init
		registerRenderers();

		// Regist Recipes
		registRecipes();

	}

	private void registRecipes() {

		// ThreshBlock
		GameRegistry.addRecipe(new ItemStack(ThreshBlock), //
				" # ", //
				"WWW", //
				"W W", //
				'#', Items.iron_ingot, //
				'W', Blocks.planks);

		// toothed Sickles
		registSickleRecipes(ToolWoodenToothedSickle, new ItemStack(Blocks.planks));
		registSickleRecipes(ToolStoneToothedSickle, new ItemStack(Blocks.cobblestone));
		registSickleRecipes(ToolIronToothedSickle, new ItemStack(Items.iron_ingot));
		registSickleRecipes(ToolGoldToothedSickle, new ItemStack(Items.gold_ingot));
		registSickleRecipes(ToolDiamondToothedSickle, new ItemStack(Items.diamond));
	}

	private void registSickleRecipes(Item output, ItemStack materialItem) {

		GameRegistry.addRecipe(new ItemStack(output), //
				" ##", //
				"# l", //
				"  l", //
				'#', materialItem, //
				'l', Items.stick);

	}

	private void InitItems(Item tmpItem, String name, String texture, CreativeTabs tab) {

		tmpItem.setUnlocalizedName(name);
		tmpItem.setCreativeTab(tab);
		tmpItem.setTextureName(MODID + ":" + texture);

		GameRegistry.registerItem(tmpItem, name);

	}

	private void InitBlocks(Block tmpBlock, String name, String texture, CreativeTabs tab, float boundsY) {

		tmpBlock.setBlockName(name);
		tmpBlock.setBlockTextureName(MODID + ":" + texture);
		tmpBlock.setCreativeTab(tab);
		tmpBlock.setBlockBounds(0F, 0F, 0F, 1F, boundsY, 1F);

		GameRegistry.registerBlock(tmpBlock, name);

	}

	private void InitMobs() {

	}

	@SideOnly(Side.CLIENT)
	private void registerRenderers() {

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityThreshBlock.class, new RenderThreshBlock());

		RenderingRegistry.registerEntityRenderingHandler(EntityCombineMob.class,
				new RenderCombineMob(new CombineModel(), 0));

		registerItemRenderers();
	}

	@SideOnly(Side.CLIENT)
	private void registerItemRenderers() {

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ThreshBlock),
				new GenericBlockItemRenderer(new TileEntityThreshBlock(), new RenderThreshBlock()));

	}

}