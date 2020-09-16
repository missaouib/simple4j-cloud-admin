package com.simple4j.user.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.user.base.Page;
import com.simple4j.user.common.constant.CommonConstant;
import com.simple4j.user.common.util.SecurityUtils;
import com.simple4j.user.mapper.DeptMapper;
import com.simple4j.user.mapper.RoleMapper;
import com.simple4j.user.mapper.TenantMapper;
import com.simple4j.user.entity.Dept;
import com.simple4j.user.entity.Role;
import com.simple4j.user.entity.Tenant;
import com.simple4j.user.mapstruct.TenantMapStruct;
import com.simple4j.user.request.TenantAddOrUpdateRequest;
import com.simple4j.user.request.TenantDetailRequest;
import com.simple4j.user.request.TenantListRequest;
import com.simple4j.user.request.TenantPageRequest;
import com.simple4j.user.request.TenantRemoveRequest;
import com.simple4j.user.request.TenantUpdateRequest;
import com.simple4j.user.response.TenantDetailResponse;
import com.simple4j.user.service.ITenantService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@AllArgsConstructor
public class TenantServiceImpl implements ITenantService {

	private final RoleMapper roleMapper;
	private final DeptMapper deptMapper;
	private final TenantMapper tenantMapper;

	private final TenantMapStruct tenantMapStruct;


	@Override
	public TenantDetailResponse getByTenantId(String tenantId) {
		return tenantMapStruct.toVo(tenantMapper
				.getOne(Wrappers.<Tenant>query().lambda().eq(Tenant::getTenantId, tenantId)));
	}

	private String getTenantId(List<String> codes) {
		String code = RandomUtil.randomNumbers(6);
		if (codes.contains(code)) {
			return getTenantId(codes);
		}
		return code;
	}

	@Override
	public TenantDetailResponse detail(TenantDetailRequest tenantDetailRequest) {
		Tenant detail = tenantMapper.getOne(
				Wrappers.<Tenant>lambdaQuery().eq(Tenant::getId, tenantDetailRequest.getId()));
		return tenantMapStruct.toVo(detail);
	}

	@Override
	public List<TenantDetailResponse> list(TenantListRequest tenantListRequest) {
		LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.<Tenant>lambdaQuery()
				.eq(StrUtil.isNotEmpty(tenantListRequest.getTenantId()), Tenant::getTenantId,
						tenantListRequest.getTenantId())
				.eq(StrUtil.isNotEmpty(tenantListRequest.getContactNumber()),
						Tenant::getContactNumber,
						tenantListRequest.getContactNumber())
				.eq(StrUtil.isNotEmpty(tenantListRequest.getTenantName()), Tenant::getTenantName,
						tenantListRequest.getTenantName());
		List<Tenant> list = tenantMapper.list(
				(!Objects.equals(SecurityUtils.getTenantId(), CommonConstant.ADMIN_TENANT_ID))
						? queryWrapper
						.eq(Tenant::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		return tenantMapStruct.toVo(list);
	}

	@Override
	public Page<TenantDetailResponse> page(TenantPageRequest tenantPageRequest) {
		LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.<Tenant>lambdaQuery()
				.eq(StrUtil.isNotEmpty(tenantPageRequest.getTenantId()), Tenant::getTenantId,
						tenantPageRequest.getTenantId())
				.eq(StrUtil.isNotEmpty(tenantPageRequest.getContactNumber()),
						Tenant::getContactNumber,
						tenantPageRequest.getContactNumber())
				.eq(StrUtil.isNotEmpty(tenantPageRequest.getTenantName()), Tenant::getTenantName,
						tenantPageRequest.getTenantName());
		IPage<Tenant> page = tenantMapper.page(
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
						tenantPageRequest.getPageNo(), tenantPageRequest.getPageSize()),
				(!Objects.equals(SecurityUtils.getTenantId(), CommonConstant.ADMIN_TENANT_ID))
						? queryWrapper
						.eq(Tenant::getTenantId, SecurityUtils.getTenantId()) : queryWrapper);
		Page<Tenant> pages = new Page<>(page.getCurrent(), page.getSize(), page.getTotal(),
				page.getRecords());
		return tenantMapStruct.toVo(pages);
	}

	@Override
	public boolean add(TenantAddOrUpdateRequest tenantAddRequest) {
		return tenantMapper.save(tenantMapStruct.toPo(tenantAddRequest));
	}

	@Override
	public boolean update(TenantUpdateRequest tenantUpdateRequest) {
		return tenantMapper.updateByIdBool(tenantMapStruct.toPo(tenantUpdateRequest));
	}

	@Override
	public boolean remove(TenantRemoveRequest tenantRemoveRequest) {
		return tenantMapper.removeByIds(tenantRemoveRequest.getIds());
	}

	@Override
	public boolean addOrUpdate(TenantAddOrUpdateRequest tenantAddOrUpdateRequest) {
		Tenant tenant = tenantMapStruct.toPo(tenantAddOrUpdateRequest);
		if (ObjectUtil.isEmpty(tenant.getId())) {
			List<Tenant> tenants = tenantMapper
					.selectList(Wrappers.<Tenant>query().lambda().eq(Tenant::getIsDelete,
							CommonConstant.DB_NOT_DELETED));
			List<String> codes = tenants.stream().map(Tenant::getTenantId)
					.collect(Collectors.toList());
			String tenantId = getTenantId(codes);
			tenant.setTenantId(tenantId);
			// 新建租户对应的默认角色
			Role role = new Role();
			role.setTenantId(tenantId);
			role.setParentId(0L);
			role.setRoleName("管理员");
			role.setRoleAlias("admin");
			role.setSort(2);
			role.setIsDeleted(0);
			roleMapper.insert(role);
			// 新建租户对应的默认部门
			Dept dept = new Dept();
			dept.setTenantId(tenantId);
			dept.setParentId(0L);
			dept.setDeptName(tenant.getTenantName());
			dept.setFullName(tenant.getTenantName());
			dept.setSort(2);
			dept.setIsDeleted(0);
			deptMapper.insert(dept);
		}
		return tenantMapper.saveOrUpdate(tenant);
	}
}
