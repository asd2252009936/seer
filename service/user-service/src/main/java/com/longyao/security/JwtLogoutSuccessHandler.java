package com.longyao.security;

import com.alibaba.fastjson.JSONObject;
import com.longyao.res.Result;
import com.longyao.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();

		response.setHeader(jwtUtils.getHeader(), "");

		Result result = Result.ok("退出成功");

		outputStream.write(JSONObject.toJSONBytes(result));

		outputStream.flush();
		outputStream.close();
	}
}
