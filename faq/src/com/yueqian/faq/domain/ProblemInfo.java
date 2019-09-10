package com.yueqian.faq.domain;

import java.util.Date;

import com.yueqian.faq.common.DifficultyLevel;
import com.yueqian.faq.common.ProblemStatus;

public class ProblemInfo {
	private Integer problemId;
	private Integer categoryId;
	private String title;
	private DifficultyLevel level;
	private Integer score;
	private String content;
	private String fileId;
	private String fileName;
	private String fileType;
	private Integer submitUserId;
	private ProblemStatus status;
	private Date submitTime;

	private UserInfo submitUser;
	private CategoryInfo categoryInfo;

	public UserInfo getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(UserInfo submitUser) {
		this.submitUser = submitUser;
	}

	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}

	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DifficultyLevel getLevel() {
		return level;
	}

	public void setLevel(DifficultyLevel level) {
		this.level = level;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getSubmitUserId() {
		return submitUserId;
	}

	public void setSubmitUserId(Integer submitUserId) {
		this.submitUserId = submitUserId;
	}

	public ProblemStatus getStatus() {
		return status;
	}

	public void setStatus(ProblemStatus status) {
		this.status = status;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

}
