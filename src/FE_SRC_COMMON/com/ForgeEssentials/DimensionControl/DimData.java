package com.ForgeEssentials.DimensionControl;

import java.util.List;

import com.ForgeEssentials.api.data.ITaggedClass;
import com.ForgeEssentials.api.data.SaveableObject;
import com.ForgeEssentials.api.data.SaveableObject.Reconstructor;
import com.ForgeEssentials.api.data.SaveableObject.SaveableField;
import com.ForgeEssentials.api.data.SaveableObject.UniqueLoadingKey;

@SaveableObject
public class DimData
{
	@UniqueLoadingKey
	@SaveableField
	public String			name;
	@SaveableField(nullableField = true)
	public Integer			dimID;
	@SaveableField(nullableField = true)
	public Integer			gm;
	@SaveableField(nullableField = true)
	public Boolean			ownInv;
	//@SaveableField
	public List<String>		blockBlacklist;
	//@SaveableField
	public List<String>		blockWhitelist;
	
	public DimData(Integer dimID)
	{
		this.dimID = dimID;
		if(this.dimID == null)
		{
			this.name = "DEFAULT";
		}
		else
		{
			this.name = "" + dimID;
		}
	}
	
	@Reconstructor
	private static DimData reconstruct(ITaggedClass tag)
	{
		DimData data = new DimData((Integer) tag.getFieldValue("dimID"));
		
		data.gm = (Integer) tag.getFieldValue("gm");
		data.ownInv = (Boolean) tag.getFieldValue("ownInv");
		data.blockBlacklist = (List<String>) tag.getFieldValue("blockBlacklist");
		data.blockWhitelist = (List<String>) tag.getFieldValue("blockWhitelist");
		
		return data;
	}
}