package com.rockfintech.reas.xabank.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rockfintech.reas.xabank.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 通用service实现
 *
 * @author zengsheng
 * @create 2017-10-12
 **/
@Service
public abstract class BaseService<T> implements IBaseService<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected Mapper<T> baseMapper;

    private Class<?> clazz = null;

    protected Class<?> getEntityClass() {
        if (clazz == null) {
            clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return clazz;
    }

    @Override
    public int insert(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int insertSelective(T entity) {
        return baseMapper.insertSelective(entity);
    }

    @Override
    public int updateByPrimaryKey(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int deleteByPrimaryKey(Object primaryKey) {
        return baseMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int deleteByPrimaryKeys(List<Object> primaryKeys) {
        int resulrt = 0;
        for (Object primaryKey : primaryKeys) {
            resulrt += baseMapper.deleteByPrimaryKey(primaryKey);
        }
        return resulrt;
    }

    @Override
    public int deleteByField(String field, String value) {
        return deleteByFields(new String[] { field }, new String[] { value });
    }

    @Override
    public int deleteByFields(String[] fields, String[] values) {
        if (null == fields || null == values || fields.length == 0 || fields.length != values.length) {
            return 0;
        }
        Example example = new Example(getEntityClass());
        Example.Criteria criteria = example.createCriteria();
        for (int i = 0; i < fields.length; i++) {
            criteria.andEqualTo(fields[i], values[i]);
        }
        return baseMapper.deleteByExample(example);
    }

    @Override
    public T selectOne(T entity) {
        return (T)baseMapper.selectOne(entity);
    }

    @Override
    public T selectByPrimaryKey(Object primaryKey) {
        return (T)baseMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public List<T> select(T entity) {
        return baseMapper.select(entity);
    }

    @Override
    public List<T> selectByExample(Object o) {
        return baseMapper.selectByExample(o);
    }

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();//TODO 可能要强转
    }

    @Override
    public int selectCount(T entity) {
        return baseMapper.selectCount(entity);
    }

    @Override
    public int selectCountByExample(Object o) {
        return baseMapper.selectCountByExample(o);
    }

    @Override
    public PageInfo<T> selectAllPage(int pageNum, int pageSize) {
        PageInfo<T> pageInfo = null;
        PageHelper.startPage(pageNum, pageSize);
        List<T> userSmsList = baseMapper.selectAll();
        if(null != userSmsList && userSmsList.size() > 0){
            pageInfo = new PageInfo<T>(userSmsList);
        }
        return pageInfo;
    }


}
