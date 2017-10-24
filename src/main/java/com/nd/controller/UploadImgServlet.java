package com.nd.controller;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 富文本编辑器图片上传
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadImgServlet  extends HttpServlet {

    private static final long serialVersionUID = -2712624630388027149L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("textml;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Part part = request.getPart("wangEditorH5File");// myFileName是文件域的name属性值
        // 文件类型限制
        String[] allowedType = { "image/bmp", "image/gif", "image/jpeg", "image/png","image/jpg" };
        boolean allowed = Arrays.asList(allowedType).contains(part.getContentType());
        if (!allowed) {
            response.getWriter().write("error|不支持的类型");
            return;
        }
        // 图片大小限制
        if (part.getSize() > 5 * 1024 * 1024) {
            response.getWriter().write("error|图片大小不能超过5M");
            return;
        }
        //判断存放文件夹路径
        String Path = getServletContext().getRealPath("/files");
        File file = new File(Path);
        if (!file.exists())
            file.mkdirs();
        
        // 包含原始文件名的字符串
        String fi = part.getHeader("content-disposition");
        // 提取文件拓展名
        String fileNameExtension = fi.substring(fi.indexOf("."), fi.length() - 1);
        // 生成实际存储的真实文件名
        String realName = UUID.randomUUID().toString() + fileNameExtension;
        // 图片存放的真实路径
        String realPath = getServletContext().getRealPath("/files") + "/" + realName;
        System.out.println(realPath);
        // 将文件写入指定路径下
        part.write(realPath);

        // 返回图片的URL地址
        response.getWriter().write(request.getContextPath() + "/files/" + realName);

    }

}