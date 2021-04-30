import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miscorf.pojo.*;
import com.miscorf.service.BookService;
import com.miscorf.service.FormService;
import com.miscorf.service.Impl.NoticeServiceImpl;
import com.miscorf.service.NoticeService;
import com.miscorf.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.Oneway;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.Format;
import java.util.List;
import java.util.Map;

public class MyTest {


    @Test
    public void text(){
        ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        for (Books books:bookServiceImpl.queryAllBook()){
            System.out.println(books);
        }
    }
    @Test
    public void user_test(){
        ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        User user =new User(1,"1234","123","123","123","123",1,"admin","");
        userServiceImpl.updateUser(user);
        List<User> users =userServiceImpl.queryAllUserPage(1,10,"li", null);
        //userServiceImpl.setTokenUser(19, "a794df83-4879-4f4a-a706-0a2f59182f74");
        FormService formService = (FormService)context.getBean("FormServiceImpl");
        Answer answer =  new Answer();
        answer.setForm_id(21);
        answer.setUser_name("admin");
        answer.setAnswer_content("content");
        formService.updateAnswer(answer);
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
        System.out.println(noticeService.deleteNoticeById(1));
        System.out.println(noticeService.updateNotice(notice));
        ObjectMapper mapper = new ObjectMapper();
        String text = "{labelPosition=left, labelWidth=100, size=mini, outputHidden=true, hideRequiredMark=true, customStyle=}";

        JSONObject obj = JSON.parseObject(text);
        System.out.println(obj);
    }
}
