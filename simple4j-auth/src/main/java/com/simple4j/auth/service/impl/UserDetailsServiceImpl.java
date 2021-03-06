package com.simple4j.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.autoconfigure.jwt.security.SecurityUtils;
import com.simple4j.auth.entity.User;
import com.simple4j.auth.mapper.UserMapper;
import com.simple4j.auth.service.IRoleMenuService;
import com.simple4j.auth.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import top.dcenter.ums.security.core.oauth.service.UmsUserDetailsService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author hyc
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UmsUserDetailsService {

	private final UserMapper userMapper;
	private final IUserRoleService userRoleService;
	private final IRoleMenuService roleMenuService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper
			.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, username));
		if (ObjectUtil.isEmpty(user)) {
			throw new UsernameNotFoundException("");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		Set<String> roleIds = userRoleService.getRoleIds(user.getId());
		for (String role : roleIds) {
			grantedAuthorities.add(SecurityUtils.createRoleAuthority(role));
		}
		Set<String> permissions = roleMenuService.getPermission(roleIds);
		for (String permission : permissions) {
			grantedAuthorities.add(SecurityUtils.createPermissionAuthority(permission));
		}

		if (StrUtil.isNotBlank(user.getTenantId())) {
			grantedAuthorities.add(SecurityUtils.createTenantAuthority(user.getTenantId()));
		}
		if (StrUtil.isNotBlank(user.getId())) {
			grantedAuthorities.add(SecurityUtils.createUserIdAuthority(user.getId()));
		}
		if (StrUtil.isNotBlank(user.getName())) {
			grantedAuthorities.add(SecurityUtils.createUserNameAuthority(user.getName()));
		}
		return org.springframework.security.core.userdetails.User.withUsername(user.getName())
			.password(user.getPassword())
			.authorities(grantedAuthorities)
			.build();
	}

	@Override
	public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		return this.loadUserByUsername(userId);
	}

	@Override
	public List<Boolean> existedByUsernames(String... usernames) {
		List<Boolean> result = new ArrayList<>();
		if (ArrayUtil.isEmpty(usernames)) {
			return result;
		}

		for (String username : usernames) {
			result.add(userMapper.existedByAccount(username));
		}
		return result;
	}
}
