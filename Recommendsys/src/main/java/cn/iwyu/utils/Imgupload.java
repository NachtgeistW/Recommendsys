package cn.iwyu.utils;/**
 * Created by Chester on 14/10/2020.
 */


import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
    private String uploadFile(String webPath, MultipartFile file, HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath(webPath);
        String fileName = UUID.randomUUID().toString().replace("-","")+"_"+file.getOriginalFilename();
        try{
            File file1 = new File(realPath);
            if(!file1.exists()){
                // 目录不存在则创建目录
                file1.mkdirs();
            }
            file.transferTo(new File(realPath+"/"+fileName));
            //返回图片在服务器下的路径
            return webPath + "/" + fileName;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Map<String,Object> uploadAreaFile( MultipartFile file, HttpServletRequest request) throws Exception {
        String schoolId = request.getParameter("schoolId");//获取参数
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            //上传文件方法，这里需要改成自己项目里上传文件方法
            String filePath = this.uploadFile("uploadImg",file,request.getSession());
            result.put("code", 0);
            result.put("msg", "上传成功");
            result.put("filePath", filePath);
            return result;
        }catch(Exception e){
            result.put("code", -1);
            result.put("msg", "上传失败");
            result.put("filePath", "");
            return result;
        }

    }

}
