package com.simple4j.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4j.user.entity.Post;
import com.simple4j.user.request.PostAddRequest;
import com.simple4j.user.request.PostDetailRequest;
import com.simple4j.user.request.PostListRequest;
import com.simple4j.user.request.PostPageRequest;
import com.simple4j.user.request.PostRemoveRequest;
import com.simple4j.user.request.PostUpdateRequest;
import com.simple4j.user.response.PostDetailResponse;

/**
 * 岗位表 服务类
 *
 * @author Chill
 */
public interface IPostService extends IService<Post> {

	/**
	 * 详情
	 */
	PostDetailResponse detail(PostDetailRequest postDetailRequest);

	/**
	 * 获取岗位ID
	 *
	 * @param tenantId
	 * @param postNames
	 * @return
	 */
	List<Long> getPostIds(String tenantId, List<String> postNames);

	/**
	 * 获取岗位名
	 *
	 * @param userId
	 * @return
	 */
	List<String> getPostNames(Long userId);


	/**
	 * 列表 岗位表
	 */
	List<PostDetailResponse> list(PostListRequest postListRequest);

	/**
	 * 自定义分页 岗位表
	 */
	Page<PostDetailResponse> page(PostPageRequest postPageRequest);

	/**
	 * 新增 岗位表
	 */
	void add(PostAddRequest postAddRequest);

	/**
	 * 修改 岗位表
	 */
	void update(PostUpdateRequest postUpdateRequest);

	/**
	 * 删除 岗位表
	 */
	void remove(PostRemoveRequest postRemoveRequest);

}
