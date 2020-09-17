package com.simple4j.user.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.api.base.Page;
import com.simple4j.user.mapper.NavbarMapper;
import com.simple4j.user.entity.Navbar;
import com.simple4j.user.mapstruct.NavbarMapStruct;
import com.simple4j.user.request.NavbarAddOrUpdateRequest;
import com.simple4j.user.request.NavbarAddRequest;
import com.simple4j.user.request.NavbarDetailRequest;
import com.simple4j.user.request.NavbarListRequest;
import com.simple4j.user.request.NavbarPageRequest;
import com.simple4j.user.request.NavbarRemoveRequest;
import com.simple4j.user.request.NavbarUpdateRequest;
import com.simple4j.user.response.NavbarDetailResponse;
import com.simple4j.user.service.INavbarMenuService;
import com.simple4j.user.service.INavbarService;
import com.simple4j.user.util.SecurityUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务实现类
 *
 * @author Blade
 * @since 2020-08-26
 */
@Service
@RequiredArgsConstructor
public class NavbarServiceImpl implements INavbarService {

	private final NavbarMapper navbarMapper;
	private final NavbarMapStruct navbarMapStruct;
	private final INavbarMenuService navbarMenuService;

	@Override
	public NavbarDetailResponse detail(NavbarDetailRequest navbarDetailRequest) {
		Navbar detail = navbarMapper.getOne(
				Wrappers.<Navbar>lambdaQuery().eq(Navbar::getId, navbarDetailRequest.getId()));
		return navbarMapStruct.toVo(detail);
	}

	@Override
	public List<NavbarDetailResponse> list(NavbarListRequest navbarListRequest) {
		LambdaQueryWrapper<Navbar> queryWrapper = Wrappers.<Navbar>lambdaQuery()
				.eq(Navbar::getTenantId,
						SecurityUtils.getTenantId());
		List<Navbar> pages = navbarMapper.list(queryWrapper);
		return navbarMapStruct.toVo(pages);
	}

	@Override
	public Page<NavbarDetailResponse> page(NavbarPageRequest navbarPageRequest) {
		LambdaQueryWrapper<Navbar> queryWrapper = Wrappers.<Navbar>lambdaQuery();
		IPage<Navbar> page = navbarMapper.page(
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
						navbarPageRequest.getPageNo(), navbarPageRequest.getPageSize()),
				queryWrapper);
		Page<Navbar> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
				page.getRecords());
		return navbarMapStruct.toVo(pages);
	}

	@Override
	public boolean add(NavbarAddRequest navbarAddRequest) {
		Navbar navbar = navbarMapStruct.toPo(navbarAddRequest);
		navbar.setTenantId(SecurityUtils.getTenantId());
		return navbarMapper.save(navbar);
	}

	@Override
	public boolean update(NavbarUpdateRequest navbarUpdateRequest) {
		Navbar navbar = navbarMapStruct.toPo(navbarUpdateRequest);
		navbar.setTenantId(SecurityUtils.getTenantId());
		return navbarMapper.updateByIdBool(navbar);
	}

	@Override
	public boolean addOrUpdate(NavbarAddOrUpdateRequest navbarAddOrUpdateRequest) {
		Navbar navbar = navbarMapStruct.toPo(navbarAddOrUpdateRequest);
		navbar.setTenantId(SecurityUtils.getTenantId());
		return navbarMapper.saveOrUpdate(navbar);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean remove(NavbarRemoveRequest navbarRemoveRequest) {
		return navbarMapper.removeByIds(navbarRemoveRequest.getIds());
	}
}
