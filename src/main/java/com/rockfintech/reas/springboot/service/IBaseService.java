package com.rockfintech.reas.springboot.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBaseService<T> {

    int insert(T entity);
    /**
     * 添加非空值，null不会被保存，使用数据库默认值
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    int updateByPrimaryKey(T entity);

    int updateByPrimaryKeySelective(T entity);

    int deleteByPrimaryKey(Object primaryKey);

    int deleteByPrimaryKeys(List<Object> primarykeys);

    /**
     * 根据指定字段值删除，判断条件为等号
     *
     * @param field
     * @param value
     * @return
     */
    int deleteByField(String field, String value);

    int deleteByFields(String[] fields, String[] values);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    T selectByPrimaryKey(Object primaryKey);

    List<T> select(T entity);

    List<T> selectByExample(Object o);

    List<T> selectAll();

    int selectCount(T entity);

    int selectCountByExample(Object o);

    PageInfo<T> selectAllPage(int pageNum, int pageSize);

}
