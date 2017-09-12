class Edge
{
	Vertex u;
	Vertex v;
	
	public Edge()
	{
		this.u=null;
		this.v=null;
	}
	
	public void addEdge(Vertex u, Vertex v)
	{
		this.u=u;
		this.v=v;
	}
	
	public void removeEdge()
	{
		this.u=null;
		this.v=null;
	}
	
	public Vertex getFirstVertex()
	{
		return this.u;
	}
	
	public Vertex getSecondVertex()
	{
		return this.v;
	}
}