package buyNsell.ItemSearch;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class ListCategoryForm extends ActionForm
{
	String itemCategory=null;
	
	public String getItemCategory()
	{
		return this.itemCategory;
	}
	public void setItemCategory(String itemCategory)
	{
		this.itemCategory=itemCategory;
	}
	
	public void reset()
	{
		this.setItemCategory(null);
	}
}