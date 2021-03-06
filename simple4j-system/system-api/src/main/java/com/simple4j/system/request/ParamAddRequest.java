package com.simple4j.system.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 参数表新增请求实体类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Data
@ApiModel(value = "参数表新增请求实体类", description = "参数表新增请求实体类")
public class ParamAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数名
	 */
	@ApiModelProperty(name = "param_name", value = "参数名")
	@JsonProperty("param_name")
	private String paramName;
	/**
	 * 参数键
	 */
	@ApiModelProperty(name = "param_key", value = "参数键")
	@JsonProperty("param_key")
	private String paramKey;
	/**
	 * 参数值
	 */
	@ApiModelProperty(name = "param_value", value = "参数值")
	@JsonProperty("param_value")
	private String paramValue;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark", value = "备注")
	@JsonProperty("remark")
	private String remark;
	/**
	 * 创建人
	 */
	@ApiModelProperty(name = "create_user", value = "创建人")
	@JsonProperty("create_user")
	private String createUser;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "create_time", value = "创建时间")
	@JsonProperty("create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@ApiModelProperty(name = "update_user", value = "修改人")
	@JsonProperty("update_user")
	private String updateUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(name = "update_time", value = "修改时间")
	@JsonProperty("update_time")
	private Date updateTime;
	/**
	 * 状态
	 */
	@ApiModelProperty(name = "status", value = "状态")
	@JsonProperty("status")
	private Integer status;
}
