package com.yueqian.faq.common;

public enum EnglishLevel {
	COLLEGE_4("大学4级", 1), COLLEGE_6("大学6级", 2), MAJOR_4("专业4级", 3), MAJOR_8("专业8级", 4), NORMAL("一般水平", 5);
	private int value;
	private String name;

	private EnglishLevel(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static EnglishLevel from(byte level) {
		EnglishLevel[] array = EnglishLevel.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].value == level) {
				return array[i];
			}
		}
		return null;
	}

}
