package com.tsc.entity;

import java.io.Serializable;

/**
 * 
 */
public class Parameter implements Serializable, Comparable<Parameter> 
{
	private static final long serialVersionUID = 2721340807561333705L;
	
	private String name;//参数名
	private String value;//参数值

	public Parameter()
	{
		super();
	}

	public Parameter(String name, String value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "Parameter [name=" + name + ", value=" + value + "]";
	}

	@Override
	public boolean equals(Object arg0)
	{
		if (null == arg0)
		{
			return false;
		}
		if (this == arg0)
		{
			return true;
		}
		if (arg0 instanceof Parameter)
		{
			Parameter param = (Parameter) arg0;

			return this.getName().equals(param.getName()) && this.getValue().equals(param.getValue());
		}
		return false;
	}

	@Override
	public int compareTo(Parameter param)
	{
		int compared;
		compared = name.compareTo(param.getName());
		if (0 == compared)
		{
			compared = value.compareTo(param.getValue());
		}
		return compared;
	}
}
