package com.yueqian.faq.common;

public enum JobStatus {
	IN_JOB("在职", 1), IN_SCHOOL("学生在读", 2), NO_JOB("待业", 3);
	private int value;
	private String name;

	private JobStatus(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static JobStatus from(byte level) {
		JobStatus[] array = JobStatus.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].value == level) {
				return array[i];
			}
		}
		return null;
	}
}
