package com.simple4j.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.simple4j.common.orm.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author huangyangcong
 * @since 2019-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RolesPermissions extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@TableId("role_id")
	private Long roleId;

	/**
	 * 权限ID
	 */
	@TableField("permission_id")
	private Long permissionId;


}