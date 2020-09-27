package com.simple4j.system.mapstruct;

import java.util.List;

import com.simple4j.api.base.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.simple4j.system.entity.AuthClient;
import com.simple4j.system.request.ClientAddOrUpdateRequest;
import com.simple4j.system.request.ClientAddRequest;
import com.simple4j.system.request.ClientUpdateRequest;
import com.simple4j.system.response.ClientDetailResponse;

/**
 * 客户端表数据转换类
 *
 * @author hyc
 * @since 2020-08-26
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapStruct {

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	AuthClient toPo(ClientAddRequest vo);

	/**
	 * VO转PO
	 *
	 * @param vo
	 * @return
	 */
	AuthClient toPo(ClientAddOrUpdateRequest vo);

	/**
	 * VO转PO
	 *
	 * @return
	 * @vo vo
	 */
	AuthClient toPo(ClientUpdateRequest vo);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	ClientDetailResponse toVo(AuthClient po);

	/**
	 * PO转VO
	 *
	 * @param po
	 * @return
	 */
	List<ClientDetailResponse> toVo(List<AuthClient> po);


	/**
	 * 分页转换PO转VO
	 *
	 * @param po
	 * @return
	 */
	Page<ClientDetailResponse> toVo(Page<AuthClient> po);
}