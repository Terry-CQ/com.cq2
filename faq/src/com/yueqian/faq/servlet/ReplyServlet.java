package com.yueqian.faq.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.yueqian.faq.common.Utils;
import com.yueqian.faq.domain.ReplyInfo;
import com.yueqian.faq.domain.UserInfo;
import com.yueqian.faq.service.ReplyService;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/replyServlet")
public class ReplyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = super.parseReqParam(req, "flag", String.class);
		if ("picture".equals(flag)) {
			String fileId = super.parseReqParam(req, "fileId", String.class);
			if (fileId != null) {
				File dir = super.getPictureDir();
				File file = new File(dir, fileId);
				// 判断文件是否存在
				if (file.exists()) {
					// 从数据中获取该文件的类型
					ReplyInfo info = ReplyService.getInstance().findInfoByFileId(fileId);
					// 返回应答
					resp.setContentType(info.getFileType());
					// 返回字节数据
					FileUtils.copyFile(file, resp.getOutputStream());
				}
			}
		} else if ("accept".equals(flag)) {
			Integer replyId = super.parseReqParam(req, "replyId", Integer.class);
			if (replyId != null) {
				// 采纳为最佳
				Integer probId = ReplyService.getInstance().acceptReply(replyId);
				resp.sendRedirect(req.getContextPath() + "/problemServlet?flag=detail&probId=" + probId);
				return;
			}
			resp.sendRedirect(req.getContextPath() + "/problemServlet");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(req)) {
			// 提交的回复
			ReplyInfo info = new ReplyInfo();
			List<FileItem> items = super.parseMultipartRequest(req);
			FileItem item;
			String fieldName, fileId;
			InputStream is;
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);
				if (item.isFormField()) {
					fieldName = item.getFieldName();
					switch (fieldName) {
					case "probId":
						info.setProblemId(Integer.parseInt(item.getString()));
						break;
					case "replyContent":
						info.setContent(item.getString("utf-8"));
						break;
					}
				} else {
					// 图片文件
					if ((is = item.getInputStream()).available() > 0) {
						fileId = Utils.createFileId();
						info.setFileType(item.getContentType());
						info.setPicId(fileId);
						File dir = super.getPictureDir();
						File file = new File(dir, fileId);
						FileUtils.copyInputStreamToFile(is, file);
					}
				}
			}
			// 提交回复的用户ID
			UserInfo userInfo = super.getLoginedUserInfo(req);
			info.setReplyUserId(userInfo.getUserId());
			// 回复的时间
			info.setReplyTime(new Date());
			// 保存回复
			boolean result = ReplyService.getInstance().saveReplyInfo(info);

			// 重定向到问题详细页面
			resp.sendRedirect(req.getContextPath() + "/problemServlet?flag=detail&probId=" + info.getProblemId()
					+ "&msgCode=" + (result ? "replySuccess" : "replyFailure"));
		}
	}
}
