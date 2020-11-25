package com.baizhi.api.controller;

import com.baizhi.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class AppCategoryController {

    @GetMapping("/getCategoryById/{id}")
    public R getCategoryById(@PathVariable("id")Integer id){

        try {
            System.err.println(id);
        } catch (Exception e) {
            e.printStackTrace();
            R.error(100,e.getMessage());
        }
        String data = "数据";
        return R.ok().put("data",data);
    }
}
