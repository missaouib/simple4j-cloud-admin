package com.simple4j.system.controller;

import java.util.List;

import javax.validation.Valid;

import com.simple4j.system.request.MenuGrantRequest;
import com.simple4j.system.request.RoleAddOrUpdateRequest;
import com.simple4j.system.request.RoleDetailRequest;
import com.simple4j.system.request.RoleListRequest;
import com.simple4j.system.request.RoleRemoveRequest;
import com.simple4j.system.response.RoleDetailResponse;
import com.simple4j.system.service.IRoleMenuService;
import com.simple4j.system.service.IRoleService;
import com.simple4j.web.bean.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @author hyc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "角色", tags = "角色")
public class RoleController {

	private final IRoleService roleService;
	private final IRoleMenuService roleMenuService;

	/**
	 * 详情
	 */
	@PostMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入role")
	public ApiResponse<RoleDetailResponse> detail(
		@Valid @RequestBody RoleDetailRequest roleDetailRequest) {
		return ApiResponse.ok(roleService.detail(roleDetailRequest));
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表", notes = "传入role")
	public ApiResponse<List<RoleDetailResponse>> list(
		@Valid @RequestBody RoleListRequest roleListRequest) {
		return ApiResponse.ok(roleService.list(roleListRequest));
	}

	/**
	 * 获取角色树形结构
	 */
	@PostMapping("/tree")
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public ApiResponse<List<RoleDetailResponse>> tree(String tenantId) {
		List<RoleDetailResponse> tree = roleService.tree(tenantId);
		return ApiResponse.ok(tree);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入role")
	public ApiResponse<Void> addOrUpdate(
		@Valid @RequestBody RoleAddOrUpdateRequest roleAddOrUpdateRequest) {
		roleService.addOrUpdate(roleAddOrUpdateRequest);
		return ApiResponse.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入ids")
	public ApiResponse<Void> remove(@RequestBody RoleRemoveRequest roleRemoveRequest) {
		roleService.remove(roleRemoveRequest);
		return ApiResponse.ok();
	}

	/**
	 * 设置菜单权限
	 */
	@PostMapping("/grant")
	@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
	public ApiResponse<Void> grant(@Valid @RequestBody MenuGrantRequest menuGrantRequest) {
		roleMenuService.grant(menuGrantRequest);
		return ApiResponse.ok();
	}
}
