package com.longyao.security;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longyao.pojo.User;
import com.longyao.res.Meta;
import com.longyao.service.UserService;
import com.longyao.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();

		// 生成jwt，并放置到请求头中
		String jwt = jwtUtils.generateToken(authentication.getName());
		response.setHeader("Access-Control-Expose-Headers",jwtUtils.getHeader());
		response.setHeader(jwtUtils.getHeader(), jwt);
		System.out.println("================="+jwt+"================");
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("name",authentication.getName());
		User one = userService.getOne(userQueryWrapper);
		one.setPassword("");
		Meta meta = Meta.ok(one).msg(jwt);
		outputStream.write(JSONObject.toJSONBytes(meta));

		outputStream.flush();
		outputStream.close();
	}

}
