package com.matrix.controller.admin;

import com.matrix.entity.Comment;
import com.matrix.service.ICommentService;
import com.matrix.utils.DateJsonValueProcessor;
import com.matrix.utils.ResponseUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * @date 2020/3/27 21:15
 * @github https://github.com/Javen-Liu
 * 评论前端控制器
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
    @Resource
    private ICommentService commentService;

    @RequestMapping("/commentList")
    public void commentList(@RequestParam("status") String status,
                            HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<>(1);
        if(status!=null && !"".equals(status)){
            map.put("status",Integer.parseInt(status));
        }
        List<Comment> comments = commentService.findCommentByMap(map);
        Long total = commentService.findCountOfCommentByMap(map);
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONArray jsonArray = JSONArray.fromObject(comments,config);
        JSONObject result = new JSONObject();
        result.put("success",Boolean.TRUE);
        result.put("comment_list", jsonArray);
        result.put("total", total);
        ResponseUtils.write(response,result);
    }

    @RequestMapping("/pass")
    public void passComment(@RequestParam("idArr") String[] idArr,
                            HttpServletResponse response) throws IOException {
        Integer flag = commentService.batchPass(idArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/fail")
    public void failComment(@RequestParam("idArr") String[] idArr,
                            HttpServletResponse response) throws IOException {
        Integer flag = commentService.batchFail(idArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("delete")
    public void deleteComment(@RequestParam("idArr") String[] idArr,
                              HttpServletResponse response) throws IOException {
        Integer flag = commentService.batchDelete(idArr);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }
}
