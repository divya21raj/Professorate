package com.d2runltd.professorate;

import java.util.ArrayList;

public class Professor
{
	private String id;

	private String name;
	private String dept;

	private String link;

	private ArrayList<Rating> ratings;

	public Professor(String id, String name, String dept, String link, ArrayList<Rating> ratings)
	{
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.link = link;
		this.ratings = ratings;
	}

	public Professor()
	{}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDept()
	{
		return dept;
	}

	public void setDept(String dept)
	{
		this.dept = dept;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public ArrayList<Rating> getRatings()
	{
		return ratings;
	}

	public void setRatings(ArrayList<Rating> ratings)
	{
		this.ratings = ratings;
	}
}
