package com.yueqian.faq.domain;

public class JSONResp {
	// 业务应答码:0--正常；-1--参数不足；-2--权限不足
	private int code;
	private String msg;
	private Object data;

	public int getCode() {
		return code;
	}

	/**
	 * 业务应答码
	 * 
	 * @param code
	 *            0--正常；-1--参数不足；-2--权限不足
	 */
	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
