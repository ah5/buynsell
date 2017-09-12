import java.io.*;

class Graph
{
	int numberOfVertices;
	int numberOfEdges;
	int sumUB;
	int sumLB;
	
	Vertex[] vertex=new Vertex[20];
	Edge[] edge=new Edge[190];
	
	public Graph()
	{
		numberOfVertices=0;
		numberOfEdges=0;
		sumUB=0;
		sumLB=0;
	}
	
	public void addBid(int weight, Bid bid)
	{
		vertex [numberOfVertices] = new Vertex();
		vertex [numberOfVertices].addVertex (numberOfVertices, weight, bid);
		if(isVertices() == true)
		{
			for(int i=0; i<numberOfVertices; i++) 	
			{
				if((vertex [i].getItem()).equals(bid.getItemName()))
				{
					edge [numberOfEdges] = new Edge();
					edge [numberOfEdges].addEdge(vertex [i], vertex [numberOfVertices]);
					numberOfEdges++;
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
	
	public void viewBids() throws Exception
	{
		OutputStream  fout=new FileOutputStream("c:/CABOB_Bidlog.txt");
		OutputStream bout = new BufferedOutputStream(fout);
		OutputStreamWriter out=new OutputStreamWriter(bout);
		
		for(int i=0; i<numberOfVertices; i++)
		{
			out.write("\nBid "+i);
			out.write("\nItem Name "+vertex[i].getItem());
			out.write("\nWeight "+vertex[i].getWeight());
			out.write("\nNo of shared vertices is "+getDegree(vertex[i]));
			for(int j=0; j<numberOfEdges; j++)
			{
				if(	vertex[i] == edge[j].getFirstVertex() )
				{
					out.write("\n\tShared Bid "+edge[j].getSecondVertex().getVertexID());
					out.write("\n\tItem Name  "+edge[j].getSecondVertex().getItem());
					out.write("\n\tWeight     "+edge[j].getSecondVertex().getWeight());
				}
				else if(	vertex[i] == edge[j].getSecondVertex() )
				{
					out.write("\n\tShared Bid "+edge[j].getFirstVertex().getVertexID());
					out.write("\n\tItem Name  "+edge[j].getFirstVertex().getItem());
					out.write("\n\tWeight     "+edge[j].getFirstVertex().getWeight());
				}
			}
		}
		out.flush();
		/*
		for(int i=0; i<numberOfVertices; i++)
		{
			System.out.println("Bid "+i);
			System.out.println("Item Name "+vertex[i].getItem());
			System.out.println("Weight "+vertex[i].getWeight());
			System.out.println("No of shared vertices is "+getDegree(vertex[i]));
			for(int j=0; j<numberOfEdges; j++)
			{
				if(	vertex[i] == edge[j].getFirstVertex() )
				{
					System.out.println("\tShared Bid "+edge[j].getSecondVertex().getVertexID());
					System.out.println("\tItem Name  "+edge[j].getSecondVertex().getItem());
					System.out.println("\tWeight     "+edge[j].getSecondVertex().getWeight());
				}
				else if(	vertex[i] == edge[j].getSecondVertex() )
				{
					System.out.println("\tShared Bid "+edge[j].getFirstVertex().getVertexID());
					System.out.println("\tItem Name  "+edge[j].getFirstVertex().getItem());
					System.out.println("\tWeight     "+edge[j].getFirstVertex().getWeight());
				}
			}
			System.out.println();
		}*/
	}
	
	public boolean itemCommonality(Vertex u, Vertex v)
	{
		boolean common=false;
		int pos1=0, pos2=0;
		for(int i=0; i<numberOfVertices; i++)
		{
			if(vertex[i] == u)		pos1=i;		
			else if(vertex[i] == v)	pos2=i;		
		}
		String n1=vertex[pos1].getItem();
		String n2=vertex[pos2].getItem();
		if(n1.equals(n2))	common=true;
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