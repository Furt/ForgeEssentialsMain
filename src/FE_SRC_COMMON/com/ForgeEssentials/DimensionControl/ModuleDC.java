package com.ForgeEssentials.DimensionControl;

import java.io.File;
import java.util.HashMap;

import com.ForgeEssentials.api.data.DataStorageManager;
import com.ForgeEssentials.api.modules.FEModule;
import com.ForgeEssentials.api.modules.FEModule.Config;
import com.ForgeEssentials.api.modules.FEModule.Init;
import com.ForgeEssentials.api.modules.FEModule.Instance;
import com.ForgeEssentials.api.modules.FEModule.ModuleDir;
import com.ForgeEssentials.api.modules.FEModule.PostInit;
import com.ForgeEssentials.api.modules.FEModule.PreInit;
import com.ForgeEssentials.api.modules.FEModule.ServerInit;
import com.ForgeEssentials.api.modules.FEModule.ServerPostInit;
import com.ForgeEssentials.api.modules.FEModule.ServerStop;
import com.ForgeEssentials.api.modules.event.FEModuleInitEvent;
import com.ForgeEssentials.api.modules.event.FEModulePostInitEvent;
import com.ForgeEssentials.api.modules.event.FEModulePreInitEvent;
import com.ForgeEssentials.api.modules.event.FEModuleServerInitEvent;
import com.ForgeEssentials.api.modules.event.FEModuleServerPostInitEvent;
import com.ForgeEssentials.api.modules.event.FEModuleServerStopEvent;
import com.ForgeEssentials.api.permissions.IPermRegisterEvent;
import com.ForgeEssentials.api.permissions.PermRegister;
import com.ForgeEssentials.core.ForgeEssentials;
import com.ForgeEssentials.tickets.Ticket;

import cpw.mods.fml.common.registry.GameRegistry;

@FEModule(name = "DimensionControl", parentMod = ForgeEssentials.class, isCore = false, configClass = ConfigDC.class)
public class ModuleDC
{
	@Instance
	public static ModuleDC	instance;
	public static boolean	enable;
	
	@Config
	public ConfigDC			config;
	@ModuleDir
	public File				moduleDir;
	public PlayerTracker	tracker = new PlayerTracker();
	public DimData			defaultData;
	
	private HashMap<Integer, DimData> map = new HashMap();
	
	@PermRegister(ident = "FE-DimensionControl")
	public void registerPerm(IPermRegisterEvent e)
	{
		
	}

	@Init
	public void init(FEModuleInitEvent e)
	{
		GameRegistry.registerPlayerTracker(tracker);
	}
	
	@ServerInit
	public void serverInit(FEModuleServerInitEvent e)
	{
		loadAll();
	}
	
	@ServerStop
	public void serverStop(FEModuleServerStopEvent e)
	{
		saveAll();
	}
	
	public void loadAll()
	{
		for (Object obj : DataStorageManager.getReccomendedDriver().loadAllObjects(DimData.class))
		{
			DimData data = (DimData) obj;
			if(data.name.equals("DEFAULT"))
				defaultData = data;
			else
				map.put(data.dimID, data);
		}
	}

	public void saveAll()
	{
		DataStorageManager.getReccomendedDriver().saveObject(defaultData);
		for (DimData data : map.values())
		{
			DataStorageManager.getReccomendedDriver().saveObject(data);
		}
	}
	
	public DimData getDimData(int id)
	{
		if(map.containsKey(id))
		{
			return map.get(id);
		}
		else
		{
			return defaultData;
		}
	}
}
