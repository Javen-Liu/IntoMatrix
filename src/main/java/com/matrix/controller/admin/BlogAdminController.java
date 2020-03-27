package com.matrix.controller.admin;

import com.matrix.entity.Blog;
import com.matrix.entity.BlogType;
import com.matrix.entity.PageBean;
import com.matrix.service.IBlogService;
import com.matrix.service.IBlogTypeService;
import com.matrix.utils.DateJsonValueProcessor;
import com.matrix.utils.ResponseUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/26 21:46
 * @github https://github.com/Javen-Liu
 * 博客文章类型控制器
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
    @Resource
    private IBlogService blogService;

    @Resource
    private IBlogTypeService blogTypeService;

    @RequestMapping("/edit")
    public String blogEdit(ModelMap modelMap){
        List<BlogType> types = blogTypeService.findAllType();
        modelMap.put("list",types);
        return "admin/blog/blogEditManager";
    }

    @RequestMapping("/blogList")
    public void blogList(@RequestParam(value = "page", required = false) String page,
                           @RequestParam(value = "rows", required = false) String rows,
                           @RequestParam(value = "status", required = false) String status,
                           HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<>(Integer.parseInt(rows));
        map.put("start",Integer.parseInt(page));
        map.put("size",Integer.parseInt(rows));
        map.put("status",Integer.parseInt(status));
        List<Blog> blogs = blogService.findBlogByMap(map);
        Long total = blogService.findCountOfBlogByMap(map);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(blogs,jsonConfig);
        JSONObject result = new JSONObject();
        result.put("success",Boolean.TRUE);
        result.put("blog_list", jsonArray);
        result.put("total", total);
        ResponseUtils.write(response,result);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam("idArr") String[] idArr,
                         HttpServletResponse response) throws IOException {
        Integer flag = blogService.batchDelete(idArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/deleteIntoRecycle")
    public void deleteIntoRecycle(@RequestParam("idArr") String[] idArr,
                                  HttpServletResponse response) throws IOException {
        Integer flag = blogService.batchDeleteIntoRecycle(idArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/recoverFromRecycle")
    public void recoverFromRecycle(@RequestParam("idArr") String[] idArr,
                                  HttpServletResponse response) throws IOException {
        Integer flag = blogService.batchRecoverFromRecycle(idArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/save")
    public void blogSave(Blog blog,
                         @RequestParam("typeId") String typeId,
                         HttpServletResponse response) throws IOException {
        BlogType blogType = blogTypeService.findTypeById(Integer.parseInt(typeId));
        blog.setBlogType(blogType);
        Integer flag = blogService.addBlog(blog);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/search")
    public String searchBlog(@RequestParam("searchInfo") String searchInfo,
                           ModelMap modelMap){
        Map<String,Object> map = new HashMap<>(1);
        map.put("title",searchInfo);
        map.put("status",0);
        List<Blog> blogs = blogService.findBlogByMap(map);
        Long total = blogService.findCountOfBlogByMap(map);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(blogs,jsonConfig);
        modelMap.put("blog_list",jsonArray);
        modelMap.put("total",total);
        return "admin/blog/blogSearchManager";
    }
}
