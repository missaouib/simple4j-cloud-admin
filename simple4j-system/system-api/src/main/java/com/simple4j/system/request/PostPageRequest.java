package com.simple4j.system.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostPageRequest {

	@ApiModelProperty(name = "page_no", value = "页码")
	@JsonProperty("page_no")
	private int pageNo;

	@ApiModelProperty(name = "pageSize", value = "分页数")
	@JsonProperty("page_size")
	private int pageSize;
}
