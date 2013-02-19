package com.ForgeEssentials.DimensionControl;

import java.io.File;
import java.util.Arrays;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.Configuration;

import com.ForgeEssentials.api.modules.ModuleConfigBase;

public class ConfigDC extends ModuleConfigBase
{
	public Configuration	config;

	public ConfigDC(File file)
	{
		super(file);
	}

	@Override
	public void init()
	{
		config = new Configuration(file, true);
		String cat = "DimensionControl";
		
		ModuleDC.enable = config.get(cat, "enable", false, "Guess what this does?").getBoolean(true);
		
		String subcat = cat + ".default";
		config.addCustomCategoryComment(subcat, "This config will be used for all dimensions without specific sattings.");
		
		DimData data = new DimData(null);
		
		data.gm = config.get(subcat, "gm", 0, "Gamemode in the dimension").getInt();
		data.ownInv = config.get(subcat, "ownInv", true, "Seperate inventory in this dimension").getBoolean(true);
		data.blockBlacklist = Arrays.asList(config.get(subcat, "blockBlacklist", new String [] {}, "Blocks that can not be used in this dimension.").valueList);
		data.blockWhitelist = Arrays.asList(config.get(subcat, "blockWhitelist", new String [] {}, "Blocks that are allowed here. Overrides the default blacklist.").valueList);
		
		ModuleDC.instance.defaultData = data;
	}

	@Override
	public void forceSave()
	{
		
	}

	@Override
	public void forceLoad(ICommandSender sender)
	{
		
	}
}