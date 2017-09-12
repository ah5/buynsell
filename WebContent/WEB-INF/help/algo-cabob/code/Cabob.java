public class Cabob
{
	int fStar;		// fStar 	- value of best soln found so far
	Graph G;		// G		- the bid graph
	Graph[] subG;	//			- independent component bid graph
	DFSearch dfSearchObject;
	
	public Cabob()
	{
		fStar=0;
		G=new Graph();
		dfSearchObject=new DFSearch();
	}
	
	public int algorithm(Graph G, int g, int MIN)
	{
		// step 1 - applying special cases COMPLETE and NO_EDGES
		if(G.isComplete())
		{
			//only 1 of the remaining bids can be accepted
			//CABOB picks the bid with highest price
			//update fStar and prune the search path
		}
		
		if(! G.isEdges())
		{
			//accept all remaining bids
			//update fStar and prune the search path
		}
		
		//step 2 - running a depth first search on G
		subG=dfSearchObject.search(G);
		
		//step 3 - calculate upper bound for each component i in G
		G.calculateUpperBounds();
		
		//step 4 -
		if(G.getSumOfUpperBounds() <= MIN)
			return 0;
			
		//step 5 - applying special case INTEGER
				
		//step 6 - calculate lower bound for each component i in G
		G.calculateLowerBounds();
		
		//step 7 - find delta
		int delta = g + G.getSumOfLowerBounds() - fStar;
		
		//step 8 - 
		if(delta>0)
		{
			fStar = fStar + delta;
			MIN = MIN + delta;
		}
		
		//step 9 -
		if(!(G.getVertexCount() > 1))
		{
			Vertex removedVertex=G.root();
			G.removeBid(0);
			for(int j=0; j<G.getVertexCount(); j++)
			{
				if((G.getVertex(j) != removedVertex) 
				&& (G.itemCommonality(G.getVertex(j), removedVertex) == false))
						G.removeBid(j);
			}
			int fStarOld=fStar;
			int fIn=algorithm(G, g + removedVertex.getWeight(), MIN - removedVertex.getWeight());
			MIN = MIN + (fStar - fStarOld);
			for(int j=0; j<G.getVertexCount(); j++)
			{
				if((G.getVertex(j) != removedVertex) 
				&& (G.itemCommonality(G.getVertex(j), removedVertex) == false))
						G.addBid(G.getVertex(j).getWeight(), G.getVertex(j).getBid());
			}
			fStarOld=fStar;
			int fOut=algorithm(G, g, MIN);
			MIN = MIN + (fStar-fStarOld);
			G.addBid(removedVertex.getWeight(), removedVertex.getBid());
			return(fIn>fOut?fIn:fOut);
		}
		else
		{
			int fStarSolved=0;
			int hUnSolved=G.getSumOfUpperBounds();
			int lUnSolved=G.getSumOfLowerBounds();
			for(int i=0; i<subG.length; i++)
			{
				if((fStarSolved + hUnSolved)<=MIN)
					return 0;
				int gDashI=fStarSolved+(lUnSolved - G.getUpperBound(i));
				int fStarOld=fStar;
				int fStarI=algorithm(subG[i], g+gDashI, MIN-gDashI);
				MIN=MIN+(fStar - fStarOld);
				fStarSolved=fStarSolved+fStarI;
				hUnSolved=hUnSolved - G.getUpperBound(i);
				lUnSolved=lUnSolved - G.getLowerBound(i);
			}
			return fStarSolved;
		}
	}
	
	public static void main(String args[])
	{
		Cabob obj=new Cabob();
		
		obj.add();
		
		
	}
	
	public void add()
	{
		Bid[] bid=new Bid[4];
		bid[0]=new Bid("pencil",20); 
		bid[1]=new Bid("pen",5);
		bid[2]=new Bid("rubber",2);
		bid[3]=new Bid("ink",4);  
		G.addBid(2700, bid);
		
		bid=null;
		bid=new Bid[4];
		bid[0]=new Bid("pencil",20); 
		bid[1]=new Bid("pen",5);
		bid[2]=new Bid("rubber",2);
		bid[3]=new Bid("ink",4);  
		G.addBid(5000, bid);
		
		bid=null;
		bid=new Bid[4];
		bid[0]=new Bid("pencil",20); 
		bid[1]=new Bid("pen",5);
		bid[2]=new Bid("rubber",2);
		bid[3]=new Bid("ink",4);  
		G.addBid(3700, bid);
		
		int result=algorithm(G, 0 ,0);
		System.out.println(result);
	}
}