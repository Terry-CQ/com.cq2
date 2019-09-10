package com.yueqian.faq.domain;

import java.util.List;

public class ProblemDetailInfo extends ProblemInfo {

	// 最佳回复
	private ReplyInfo bestReply;
	// 所有回复
	private List<ReplyInfo> replies;

	public ReplyInfo getBestReply() {
		return bestReply;
	}

	public void setBestReply(ReplyInfo bestReply) {
		this.bestReply = bestReply;
	}

	public List<ReplyInfo> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyInfo> replies) {
		this.replies = replies;
	}

}
