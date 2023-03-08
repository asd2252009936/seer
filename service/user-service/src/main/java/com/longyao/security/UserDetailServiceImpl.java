package com.longyao.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longyao.pojo.User;
import com.longyao.service.UserService;
//import com.longyao.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 匹配用户登录账号密码
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

//	@Autowired
//	RedisUtil redisUtil;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("id",Integer.valueOf(id));
		User user = userService.getOne(userQueryWrapper);
		if (user == null) {
			throw new UsernameNotFoundException("用户名或密码不正确");
		}

		return new AccountUser((long) user.getId(),user.getName(), user.getPassword(),getUserAuthority(user.getId()));
	}
	public List<GrantedAuthority> getUserAuthority(int id){

//		// 角色(ROLE_admin)、菜单操作权限 sys:user:list
//		Role role = roleService.getById(id);
//		String authority = role.getRoleName();  // ROLE_admin,ROLE_normal,sys:user:list,....
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("id",id);
		String authority = userService.getOne(userQueryWrapper).getIsVIP();
		//将角色名转换为ROLE_admin这种格式返回
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
	}
}
