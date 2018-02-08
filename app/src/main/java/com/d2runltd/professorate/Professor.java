package com.d2runltd.professorate;

public class Professor
{
	private String id;

	private String name;
	private String dept;

	private String link;

	private String imageLink;

	public Professor(String id, String name, String dept, String link, String imageLink)
	{
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.link = link;
		this.imageLink = imageLink;
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

	public String getImageLink()
	{
		return imageLink;
	}

	public void setImageLink(String imageLink)
	{
		this.imageLink = imageLink;
	}
}
