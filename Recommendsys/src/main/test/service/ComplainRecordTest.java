package service;/**
 * Created by Chester on 30/9/2020.
 */

import cn.iwyu.domain.ComplainRecord;
import cn.iwyu.domain.ComplainRecordCustom;
import cn.iwyu.service.ComplainRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ComplainRecordTest
 * @Description
 * @Author XiaoMao
 * @Date 30/9/2020 下午4:16
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ComplainRecordTest {

    @Resource
    ComplainRecordService complainRecordService;

    @Test
    public void save(){
        ComplainRecord complainRecord = new ComplainRecord();
        complainRecord.setIdRestaurant(1);
        complainRecord.setIdUser(1);
        Byte a = 0;
        complainRecord.setIsProcessed(a);
        complainRecord.setReason("脏乱差");
        complainRecordService.save(complainRecord);
    }
    @Test
    public void findAll(){
        for (ComplainRecord c: complainRecordService.findAll()
             ) {
            System.out.println(c);
        }
    }
    @Test
    public void findByUserId(){
        for (ComplainRecord c: complainRecordService.findByUserId(3)
             ) {
            System.out.println(c);
        }
    }
    @Test
    public void findByResId(){
        for (ComplainRecord c:complainRecordService.findByResId(1)
             ) {
            System.out.println(c);
        }
    }
    @Test
    public void update(){
        List<ComplainRecordCustom> c = complainRecordService.findByUserId(3);
        ComplainRecord complainRecord = c.get(0);
        complainRecord.setReason("其实还行");
        System.out.println(complainRecordService.update(complainRecord));
    }
    @Test
    public void delete(){
        System.out.println(complainRecordService.delete(2));
    }
}
