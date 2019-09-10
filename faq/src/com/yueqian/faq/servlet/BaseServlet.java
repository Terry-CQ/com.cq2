package com.yueqian.faq.servlet;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yueqian.faq.common.Constants;
import com.yueqian.faq.common.Utils;
import com.yueqian.faq.domain.JSONResp;
import com.yueqian.faq.domain.StatisticsInfo;
import com.yueqian.faq.domain.UserInfo;
import com.yueqian.faq.service.CommonService;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ObjectMapper mapper = new ObjectMapper();
	private ServletFileUpload sfu = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(524288);
		ServletContext sc = super.getServletContext();
		String tempDir = sc.getRealPath("/temp");
		dfif.setRepository(new File(tempDir));
		sfu = new ServletFileUpload(dfif);
	}

	/**
	 * 设置统计数据
	 * 
	 * @param req
	 */
	public void setStatisticsInfo(HttpServletRequest req) {
		StatisticsInfo info = CommonService.getInstance().findStatisticsInfo();
		req.setAttribute(Constants.COMMON_STATISTICS_INFO_KEY, info);
	}

	/**
	 * 解析多部分表单请求
	 * 
	 * @param req
	 * @return
	 */
	public List<FileItem> parseMultipartRequest(HttpServletRequest req) {
		List<FileItem> list = null;
		try {
			list = sfu.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 解析请求参数值，转换为特定类型返回
	 * 
	 * @param req
	 * @param paraName
	 * @return
	 */
	public <T> T parseReqParam(HttpServletRequest req, String paraName, Class<T> clz) {
		String value = req.getParameter(paraName);
		T result = null;
		if (value != null && value.length() > 0) {
			// String,Integer,Short,Long....
			if (clz == String.class || Number.class.isAssignableFrom(clz)) {
				try {
					result = clz.getDeclaredConstructor(String.class).newInstance(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (clz == Date.class) {
				// 日期类型对象
				Date temp = Utils.parseDate(value);
				if (temp != null) {
					try {
						result = clz.getDeclaredConstructor(long.class).newInstance(temp.getTime());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	/**
	 * 返回JSON应答
	 * 
	 * @param resp
	 * @param jr
	 */
	public void jsonResponse(HttpServletResponse resp, JSONResp jr) {
		resp.setContentType("application/json;charset=utf-8");
		try {
			mapper.writeValue(resp.getWriter(), jr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取图片的保存目录
	 * 
	 * @return
	 */
	public File getPictureDir() {
		return new File(super.getServletContext().getRealPath(Constants.PICTURE_DIR));
	}

	public UserInfo getLoginedUserInfo(HttpServletRequest req) {
		return (UserInfo) req.getSession().getAttribute(Constants.SESSION_KEY_LOGIN_INFO);
	}
}
