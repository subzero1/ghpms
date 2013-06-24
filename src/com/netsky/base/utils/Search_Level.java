package com.netsky.base.utils;

public class Search_Level {
	private int id;
	private int level;
	private String name;

	public Search_Level() {
		super();

	}

	public Search_Level(int id, int level, String name) {
		super();
		this.id = id;
		this.level = level;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
