class Vertex
{
	int number;
	int weight;
	public int numberOfItems;
	int upperBound;
	int lowerBound;
	public boolean active;
	public int color;
	Bid[] bid;
	
	public Vertex()
	{
		number=0;
		weight=0;
		numberOfItems=0;
		upperBound=0;
		lowerBound=0;
		active=false;
	}
	
	public void addVertex(int number, int weight, Bid[] bid)
	{
		this.number=number;
		this.weight=weight;
		this.bid=bid;
		this.numberOfItems=bid.length;
	}
	
	public void removeVertex()
	{
		this.number=0;
		this.weight=0;
		this.bid=null;
		this.numberOfItems=0;
		this.upperBound=0;
		this.lowerBound=0;
	}
	
	public String getItem(int itemCount)
	{
		return bid[itemCount].getItemName();
	}
	
	public int getUpperBound()
	{
		this.upperBound=0;
		double mantissa=(this.weight / 4.0);
		while(mantissa>=1)
			{	mantissa=mantissa-1;	this.upperBound=this.upperBound + 1;	}
		if(mantissa >= 0.5)	
			this.upperBound=this.upperBound + 1;
		return (this.upperBound);
	}
	
	public int getLowerBound()
	{
		this.lowerBound=0;
		double mantissa=((3.0 - 2.65) * (this.weight) / 2.0) + 0.34;
		while(mantissa>=1)
			{	mantissa=mantissa-1;	this.lowerBound=this.lowerBound + 1;	}
		if(mantissa >= 0.5)	
			this.lowerBound=this.lowerBound + 1;
		return (this.lowerBound);
	}
	
	public Bid[] getBid()
	{
		return this.bid;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
}