package buyNsell.Utils;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateID 
{
	String id;
	
	public String getCatalogID()
  	{
  		String temp="C";
  		temp=temp+(genID().substring(0,5));
  		return temp;
  	}
  	
  	public String getAuctionID()
  	{
  		String temp="A";
  		temp=temp+(genID().substring(0,5));
  		return temp;
  	}
  	
  	public String getProductID()
  	{
  		String temp="P";
  		temp=temp+(genID().substring(0,5));
  		return temp;
  	}
  	
  	public String getBidID()
  	{
  		String temp="B";
  		temp=temp+(genID().substring(0,5));
  		return temp;
  	}
  	
  	public String genID () 
  	{
  		try 
    	{     
      		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");    
      		String randomNum = new Integer( prng.nextInt() ).toString();   
      		id=Integer.toString(Math.abs(Integer.parseInt(randomNum)));
    	}
    	catch ( NoSuchAlgorithmException ex ) 
    	{
      		System.err.println(ex);
    	}
    	return id;
  	}
  	
  	public static void main(String args[])
  	{
  		GenerateID o=new GenerateID();
  		System.out.println(o.getCatalogID());
  		System.out.println(o.getAuctionID());
  		System.out.println(o.getProductID());
  	}
}

  