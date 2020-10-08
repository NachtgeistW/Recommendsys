package cn.iwyu.service.impl;/**
 * Created by Chester on 29/9/2020.
 */

import cn.iwyu.dao.UserCustomMapper;
import cn.iwyu.dao.UserMapper;
import cn.iwyu.domain.Email;
import cn.iwyu.domain.User;
import cn.iwyu.service.UserService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author XiaoMao
 * @Date 29/9/2020 下午3:45
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    UserCustomMapper customMapper;
    @Resource
    JavaMailSender javaMailSender;

    @Transactional
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findById(Integer id_user) {
        return userMapper.selectByPrimaryKey(id_user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int delete(Integer id_user) {
        return userMapper.deleteByPrimaryKey(id_user);
    }

    @Override
    public List<User> findByExample(User example) {
        return customMapper.findByExample(example);
    }

    @Override
    public Integer batchDelete(List<User> users) {
        Integer flag = 0;
        for (User user :users) {
            flag += userMapper.deleteByPrimaryKey(user.getIdUser());
        }
        return flag;
    }
/**
*@Description 验证邮箱
*@Author XiaoMao
*@Date 7/10/2020 下午4:18
*@Param [user]
*Return java.lang.Integer 返回0
**/
    @Override
    public Integer checkEmail(User user) {
        User temp = customMapper.checkEmail(user.getEmail());
        if(temp == null){
            return 0;
        }
        return 1;
    }

    @Override
    public Integer checkPwd(User user) {
        User temp = customMapper.checkPwd(user);
        if(temp == null){
            return 0;
        }
        return 1;
    }

    @Override
    public String  sendEmail(Email mail) {
        Map<String,Object> modelMap = new HashMap<>();
        MimeMessage mMessage = javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        String from;
        String checkCode = null;
        try {
            //从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
            from = prop.get("mail.smtp.username")+"";
            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom(from);//发件人邮箱
            mMessageHelper.setTo(mail.getAddress());//收件人邮箱
            if (mail.getTitle() == null){
                mail.setTitle("check");
            }
            mMessageHelper.setSubject(mail.getTitle());//邮件的主题
            if (mail.getText() == null){
                checkCode = String.valueOf(new Random().nextInt(89999/9) + 100000);
                String message = "您的注册验证码为："+checkCode;
                mail.setText(message);
                System.out.println(message);
            }
            mMessageHelper.setText("<p>"+mail.getText()+"</p><br/>",true);//邮件的文本内容，true表示文本以html格式打开
            javaMailSender.send(mMessage);//发送邮件
        } catch ( MessagingException e) {
            e.printStackTrace();
            return checkCode;
        } catch (IOException e) {
            e.printStackTrace();
            return checkCode;
        }
        return checkCode;
    }
}
