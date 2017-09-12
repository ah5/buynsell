package com.buynsell.utils.auctionsheduler;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import com.buynsell.businessobjects.Auction;
import com.buynsell.databaseconnection.JdbcUtil;

public class AutoScheduler
{
	SchedulerFactory schedFact;
	Scheduler sched;
	JobDetail jobDetail;
	JobDataMap jobData;
	SimpleTrigger triggerJob;
	
	public AutoScheduler() throws Exception
	{
		SchedulerFactory schedFact=new StdSchedulerFactory();
		sched=schedFact.getScheduler();
	}
	
	public void scheduleCurrentAuction(Auction a) throws Exception
	{
		sched.start();
		jobDetail=new JobDetail(a.getAuctionid(), sched.DEFAULT_GROUP, StartAuctionsJob.class);
		jobData=new JobDataMap();
		jobData.put("auctionID", a.getAuctionid());
		jobData.put("catalogID", a.getCatalogid());
		jobDetail.setJobDataMap(jobData);
		triggerJob=new SimpleTrigger(a.getAuctionid(),sched.DEFAULT_GROUP, JdbcUtil.fromSQLStringToJavaDate(a.getStartingdate()), null, 0, 0L);
		sched.scheduleJob(jobDetail, triggerJob);
	}
	
	public void unscheduleCurrentAuction(Auction a) throws Exception
	{
		sched.start();
		String jobid="j"+a.getAuctionid();
		jobDetail=new JobDetail(jobid, sched.DEFAULT_GROUP, StopAuctionsJob.class);
		jobData=new JobDataMap();
		jobData.put("auctionID", a.getAuctionid());
		jobData.put("catalogID", a.getCatalogid());
		jobDetail.setJobDataMap(jobData);
		triggerJob=new SimpleTrigger("j"+a.getAuctionid(),sched.DEFAULT_GROUP, JdbcUtil.fromSQLStringToJavaDate(a.getEndingdate()), null, 0, 0L);
		sched.scheduleJob(jobDetail, triggerJob);
	}
	
	/*
	public void scheduleAuctions() throws Exception
	{
		sched.start();
		ArrayList auctionsToBeActive=JdbcData.loadAllAuctionsToBeActive();
		if(auctionsToBeActive.size()	>	0)
		{
			for(int i=0; i<auctionsToBeActive.size(); i++)
			{
				Auction a=(Auction)auctionsToBeActive.get(i);
				jobDetail=new JobDetail(a.getAuctionid(), sched.DEFAULT_GROUP, StartAuctionsJob.class);
				jobData=new JobDataMap();
				jobData.put("auctionID", a.getAuctionid());
				jobData.put("catalogID", a.getCatalogid());
				jobDetail.setJobDataMap(jobData);
				triggerJob=new SimpleTrigger(a.getAuctionid(),sched.DEFAULT_GROUP, JdbcUtil.fromSQLStringToJavaDate(a.getStartingdate()), null, 0, 0L);
				sched.scheduleJob(jobDetail, triggerJob);
			}
		}
	}*/
	
	
	/*
	public void unscheduleAuctions() throws Exception
	{
		sched.start();
		ArrayList auctionsToBeInActive=JdbcData.loadAllAuctionsToBeInActive();
		if(auctionsToBeInActive.size()	>	0)
		{
			for(int i=0; i<auctionsToBeInActive.size(); i++)
			{
				Auction a=(Auction)auctionsToBeInActive.get(i);
				
				jobDetail=new JobDetail(a.getAuctionid(), sched.DEFAULT_GROUP, StopAuctionsJob.class);
				jobData=new JobDataMap();
				jobData.put("auctionID", a.getAuctionid());
				jobData.put("catalogID", a.getCatalogid());
				jobDetail.setJobDataMap(jobData);
				triggerJob=new SimpleTrigger(a.getAuctionid(),sched.DEFAULT_GROUP, JdbcUtil.fromSQLStringToJavaDate(a.getStartingdate()), null, 0, 0L);
				sched.scheduleJob(jobDetail, triggerJob);
			}
		}
	}*/
	
}