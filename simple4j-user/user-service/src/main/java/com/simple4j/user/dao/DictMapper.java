package com.simple4j.user.dao;

import java.util.List;

import com.simple4j.autoconfigure.mybatis.base.ExtendMapper;
import com.simple4j.user.entity.Dict;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface DictMapper extends ExtendMapper<Dict> {

	/**
	 * 获取字典表对应中文
	 *
	 * @param code    字典编号
	 * @param dictKey 字典序号
	 * @return
	 */
	String getValue(String code, Integer dictKey);

	/**
	 * 获取字典表
	 *
	 * @param code 字典编号
	 * @return
	 */
	List<Dict> getList(String code);

	/**
	 * 获取树形节点
	 *
	 * @return
	 */
	List<Dict> tree();

}
