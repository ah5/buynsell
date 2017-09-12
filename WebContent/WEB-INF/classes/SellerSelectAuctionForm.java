package buyNsell.AccountOperations.SellerOperations;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class SellerSelectAuctionForm extends ActionForm
{
	String auctionSelected=null;
	
	public String getAuctionSelected()
	{	
		return this.auctionSelected;	
	}
	public void setAuctionSelected(String auctionSelected)
	{
		this.auctionSelected=auctionSelected;
	}
	
	public boolean validator()
	{
		if((getAuctionSelected() == null) || (getAuctionSelected().length() < 1))
			return true;
		else
			return false;
	}
}



