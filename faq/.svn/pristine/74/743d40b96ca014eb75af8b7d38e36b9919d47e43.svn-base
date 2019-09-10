package com.yueqian.faq.common;

public enum DifficultyLevel {

	SIMPLE("简单", 1), NORMAL("一般", 2), JIAO_NAN("较难", 3), HARD("很难", 4);
	private int value;
	private String name;

	private DifficultyLevel(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static DifficultyLevel from(byte level) {
		DifficultyLevel[] array = DifficultyLevel.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].value == level) {
				return array[i];
			}
		}
		return null;
	}
}
