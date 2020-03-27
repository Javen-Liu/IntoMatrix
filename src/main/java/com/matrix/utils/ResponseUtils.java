package com.matrix.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 20:22
 * @github https://github.com/Javen-Liu
 * 写入response的工具类
 */
public class ResponseUtils {
    public static void write(HttpServletResponse response, Object o) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
