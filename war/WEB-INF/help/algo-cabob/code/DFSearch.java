public class DFSearch
{
	int c;
	Graph subG[];
	
	public DFSearch()
	{
		c=0;
		subG=null;
	}
	
    protected void visit(Graph g, Vertex u)
    {
		g.mark(u, true);             	//marking vertex u as true
		g.paint(u, 1);      			//painting gray to vertex u
		
		int dp = g.getDegree(u);		//finding no of vertices for vertex u

		for (int i = 0; i < dp; i++)
		{
			Vertex v = g.getSuccessor(i, u);
	    	if(v.color==0) 
	    		visit(g, v);
		}
		g.paint(u, 2);
		g.mark(u, false);       
		subG[c]= new Graph();
		c++;
    }
 
    public Graph[] search(Graph g)
    {
		g.paint(0);  						//painting white to all vertices
		g.mark(false);      				//marking all vertices false
		visit(g, g.root());					//visiting 
		return subG;
    }
}