package buyNsell.AccountOperations.SellerOperations.CatalogPosting;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.lang.*;

public class AddProductToCatalogForm extends ActionForm
{
	String id;
	String name;
	String desc;
	String cat;
	String qty;
	String wt;
	
	static boolean errorExists=false;
	
	public String getId()
	{	
		return this.id;	
	}
	public void setId(String id)
	{
		this.id=id;
	}
	
	public String getName()
	{	
		return this.name;	
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getDesc()
	{	
		return this.desc;	
	}
	public void setDesc(String desc)
	{
		this.desc=desc;
	}
	
	public String getCat()
	{	
		return this.cat;	
	}
	public void setCat(String cat)
	{
		this.cat=cat;
	}
	
	public String getQty()
	{	
		return this.qty;	
	}
	public void setQty(String qty)
	{
		this.qty=qty;
	}
	
	public String getWt()
	{	
		return this.wt;	
	}
	public void setWt(String wt)
	{
		this.wt=wt;
	}
	
	public void clearFormValues()
	{
		id=null;
		name=null;
		desc=null;
		cat=null;
		qty=null;
		wt=null;
		errorExists=false;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		clearFormValues();
	}
	
	public boolean validator() 
	{
			char [] chars;
		if ((id == null) || (id.length() < 1))
			errorExists=true;
		if ((name == null) || (name.length() < 1))
			errorExists=true;
		if ((desc == null) || (desc.length() < 1))
			errorExists=true;
		if ((cat == null) || (cat.length() < 1))
			errorExists=true;
		if ((qty == null) || (qty.length() < 1))
			errorExists=true;
		chars=qty.toCharArray();
		for(int i=0; i<chars.length; i++)
			if(! Character.isDigit(chars[i]))
				errorExists=true;		
				
		if ((wt == null) || (wt.length() < 1))
			errorExists=true;
		chars=wt.toCharArray();
		for(int i=0; i<chars.length; i++)
			if(! Character.isDigit(chars[i]))
				errorExists=true;	
				
		return errorExists;
	}
}