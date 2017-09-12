// Cabob.java 	COMBINATORIAL AUCTION BRANCH ON BIDS
import java.io.*;

public class Cabob
{
	int fStar;						// fStar 	- value of best soln found so far
	Graph G;						// G		- the bid graph
	Graph[] subG=new Graph[100];	//			- independent component bid graph
	DFSearch dfSearchObject;
	
	
	
	public Cabob()
	{
	}
	
	public int algorithm(Graph G, int g, int MIN) throws Exception
	{
		OutputStream  fout=new FileOutputStream("c:/CABOB_Processlog.txt");
		OutputStream bout = new BufferedOutputStream(fout);
		OutputStreamWriter out=new OutputStreamWriter(bout);
		
		G.viewBids();
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
		
		out.write("\nSum of Upper Bounds="+G.getSumOfUpperBounds());
		out.write("\nMIN="+MIN);
		out.flush();		
		//step 5 - applying special case INTEGER
		
				
		//step 6 - calculate lower bound for each component i in G
		G.calculateLowerBounds();
		
		//step 7 - find delta
		int delta = g + G.getSumOfLowerBounds() - fStar;
		
		//System.out.println("delta="+delta);//
		out.write("\ng="+g);
		out.write("\nSum of Lower Bounds="+G.getSumOfLowerBounds());
		out.write("\nfStar="+fStar);
		out.write("\nDelta(g + sumoflowerBounds - fStar)="+delta);
		out.flush();
		
		//step 8 - 
		if(delta>0)
		{
			fStar = fStar + delta;
			MIN = MIN + delta;
			
			out.write("\nfStar="+fStar);
			out.write("\nMIN="+MIN);
		}
		
		//System.out.println("fstar="+fStar);//
		//System.out.println("MIN="+MIN);//
		
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
	
	public static void main(String args[]) throws Exception
	{
		Cabob obj=new Cabob();
		obj.fStar=0;
		obj.G=new Graph();
		obj.add();
		obj.G.viewBids();
		obj.dfSearchObject=new DFSearch();
		System.out.println("call to cabob process..................");
		int result=obj.algorithm(obj.G, 0 ,0);
		
		//System.out.println(result);
	}
	
	public void add()
	{
		Bid bid0=new Bid("pencil",20); 
		G.addBid(2700, bid0);
		
		Bid bid1=new Bid("pencil",10);
		G.addBid(5000, bid1);
		
		Bid bid2=new Bid("pen",5);
		G.addBid(3700, bid2);
		
		Bid bid3=new Bid("ink",51);
		G.addBid(3000, bid3);
	}
}