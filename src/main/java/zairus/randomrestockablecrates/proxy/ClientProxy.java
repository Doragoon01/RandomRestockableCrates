package zairus.randomrestockablecrates.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zairus.randomrestockablecrates.RRCConstants;
import zairus.randomrestockablecrates.block.RRCBlock;
import zairus.randomrestockablecrates.client.renderer.tileentity.TileEntityCrateRenderer;
import zairus.randomrestockablecrates.tileentity.TileEntityCrate;

public class ClientProxy extends CommonProxy
{
	public static final Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		;
	}
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		;
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e)
	{
		;
	}
	
	@Override
	public void registerItem(Item item, String name, int meta, boolean model)
	{
		super.registerItem(item, name, meta, model);
		
		if (model && item != null)
		{
			registerModel(item, meta, name);
		}
	}
	
	public void registerModel(Item item, int meta, String name)
	{
		RenderItem renderItem = mc.getRenderItem();
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(RRCConstants.MODID + ":" + name, "inventory");
		
		renderItem.getItemModelMesher().register(item, meta, modelResourceLocation);
		
		if (meta == 0)
			ModelBakery.registerItemVariants(item, new ResourceLocation(RRCConstants.MODID, name));
	}
	
	@Override
	public void registerItemModel(Item item, int meta)
	{
		String itemId = RRCConstants.MODID + ":"; // + item.getModName();
		mc.getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(itemId, "inventory"));
	}
	
	@Override
	public void registerItemModel(Item item, int meta, String texture)
	{
		ModelBakery.registerItemVariants(item, new ResourceLocation(RRCConstants.MODID, texture));
		
		String itemId = RRCConstants.MODID + ":" + texture;
		mc.getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(itemId, "inventory"));
	}
	
	public void registerBlockModel(RRCBlock block, String modName)
	{
		registerBlockModel(block, 0, modName);
	}
	
	@Override
	public void registerBlockModel(Block block, int meta, String modName)
	{
		Item item = Item.getItemFromBlock(block);
		
		if (item != null)
		{
			registerItemModel(item, meta, modName);
		}
	}
	
	@Override
	public void initTESR()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrate.class, new TileEntityCrateRenderer());
	}
}
