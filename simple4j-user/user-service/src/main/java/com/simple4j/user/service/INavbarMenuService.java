package com.simple4j.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.NavbarMenu;
import com.simple4j.user.request.NavbarGrantRequest;
import com.simple4j.user.request.NavbarMenuAddOrUpdateRequest;
import com.simple4j.user.request.NavbarMenuAddRequest;
import com.simple4j.user.request.NavbarMenuDetailRequest;
import com.simple4j.user.request.NavbarMenuListRequest;
import com.simple4j.user.request.NavbarMenuPageRequest;
import com.simple4j.user.request.NavbarMenuRemoveRequest;
import com.simple4j.user.request.NavbarMenuUpdateRequest;
import com.simple4j.user.request.NavbarPermissionRequest;
import com.simple4j.user.response.NavbarMenuDetailResponse;
import com.simple4j.user.response.NavbarPermissionResponse;


/**
 * 服务类
 *
 * @author Blade
 * @since 2020-08-26
 */
public interface INavbarMenuService extends IService<NavbarMenu> {

	/**
	 * 详情
	 */
	NavbarMenuDetailResponse detail(NavbarMenuDetailRequest navbarMenuDetailRequest);

	/**
	 * 列表
	 */
	List<NavbarMenuDetailResponse> list(NavbarMenuListRequest navbarMenuListRequest);

	/**
	 * 自定义分页
	 */
	Page<NavbarMenuDetailResponse> page(NavbarMenuPageRequest navbarMenuPageRequest);

	/**
	 * 新增
	 */
	void add(NavbarMenuAddRequest navbarMenuAddRequest);

	/**
	 * 修改
	 */
	void update(NavbarMenuUpdateRequest navbarMenuUpdateRequest);

	/**
	 * 新增或修改
	 */
	void addOrUpdate(NavbarMenuAddOrUpdateRequest navbarMenuAddOrUpdateRequest);


	/**
	 * 删除
	 */
	void remove(NavbarMenuRemoveRequest navbarMenuRemoveRequest);

	/**
	 * 查询顶部菜单权限
	 *
	 * @param navbarDetailRequest
	 * @return
	 */
	NavbarPermissionResponse permission(NavbarPermissionRequest navbarDetailRequest);

	/**
	 * 顶部菜单分配
	 *
	 * @param navbarGrantRequest
	 */
	void grant(NavbarGrantRequest navbarGrantRequest);

	/**
	 * 顶部菜单分配
	 *
	 * @param navbarId
	 * @param menuIds
	 */
	void grant(Long navbarId, List<Long> menuIds);
}
