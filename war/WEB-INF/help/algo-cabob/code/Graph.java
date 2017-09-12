class Graph
{
	int numberOfVertices;
	int numberOfEdges;
	int sumUB;
	int sumLB;
	
	Vertex[] vertex;
	Edge[] edge;
	
	public Graph()
	{
		numberOfVertices=0;
		numberOfEdges=0;
		sumUB=0;
		sumLB=0;
	}
	
	public void addBid(int weight, Bid[] bid)
	{
		vertex [numberOfVertices] = new Vertex();
		if(isVertices() == true)
		{
			for(int i=0; i<numberOfVertices; i++) 				//for each vertex
			{
				for(int j=0; j<(vertex [i].numberOfItems); j++)	//for each item in the vertex
				{
					for(int k=0; k < bid.length; k++)			//for each item in the new bid
					{
						if((vertex [i].getItem(j)).equals(bid [k].getItemName()))
						{
							vertex [numberOfVertices].addVertex (numberOfVertices, weight, bid);
							edge [numberOfEdges] = new Edge();
							edge [numberOfEdges].addEdge(vertex [i], vertex [numberOfVertices]);
							numberOfEdges++;
						}
					}
				}
			}
		}
		numberOfVertices++;
	}
	
	public void removeBid(int count)
	{
		int i,j;
		Vertex v1, v2;
		for(i=0; i<numberOfEdges; i++)
		{
			v1=edge[i].getFirstVertex();
			v2=edge[i].getSecondVertex();
			if( (vertex[count] == v1) || (vertex[count] == v2) )
			{
				edge[i].removeEdge();
				for(j=i; j<numberOfEdges; j++)
				{
					if(j==(numberOfEdges-1))
						edge[j].removeEdge();
					else
						edge[j]=edge[j+1];
				}
				edge[j]=null;
				numberOfEdges=numberOfEdges-1;
			}
		}
		vertex[count].removeVertex();
		for(i=count; i<numberOfVertices; i++)
		{
			if(i==(numberOfVertices-1))
				vertex[i].removeVertex();
			else
				vertex[i]=vertex[i+1];
		}
		vertex[i]=null;
		numberOfVertices=numberOfVertices-1;	
	}
	
	public boolean itemCommonality(Vertex u, Vertex v)
	{
		boolean common=false;
		int itemsU=0, itemsV=0;
		int pos1=0, pos2=0;
		for(int i=0; i<numberOfVertices; i++)
		{
			if(vertex[i] == u)
			{	itemsU=vertex[i].numberOfItems;		pos1=i;		}
			else if(vertex[i] == v)	
			{	itemsV=vertex[i].numberOfItems; 	pos2=i;		}
		}
		for(int j=0; j<itemsU; j++)
		{
			String n1=vertex[pos1].getItem(j);
			for(int k=0; k<itemsV; k++)
			{
				String n2=vertex[pos2].getItem(k);
				if(n1.equals(n2))	common=true;
			}
		}
		return common;
	}
	
	public void calculateUpperBounds()
	{
		this.sumUB=0;
		for(int i=0; i<numberOfVertices; i++)
			this.sumUB = this.sumUB + vertex [i].getUpperBound();
	}
	
	public void calculateLowerBounds()
	{
		this.sumLB=0;
		for(int i=0; i<numberOfVertices; i++)
			this.sumLB = this.sumLB + vertex [i].getLowerBound();
	}
	
	public int getSumOfUpperBounds()
	{
		return sumUB;
	}
	
	public int getSumOfLowerBounds()
	{
		return sumLB;
	}
	
	public int getVertexCount()
	{
		return numberOfVertices;
	}
	
	public int getUpperBound(int count)
	{
		return vertex[count].getUpperBound();
	}
	
	public int getLowerBound(int count)
	{
		return vertex[count].getLowerBound();
	}
	
	public Vertex getVertex(int count)
	{
		return vertex[count];
	}
	
	public boolean isVertices()
	{
		if(numberOfVertices > 0)
			return true;
		else
			return false;
	}
	
	public boolean isEdges()
	{
		if(numberOfEdges > 0)
			return true;
		else
			return false;
	}
	
	public boolean isComplete()
	{
		if(numberOfEdges == (numberOfVertices*(numberOfVertices-1)/2))
			return true;
		else
			return false;
		/*boolean complete=true;
		Vertex v1, v2;
		Edge e=new Edge();
		
		if(numberOfVertices>1)
		{
			for(int i=0; i<numberOfVertices; i++)
			{
				v1 = vertex [i];
				for(int j=(i+1); j<numberOfVertices; j++)
				{
					v2 = vertex [j];
					e.addEdge (v1, v2);
					for(int k=0; k<numberOfEdges; k++)
					{
						if(e != edge[k])	
							complete=false;
					}
				}
			}
		}
		return complete;*/
	}
	
	public void mark(boolean flag)
	{
		for(int i=0; i<numberOfVertices; i++)
			mark(vertex[i], flag);
	}
	
	public boolean mark(Vertex v, boolean flag)
	{
		if(v==null)
			return false;
		for(int i=0; i<numberOfVertices; i++)
		{
			if(vertex[i] == v)
				vertex[i].active = flag;
		}
		return true;
	}
	
	public Vertex root()
	{
		return vertex[0];
	}
	
	public void paint(int color)
	{
		for(int i=0; i<numberOfVertices; i++)
			paint(vertex[i], color);
	}
	
	public void paint(Vertex v, int color)
	{
		for(int i=0; i<numberOfVertices; i++)
		{
			if(vertex[i] == v)
				vertex[i].color=color;
		}
	}
	
	public int getDegree(Vertex v)
	{
		int edgesForVertex=0;
		for(int i=0; i<numberOfEdges; i++)
		{
			if(( edge[i].getFirstVertex() == v ) ||
			   ( edge[i].getSecondVertex() == v))
			   		edgesForVertex++;
		}
		return edgesForVertex;
	}
	
	public Vertex getSuccessor(int i, Vertex v)
	{
		if(v==null || i<0) return null;
		int k=0;
		boolean edgeExist=false;
		for(int j=0; j<numberOfVertices; j++)
		{
			for(int x=0; x<numberOfEdges; x++)
				if(edge[x].getFirstVertex()==v && edge[x].getSecondVertex()==vertex[j])
					edgeExist=true;
			if(edgeExist && k++ == i)
				return vertex[j];
		}
		return null;
	}
}