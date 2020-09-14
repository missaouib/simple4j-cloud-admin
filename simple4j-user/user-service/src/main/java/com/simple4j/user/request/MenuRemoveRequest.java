package com.simple4j.user.request;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MenuRemoveRequest {

	@ApiModelProperty(value = "菜单删除编号列表", name = "menu_ids")
	@JsonProperty("menu_ids")
	private List<Long> menuIds;
}
