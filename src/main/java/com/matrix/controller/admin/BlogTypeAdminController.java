package com.matrix.controller.admin;

import com.matrix.entity.BlogType;
import com.matrix.entity.PageBean;
import com.matrix.service.IBlogTypeService;
import com.matrix.utils.ResponseUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 18:08
 * @github https://github.com/Javen-Liu
 * 博客文章类型控制器
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {
    @Resource
    private IBlogTypeService blogTypeService;

    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       ModelMap modelMap) {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String,Object> map = new HashMap<>();
        map.put("start",pageBean.getPage());
        map.put("size",pageBean.getPageSize());
        List<BlogType> blogTypes = blogTypeService.findTypeByMap(map);
        modelMap.put("list",blogTypes);
        return "admin/blog_type/blogTypeManager";
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(@RequestParam(value = "idArr") String[] idArr,
                       @RequestParam(value = "nameArr") String[] nameArr,
                       @RequestParam(value = "sortArr") String[] sortArr,
                       HttpServletResponse response) throws IOException {
        Integer flag = blogTypeService.batchSave(idArr, nameArr, sortArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam(value = "idArr") String[] idArr,
                       HttpServletResponse response) throws IOException {
        Integer flag = blogTypeService.batchDelete(idArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }
}
