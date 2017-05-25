package com.springboot.common;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ez on 2016/11/15.
 */
public class PageBean<T> extends PageInfo<T> {

    public PageBean(){
        super();
    }
	public PageBean(List<T> list){
        super(list,10);
    }
    private Map<String,Object> conditionMap;

    public void setConditionMap(Map<String, Object> conditionMap) {
        this.conditionMap = conditionMap;
    }

    public Map<String, Object> getConditionMap() {
        if (conditionMap == null) conditionMap = new HashMap<>();
        return conditionMap;
    }
}
