package buyNsell.AccountOperations.BuyerOperations.BidPosting;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class BidForAuctionForm extends ActionForm
{
	String cid;
	
	public String getCid()
	{
		return this.cid;
	}
	public void setCid(String cid)
	{
		this.cid=cid;
	}
}