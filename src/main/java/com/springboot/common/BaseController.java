package com.springboot.common;

/**
 * Created by Administrator on 2017/5/11.
 */

import java.util.List;
import java.util.Map;

/**
 * Created by ez on 2017/5/11.
 */
public class BaseController<T extends BaseModel> {

    //转发
    public String forward(String action){
        return "forward:/"+action;
    }

    //重定向
    public String redirect(String action){
        return "redirect:/"+action;
    }

    //设置分页信息
    public PageBean<T> setValue(List<T> list, Map<String,Object> condition, T model){
        PageBean<T> pageBean = new PageBean<T>(list);
        pageBean.setPageNum(model.getPage());
        pageBean.setPageSize(model.getRows());
        pageBean.setConditionMap(condition);
        return pageBean;
    }
}

