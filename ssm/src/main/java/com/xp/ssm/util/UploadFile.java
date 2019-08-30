package com.xp.ssm.util;

import com.xp.ssm.service.ex.FileException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadFile {

    /**
     * 上传文件最大
     */
    private static final long MAX_FILE = 5*1024*1024;

    /**
     * 上传文件类型
     */
    private static final List<String> FILE_TYPE = new ArrayList<>();
    static{
        FILE_TYPE.add("image/jpeg");
        FILE_TYPE.add("image/bmp");
        FILE_TYPE.add("image/png");
        FILE_TYPE.add("image/gif");
    }

    public String FileNmae(MultipartFile filename, HttpSession session,String fileNa){
        if(filename.isEmpty()){
            throw new FileException("请选择要上传的文件");
        }
        if(filename.getSize() > MAX_FILE){
            throw new FileException("上传文件过大");
        }
        //检查文件类型
        String fileType = filename.getContentType();
        if(!FILE_TYPE.contains(fileType)){
            throw new FileException("文件类型错误");
        }
        //基于session获取上传路径
        String path = session.getServletContext().getRealPath(fileNa);
        //检查文件是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取原始文件名
        String fileName = filename.getOriginalFilename();
        //获取文件类型
        Integer index = fileName.lastIndexOf(".");
        //生成目标文件名
        String name = System.currentTimeMillis() + fileName.substring(index);
        //保存头像
        File dest = new File(file,name);
        try {
            filename.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new FileException("上传失败,文件状态发生错误");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException("上传失败,读写时发生错误");
        }
        return name;
    }
}
