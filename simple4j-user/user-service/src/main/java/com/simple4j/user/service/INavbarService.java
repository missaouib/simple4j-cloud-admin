package com.simple4j.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.Navbar;
import com.simple4j.user.request.NavbarAddOrUpdateRequest;
import com.simple4j.user.request.NavbarAddRequest;
import com.simple4j.user.request.NavbarDetailRequest;
import com.simple4j.user.request.NavbarListRequest;
import com.simple4j.user.request.NavbarPageRequest;
import com.simple4j.user.request.NavbarRemoveRequest;
import com.simple4j.user.request.NavbarUpdateRequest;
import com.simple4j.user.response.NavbarDetailResponse;


/**
 * 服务类
 *
 * @author Blade
 * @since 2020-08-26
 */
public interface INavbarService extends IService<Navbar> {

	/**
	 * 详情
	 */
	NavbarDetailResponse detail(NavbarDetailRequest navbarDetailRequest);

	/**
	 * 列表
	 */
	List<NavbarDetailResponse> list(NavbarListRequest navbarListRequest);

	/**
	 * 自定义分页
	 */
	Page<NavbarDetailResponse> page(NavbarPageRequest navbarPageRequest);

	/**
	 * 新增
	 */
	void add(NavbarAddRequest navbarAddRequest);

	/**
	 * 修改
	 */
	void update(NavbarUpdateRequest navbarUpdateRequest);

	/**
	 * 新增或修改
	 */
	void addOrUpdate(NavbarAddOrUpdateRequest navbarAddOrUpdateRequest);


	/**
	 * 删除
	 */
	void remove(NavbarRemoveRequest navbarRemoveRequest);

}
