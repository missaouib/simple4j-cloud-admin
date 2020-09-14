package com.simple4j.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.Tenant;
import com.simple4j.user.request.TenantAddOrUpdateRequest;
import com.simple4j.user.request.TenantDetailRequest;
import com.simple4j.user.request.TenantListRequest;
import com.simple4j.user.request.TenantPageRequest;
import com.simple4j.user.request.TenantRemoveRequest;
import com.simple4j.user.request.TenantUpdateRequest;
import com.simple4j.user.response.TenantDetailResponse;

/**
 * 服务类
 *
 * @author Chill
 */
public interface ITenantService extends IService<Tenant> {

	/**
	 * 根据租户编号获取实体
	 *
	 * @param tenantId
	 * @return
	 */
	Tenant getByTenantId(String tenantId);

	/**
	 * 详情
	 */
	TenantDetailResponse detail(TenantDetailRequest tenantDetailRequest);

	/**
	 * 列表 租户表
	 */
	List<TenantDetailResponse> list(TenantListRequest tenantListRequest);

	/**
	 * 自定义分页 租户表
	 */
	Page<TenantDetailResponse> page(TenantPageRequest tenantPageRequest);

	/**
	 * 新增 租户表
	 */
	void add(TenantAddOrUpdateRequest tenantAddRequest);

	/**
	 * 修改 租户表
	 */
	void update(TenantUpdateRequest tenantUpdateRequest);

	/**
	 * 删除 租户表
	 */
	void remove(TenantRemoveRequest tenantRemoveRequest);


	/**
	 * 新增或修改 租户
	 *
	 * @param tenantAddOrUpdateRequest
	 */
	void addOrUpdate(TenantAddOrUpdateRequest tenantAddOrUpdateRequest);
}
