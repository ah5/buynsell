package buyNsell.AccountOperations.BuyerOperations.BidPosting;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.lang.*;

public class BidPostForm extends ActionForm
{
	String pi0=null;
	String pi1=null;
	String pi2=null;
	String pi3=null;
	String pi4=null;
	
	String bq0=null;
	String bq1=null;
	String bq2=null;
	String bq3=null;
	String bq4=null;
	
	String bp=null;
	String no=null;
	
	public String getno()
	{	
		return this.no;	
	}
	public void setno(String no)
	{
		this.no=no;
	}
	
	public String getpi0()
	{	
		return this.pi0;	
	}
	public void setpi0(String pi0)
	{
		this.pi0=pi0;
	}
	
	public String getpi1()
	{	
		return this.pi1;	
	}
	public void setpi1(String pi1)
	{
		this.pi1=pi1;
	}
	
	public String getpi2()
	{	
		return this.pi2;	
	}
	public void setpi2(String pi2)
	{
		this.pi2=pi2;
	}
	
	public String getpi3()
	{	
		return this.pi3;	
	}
	public void setpi3(String pi3)
	{
		this.pi3=pi3;
	}
	
	public String getpi4()
	{	
		return this.pi4;	
	}
	public void setpi4(String pi4)
	{
		this.pi4=pi4;
	}
	
	public String getbq0()
	{	
		return this.bq0;	
	}
	public void setbq0(String bq0)
	{
		this.bq0=bq0;
	}
	
	public String getbq1()
	{	
		return this.bq1;	
	}
	public void setbq1(String bq1)
	{
		this.bq1=bq1;
	}
	
	public String getbq2()
	{	
		return this.bq2;	
	}
	public void setbq2(String bq2)
	{
		this.bq2=bq2;
	}
	
	public String getbq3()
	{	
		return this.bq3;	
	}
	public void setbq3(String bq3)
	{
		this.bq3=bq3;
	}
	
	public String getbq4()
	{	
		return this.bq4;	
	}
	public void setbq4(String bq4)
	{
		this.bq4=bq4;
	}
	
	public String getbp()
	{	
		return this.bp;	
	}
	public void setbp(String bp)
	{
		this.bp=bp;
	}
	
	public boolean validator()
	{
		char [] chars;
		boolean errorExists=false;
		int noOfBids=Integer.parseInt(getno());
		if(noOfBids > 0)
		{
			if ((getpi0() == null) || (getpi0().length() < 1))
				errorExists=true;
			if ((getbq0() == null) || (getbq0().length() < 1))
				errorExists=true;
			chars=bq0.toCharArray();
			for(int i=0; i<chars.length; i++)
				if(! Character.isDigit(chars[i]))
					errorExists=true;	
			
			if ((getbp() == null) || (getbp().length() < 1))
				errorExists=true;
			chars=bp.toCharArray();
			for(int i=0; i<chars.length; i++)
				if(! Character.isDigit(chars[i]))
					errorExists=true;	
			if(noOfBids > 1)
			{
				if ((getpi1() == null) || (getpi1().length() < 1))
					errorExists=true;
				if ((getbq1() == null) || (getbq1().length() < 1))
					errorExists=true;
				chars=bq1.toCharArray();
				for(int i=0; i<chars.length; i++)
					if(! Character.isDigit(chars[i]))
						errorExists=true;	
				if(noOfBids > 2)
				{
					if ((getpi2() == null) || (getpi2().length() < 1))
						errorExists=true;
					if ((getbq2() == null) || (getbq2().length() < 1))
						errorExists=true;
					chars=bq2.toCharArray();
					for(int i=0; i<chars.length; i++)
						if(! Character.isDigit(chars[i]))
							errorExists=true;	
					if(noOfBids > 3)
					{
						if ((getpi3() == null) || (getpi3().length() < 1))
							errorExists=true;
						if ((getbq3() == null) || (getbq3().length() < 1))
							errorExists=true;
						chars=bq3.toCharArray();
						for(int i=0; i<chars.length; i++)
							if(! Character.isDigit(chars[i]))
								errorExists=true;	
						if(noOfBids > 4)
						{
							if ((getpi4() == null) || (getpi4().length() < 1))
								errorExists=true;
							if ((getbq4() == null) || (getbq4().length() < 1))
								errorExists=true;
							chars=bq4.toCharArray();
							for(int i=0; i<chars.length; i++)
								if(! Character.isDigit(chars[i]))
									errorExists=true;	
						}
					}
				}
			}
		}
		return errorExists;
	}
}



