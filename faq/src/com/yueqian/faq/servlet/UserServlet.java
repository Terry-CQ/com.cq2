package com.yueqian.faq.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yueqian.faq.common.Constants;
import com.yueqian.faq.common.EnglishLevel;
import com.yueqian.faq.common.JobStatus;
import com.yueqian.faq.common.Utils;
import com.yueqian.faq.domain.JSONResp;
import com.yueqian.faq.domain.UserInfo;
import com.yueqian.faq.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private Font font = new Font("Arial", Font.BOLD, 18);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = super.parseReqParam(req, "flag", String.class);
		if ("checkAccount".equals(flag)) {
			String account = super.parseReqParam(req, "account", String.class);
			JSONResp jr = new JSONResp();
			if (account == null || account.length() == 0) {
				jr.setCode(-1);
				jr.setMsg("未提供有效的参数!");
			} else {
				UserInfo info = UserService.getInstance().findInfoByAccount(account, false);
				if (info != null) {
					jr.setData(false);
				} else {
					jr.setData(true);
				}
			}
			super.jsonResponse(resp, jr);
		} else if ("verifyCode".equals(flag)) {
			// 得到长度为4的随机字符串
			String code = Utils.getRandomString(4);
			req.getSession().setAttribute(Constants.SESSION_KEY_VERIFY_CODE, code);
			// 生成图片
			BufferedImage img = new BufferedImage(60, 24, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = (Graphics2D) img.getGraphics();
			g.setColor(Color.LIGHT_GRAY);
			Rectangle2D rect = new Rectangle2D.Float(0, 0, 60, 24);
			g.fill(rect);
			g.setColor(Color.BLUE);
			g.setFont(font);
			g.drawString(code, 5, 20);

			// 返回应答
			resp.setContentType("image/png");
			//
			ImageIO.write(img, "png", resp.getOutputStream());
		} else if ("logout".equals(flag)) {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/problemServlet");
		} else if ("preRegister".equals(flag)) {
			super.setStatisticsInfo(req);
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flag = super.parseReqParam(req, "flag", String.class);
		if ("login".equals(flag)) {
			// 检查验证码是否正确
			String verifyCode = super.parseReqParam(req, "verifyCode", String.class);
			String code = (String) req.getSession().getAttribute(Constants.SESSION_KEY_VERIFY_CODE);
			if (verifyCode == null || verifyCode.length() < 4 || !verifyCode.equals(code)) {
				req.setAttribute("msg", "验证码有误!");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				return;
			}
			// account,pwd
			String account = super.parseReqParam(req, "account1", String.class);
			String pwd = super.parseReqParam(req, "pwd1", String.class);
			// 根据账号获取用户信息
			UserInfo userInfo = UserService.getInstance().findInfoByAccount(account, false);
			if (userInfo == null) {
				req.setAttribute("msg", "账号不存在!");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				if (userInfo.getPwd().equals(pwd)) {
					req.getSession().setAttribute(Constants.SESSION_KEY_LOGIN_INFO, userInfo);
					resp.sendRedirect(req.getContextPath() + "/problemServlet");
				} else {
					req.setAttribute("msg", "密码不正确!");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
			}
		} else if ("register".equals(flag)) {
			// 用户注册
			UserInfo userInfo = new UserInfo();
			String account = super.parseReqParam(req, "account", String.class);
			String alias = super.parseReqParam(req, "name", String.class);
			String pwd = super.parseReqParam(req, "pwd", String.class);
			String confirmPwd = super.parseReqParam(req, "confirmPwd", String.class);
			String email = super.parseReqParam(req, "email", String.class);
			String mobile = super.parseReqParam(req, "mobile", String.class);

			List<String> msg = new ArrayList<>();
			if (account == null || account.length() < 3) {
				msg.add("账号不符合要求！");
			}
			if (alias == null || alias.length() < 3) {
				msg.add("昵称不符合要求！");
			}
			if (pwd == null || pwd.length() < 3 || confirmPwd == null || confirmPwd.length() < 3) {
				msg.add("密码不符合要求！");
			}
			if (pwd != null && confirmPwd != null && !confirmPwd.equals(pwd)) {
				msg.add("两次输入的密码不一致！");
			}
			if (email == null || email.length() < 3) {
				msg.add("电子邮箱不符合要求！");
			}
			if (mobile == null || mobile.length() < 3) {
				msg.add("手机号码不符合要求！");
			}
			if (msg.size() > 0) {
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/reigster.jsp").forward(req, resp);
				return;
			}
			userInfo.setAdmin(false);
			userInfo.setAccount(account);
			userInfo.setAlias(alias);
			userInfo.setPwd(pwd);
			userInfo.setEmial(email);
			userInfo.setMobile(mobile);

			String qq = super.parseReqParam(req, "qq", String.class);
			String college = super.parseReqParam(req, "college", String.class);
			String major = super.parseReqParam(req, "major", String.class);
			String idcard = super.parseReqParam(req, "idcard", String.class);
			String dob = super.parseReqParam(req, "dob", String.class);

			String english = super.parseReqParam(req, "english", String.class);
			String jobType = super.parseReqParam(req, "jobType", String.class);
			String company = super.parseReqParam(req, "company", String.class);

			userInfo.setQq(qq);
			userInfo.setCollege(college);
			userInfo.setMajor(major);
			userInfo.setIdcard(idcard);
			if (english != null && english.length() > 0) {
				userInfo.setEnglishLevel(EnglishLevel.from(Byte.parseByte(english)));
			}
			if (dob != null && dob.length() > 0) {
				userInfo.setDob(Utils.parseDate(dob));
			}
			if (jobType != null && jobType.length() > 0) {
				userInfo.setJobStatus(JobStatus.from(Byte.parseByte(jobType)));
			}
			userInfo.setCpmpany(company);
			// 保存新用户的信息
			boolean result = UserService.getInstance().saveInfo(userInfo);
			msg.add(result ? "注册成功!" : "注册失败!");
			req.setAttribute("register_msg", msg);
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
	}
}
