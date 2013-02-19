package com.ForgeEssentials.DimensionControl;

import com.ForgeEssentials.util.OutputHandler;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker
{
	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		player.sendChatToPlayer("### onPlayerChangedDimension ###");
		OutputHandler.severe("### onPlayerChangedDimension ###");
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		player.sendChatToPlayer("### onPlayerRespawn ###");
		OutputHandler.severe("### onPlayerRespawn ###");
	}
}