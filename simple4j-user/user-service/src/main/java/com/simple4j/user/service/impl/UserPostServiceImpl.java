package com.simple4j.user.service.impl;


import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.simple4j.user.service.IUserPostService;
import com.simple4j.user.entity.UserPost;
import com.simple4j.user.mapper.UserPostMapper;
import com.simple4j.user.request.UserPostGrantRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务类
 *
 * @author Chill
 */
@Service
@RequiredArgsConstructor
public class UserPostServiceImpl implements
		IUserPostService {
	private final UserPostMapper userPostMapper;

	@Override
	public List<Long> getPostIds(Long userId) {
		return userPostMapper.getPostIds(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grant(UserPostGrantRequest userPostGrantRequest) {
		grant(userPostGrantRequest.getUserIds(), userPostGrantRequest.getPostIds());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grant(List<Long> userIds, List<Long> postIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userPostMapper
				.physicsDelete(Wrappers.<UserPost>lambdaQuery().in(UserPost::getUserId, userIds));
			if (CollUtil.isNotEmpty(postIds)) {
				List<UserPost> userPosts = new ArrayList<>();
				for (Long userId : userIds) {
					for (Long PostId : postIds) {
						UserPost userPost = new UserPost();
						userPost.setUserId(userId);
						userPost.setPostId(PostId);
						userPosts.add(userPost);
					}
				}
				userPostMapper.saveBatch(userPosts);
			}
		}
	}

	@Override
	public void removeByPostIds(List<String> postIds) {
		if (CollUtil.isNotEmpty(postIds)) {
			userPostMapper.physicsDelete(
					Wrappers.<UserPost>lambdaQuery().in(UserPost::getPostId, postIds));
		}
	}
	@Override
	public void removeByUserIds(List<String> userIds) {
		if (CollUtil.isNotEmpty(userIds)) {
			userPostMapper.physicsDelete(
					Wrappers.<UserPost>lambdaQuery().in(UserPost::getUserId, userIds));
		}
	}
}
