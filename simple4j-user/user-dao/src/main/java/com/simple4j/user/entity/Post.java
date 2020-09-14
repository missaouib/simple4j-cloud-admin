package com.simple4j.user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 岗位表实体类
 *
 * @author Chill
 */
@Data
@TableName("simple4j_post")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 租户ID
	 */
	private String tenantId;
	/**
	 * 类型
	 */
	private Integer category;
	/**
	 * 岗位编号
	 */
	private String postCode;
	/**
	 * 岗位名称
	 */
	private String postName;
	/**
	 * 岗位排序
	 */
	private Integer sort;
	/**
	 * 岗位描述
	 */
	private String remark;


}
