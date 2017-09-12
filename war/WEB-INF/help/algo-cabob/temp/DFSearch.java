public class DFSearch
{
	int c;
	Graph[] subG=new Graph[100];
	
	public DFSearch()
	{
		c=0;
	}
	
    protected void visit(Graph g, Vertex u)
    {
		g.mark(u, true);             	//marking vertex u as true
		g.paint(u, 1);      			//painting gray to vertex u
		
		int dp = g.getDegree(u);		//finding no of edges for vertex u
		
		for (int i = 0; (dp>0)&&(i<dp); i++)
		{
			Vertex v = g.getSuccessor(i, u);
			if(v!=null)
				if(v.color==0) 
	    			visit(g, v);
		}
		
		g.paint(u, 2);
		g.mark(u, false);       
		subG[c]=g;
		c++;
    }
 
    public Graph[] search(Graph g)
    {
		g.paint(0);  						//painting white to all vertices
		g.mark(false);      				//marking all vertices false
		visit(g, g.root());					//visiting from root vertex
		return subG;
    }
}