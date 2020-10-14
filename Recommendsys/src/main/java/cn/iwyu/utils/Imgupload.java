package cn.iwyu.utils;/**
 * Created by Chester on 14/10/2020.
 */


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

/**
 * @ClassName Imgupload
 * @Description
 * @Author XiaoMao
 * @Date 14/10/2020 下午2:51
 * @Version 1.0
 **/
public class Imgupload {
        /**
         * 一个输入框上传多个文件
         * @param files
         * @param request
         * @return
         * @throws Exception
         */
        public String uploadMultipal(MultipartFile[] files, HttpServletRequest request) throws Exception {
            String fileNames = null;
            ServletContext servletContext = request.getSession().getServletContext();
            String realPath = servletContext.getRealPath("/uploadImg/");
            System.out.println("文件存放的位置： " + realPath);
            File file1 = new File(realPath);
            if (!file1.exists()){
                file1.mkdirs();
            }
            for(MultipartFile file : files){
                System.out.println("现在存储 " + file.getOriginalFilename());
                String filename = UUID.randomUUID().toString().replace("-","").toUpperCase() + "_" + file.getOriginalFilename();
                file.transferTo(new File(realPath + filename));
                fileNames = fileNames +filename + ",";
            }
            return fileNames;
        }
}
