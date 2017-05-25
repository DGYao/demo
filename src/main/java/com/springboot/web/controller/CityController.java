package com.springboot.web.controller;

import com.springboot.web.model.City;
import com.springboot.web.service.CityService;
import com.springboot.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ez on 2017/5/11.
 */
@Controller
@RequestMapping("/city")
public class CityController extends BaseController {
    @Autowired
    private CityService cityService;
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('city')")
    public ModelAndView list(City city){
        ModelAndView result = new ModelAndView("city");
        List<City> list = cityService.findList(city.getPage(), city.getRows());
        Map<String,Object> condition = new HashMap();
        condition.put("name",city.getName());
        result.addObject("pageBean",setValue(list,condition,city));
        return result;
    }

    @RequestMapping("/editPage")
    @ResponseBody
    public City editPage(Integer id){
        City city = cityService.findById(id);
        return city;
    }

    @RequestMapping("/addOrEdit")
    @ResponseBody
    public String addOrEdit(City city, RedirectAttributes ra){
        cityService.addOrUpdate(city);
        ra.addAttribute("msg", city.getId()==null?"新增成功":"更新成功");
        return redirect("/city/list");
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id, RedirectAttributes ra){
        cityService.delete(id);
        ra.addAttribute("msg","删除成功");
        return redirect("/city/list");
    }
}
