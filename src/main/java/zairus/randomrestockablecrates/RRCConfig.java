package zairus.randomrestockablecrates;

import java.io.File;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.config.Configuration;

public class RRCConfig
{
	public static Configuration config;
	
	public static int tier1RestockTime = 6000;
	public static int tier2RestockTime = 24000;
	public static int tier3RestockTime = 24000;
	public static int tier4RestockTime = 24000;
	
	public static NBTTagList crateTier1 = new NBTTagList();
	public static NBTTagList crateTier2 = new NBTTagList();
	public static NBTTagList crateTier3 = new NBTTagList();
	public static NBTTagList crateTier4 = new NBTTagList();
	
	public static NBTTagList rewardCaseLoot = new NBTTagList();
	
	public static void init(File file)
	{
		config = new Configuration(file);
		
		config.load();
		
		config.setCategoryComment("CRATE_RESTOCK_TIMES", "Restock time in ticks, 24000 ticks is one Minecraft day.");
		
		tier1RestockTime = config.getInt("tier1RestockTime", "CRATE_RESTOCK_TIMES", tier1RestockTime, 10, 300000, "Ticks for tier 1 crate.");
		tier2RestockTime = config.getInt("tier2RestockTime", "CRATE_RESTOCK_TIMES", tier2RestockTime, 10, 300000, "Ticks for tier 2 crate.");
		tier3RestockTime = config.getInt("tier3RestockTime", "CRATE_RESTOCK_TIMES", tier3RestockTime, 10, 300000, "Ticks for tier 3 crate.");
		tier4RestockTime = config.getInt("tier4RestockTime", "CRATE_RESTOCK_TIMES", tier4RestockTime, 10, 300000, "Ticks for tier 4 crate.");
		
		config.setCategoryComment("TIER1_POOL", "Pool configuration for tier 1 crates.");
		int tier1Elements = config.getInt("totalElements", "TIER1_POOL", 2, 1, 1000, "Total number of elements(items) on tier 1 pool.");
		crateTier1 = getDefaultTagList(tier1Elements, "TIER1_POOL", new String[] { "minecraft:wooden_sword" });
		
		config.setCategoryComment("TIER2_POOL", "Pool configuration for tier 2 crates.");
		int tier2Elements = config.getInt("totalElements", "TIER2_POOL", 2, 1, 1000, "Total number of elements(items) on tier 2 pool.");
		crateTier2 = getDefaultTagList(tier2Elements, "TIER2_POOL", new String[] { "minecraft:stone_sword" });
		
		config.setCategoryComment("TIER3_POOL", "Pool configuration for tier 3 crates.");
		int tier3Elements = config.getInt("totalElements", "TIER3_POOL", 2, 1, 1000, "Total number of elements(items) on tier 3 pool.");
		crateTier3 = getDefaultTagList(tier3Elements, "TIER3_POOL", new String[] { "minecraft:iron_sword" });
		
		config.setCategoryComment("TIER4_POOL", "Pool configuration for tier 4 crates.");
		int tier4Elements = config.getInt("totalElements", "TIER4_POOL", 2, 1, 1000, "Total number of elements(items) on tier 4 pool.");
		crateTier4 = getDefaultTagList(tier4Elements, "TIER4_POOL", new String[] { "minecraft:diamond_sword", "randomrestockablecrates:reward_case" });
		
		config.setCategoryComment("REWARD_CASE_POOL", "Pool configuration for reward case.");
		int rewardCaseItems = config.getInt("totalElements", "REWARD_CASE_POOL", 3, 1, 1000, "Total number of elements(items) for reward case pool.");
		rewardCaseLoot = getDefaultTagList(rewardCaseItems, "REWARD_CASE_POOL", new String[] { "minecraft:diamond", "minecraft:emerald", "minecraft:iron_ingot", "minecraft:gold_ingot" });
		
		config.save();
	}
	
	private static NBTTagList getDefaultTagList(int elements, String category, String[] default_item_array)
	{
		NBTTagList list = new NBTTagList();
		
		String default_item;
		
		for (int i = 0; i < elements; ++i)
		{
			if (i < default_item_array.length)
				default_item = default_item_array[i];
			else
				default_item = default_item_array[default_item_array.length - 1];
			
			int weight = config.getInt("weight_item" + i, category, 1, 1, 20, "Weight for generation on crate.");
			String itemId = config.getString("itemId_item" + i, category, default_item, "Named item id.");
			int min = config.getInt("min_item" + i, category, 0, 0, 25565, "Minimum count for the item stack.");
			int max = config.getInt("max_item" + i, category, 1, 1, 25565, "Maximum count for the item stack.");
			int meta = config.getInt("meta_item" + i, category, 0, 0, 25565, "Metadata for the item.");
			String nbt = config.getString("nbt_item" + i, category, "", "Additional NBT Data for the item.");
			
			NBTTagCompound item = new NBTTagCompound();
			item.setInteger("weight", weight);
			item.setString("itemId", itemId);
			item.setInteger("min", min);
			item.setInteger("max", max);
			item.setInteger("meta", meta);
			item.setString("NBTData", nbt);
			list.appendTag(item);
		}
		
		return list;
	}
}
