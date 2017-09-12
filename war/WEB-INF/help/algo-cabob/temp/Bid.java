class Bid
{
	String itemName;
	int itemQty;
	
	public Bid(String itemName, int itemQty)
	{
		this.itemName=itemName;
		this.itemQty=itemQty;
	}
	
	public String getItemName()
	{
		return this.itemName;
	}
	
	public int getItemQty()
	{
		return this.itemQty;
	}
}