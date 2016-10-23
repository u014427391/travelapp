package com.tsc.entity;

public class MenuItem {

	/** 菜单的id. */
	private int id;

	/** 菜单的图标id. */
	private int iconId;

	/** 菜单的文本. */
	private String text;

	/** 菜单的描述. */
	private String mark;

	public MenuItem(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public MenuItem(String text) {
		super();
		this.text = text;
	}

	public MenuItem() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

}
