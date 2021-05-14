
import com.alipay.api.AlipayApiException;
import com.miscorf.pojo.*;
import com.miscorf.service.*;
import com.miscorf.util.AlipayUtil;
import com.miscorf.util.QiniuCloudUtil;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MyTest {


    @Test
    public void text(){
        ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
//        Books book =new Books();
//        book.setBookName("12312");
//        bookServiceImpl.addBook(book);
//        System.out.println(book.getBookID());
        PayService payService = (PayService)context.getBean("PayServiceImpl");
        Pay pay  = new Pay();
        pay.setPay_title("text");
        payService.creatPay(pay);
        System.out.println(pay.getPay_table_id());
    }
    @Test
    public void user_test(){
        ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        User user = new User();
        user.setUser_name("admin");
        user.setUser_image("haha");
        userServiceImpl.updateUserImage(user);
        //User user =new User(1,"","","1234","123","123","123","123",1,"admin","","");
       // userServiceImpl.updateUser(user);
       // List<User> users =userServiceImpl.queryAllUserPage(1,10,"li", null);
        //userServiceImpl.setTokenUser(19, "a794df83-4879-4f4a-a706-0a2f59182f74");

//        FormService formService = (FormService)context.getBean("FormServiceImpl");
//        Answer answer =  new Answer();
//        answer.setForm_id(21);
//        answer.setUser_name("admin");
//        answer.setAnswer_content("content");
//        formService.updateAnswer(answer);
//        Template template = new Template();
//        template.setName("123");
//        template.setCreat_time("2021-04-27 13:41:24.832");
//        template.setCreator();
//        template.setVisible();
//        template.setData();
        //formService.addTemplate(template);
//        Form form =new Form();
//        form.setForm_name("123");
//        form.setTemplate_id(3);
//        form.setForm_creator("admin");
//        form = formService.getFormById(13);
//        System.out.println(form.getForm_creat_time());
//                System.out.println( formService.getFormByForm(form));

        //System.out.println(formService.getUserFormList("admin",0,10));


    }

    @Test
    public void user_test_right() throws IOException {
        ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
        NoticeService  noticeService = (NoticeService) context.getBean("NoticeServiceImpl");
        Notice notice =new Notice(3, null, null, null, null, 0, null, null, null, null, false, null);
        //System.out.println(noticeService.deleteNoticeById(1));
        //System.out.println(noticeService.updateNotice(notice));
        noticeService.addNotice(notice);
//        ObjectMapper mapper = new ObjectMapper();
//        String text = "{labelPosition=left, labelWidth=100, size=mini, outputHidden=true, hideRequiredMark=true, customStyle=}";
//
//        JSONObject obj = JSON.parseObject(text);
//        System.out.println(obj);
    }
    @Test
    public void user_test23_right() throws IOException {
        QiniuCloudUtil qiniuCloudUtil = new QiniuCloudUtil();
        String url= "http://test.miscorf.top/84f77739-fe3c-47e1-b1ac-148d2510d5a7";
            //url = url.substring(23);
        qiniuCloudUtil.delete(url);
        System.out.println(url);

    }
    @Test
    public void aliTest() throws IOException, AlipayApiException {
        AlipayUtil alipayUtil = new AlipayUtil();
        System.out.println(  alipayUtil.testAliPay());

    }
}
