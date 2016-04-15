package com.damon.spring.controller;

import com.damon.spring.bean.RatePlan;
import com.damon.spring.bean.StringArray;
import com.damon.spring.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试参数绑定
 * Created by dongjun.wei on 16/3/16.
 */
@Controller
public class BindingController {
    public static final Logger logger = LoggerFactory.getLogger(BindingController.class);

    /**
     * 不加任何注解的参数，而且是简单类型，那么会使用RequestParamMethodArgumentResolver来解析
     *使用request.getParameter(参数名)从请求中获取参数
     * http://localhost:8080/testSimple?name=hello
     * @param name 请求参数
     * @return 使用了ResponseBody注解，pom中添加了jackson依赖，
     *         使用MappingJackson2HttpMessageConverter序列化为Json塞到response body中
     */
    @RequestMapping(value = "testSimple", method = RequestMethod.GET)
    @ResponseBody
    public User testSimple(String name) {
        logger.info(name);
        User user = new User();
        user.setName(name);
        user.setBirthday(new Date());
        return user;
    }

    /**
     * 不加任何注解的参数，而且不是简单类型，那么会使用ServletModelAttributeMethodProcessor来解析
     * curl -d 'name=bob&password=123&married=1&birthday=2016-03-16 12:12:12' "http://localhost:8080/testCustomerObject"
     * 这里不需要直接提供user参数，而是提供User类的属性值。
     * 就像我们填了一个表单，然后使用POST提交
     * @param user ServletModelAttributeMethodProcessor会将参数值绑定到User属性上
     * @return 同上
     */
    @RequestMapping(value = "testCustomerObject")
    @ResponseBody
    public User testCustomerObject(User user) {
        logger.info(user.toString());
        return user;
    }

    /**
     * 使用RequestParam注解标注的参数，会使用RequestParamMethodArgumentResolver来解析
     * 当然这个HandlerMethodArgumentResolver还会处理简单类型，比如Date
     * curl -d 'addressbook=alice_123213' "http://localhost:8080/testRequestParam"
     *
     * @param user 请求参数中必须有user，因为处理时会使用request.getParameter(参数名)获取参数
     *             拿到的是一个String，然后使用自定义的UserEditor转化为User对象
     * @return 同上
     */
    @RequestMapping(value = "testRequestParam")
    @ResponseBody
    public User testRequestParam(@RequestParam User user) {
        logger.info(user.toString());
        return user;
    }

    /**
     *
     curl -H "Content-Type: application/json" -d '{
     "name": "cat",
     "password": "123",
     "married": true,
     "birthday": "2016-03-17 09:58:04"
     }' "http://localhost:8080/testPostJson"
     * 使用POST方式，将JSON放到request body中
     * 方法参数加了@RequestBody注解，会将Request body中的内容拿出来，注意，这里还加了Content-Type: application/json头
     * 有@ResponseBody和@RequestBody注解会使用RequestResponseBodyMethodProcessor解析
     * 看看日志：
     10:25:30.331 [http-bio-8080-exec-1] DEBUG o.s.web.servlet.DispatcherServlet - DispatcherServlet with name 'springServlet' processing POST request for [/testPostJson]
     10:25:30.333 [http-bio-8080-exec-1] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Looking up handler method for path /testPostJson
     10:25:30.337 [http-bio-8080-exec-1] DEBUG o.s.w.s.m.m.a.RequestMappingHandlerMapping - Returning handler method [public com.damon.spring.bean.User com.damon.spring.controller.BindingController.testPostJson(com.damon.spring.bean.User)]
     10:25:30.337 [http-bio-8080-exec-1] DEBUG o.s.b.f.s.DefaultListableBeanFactory - Returning cached instance of singleton bean 'bindingController'
     10:25:30.404 [http-bio-8080-exec-1] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Reading [com.damon.spring.bean.User] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@5341870e]
     10:25:30.414 [http-bio-8080-exec-1] INFO  c.d.s.controller.BindingController - User{name='cat', password='123', married=true, birthday=Thu Mar 17 09:58:04 CST 2016}
     10:25:30.444 [http-bio-8080-exec-1] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Written [User{name='cat', password='123', married=true, birthday=Thu Mar 17 09:58:04 CST 2016}] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@5341870e]
     10:25:30.444 [http-bio-8080-exec-1] DEBUG o.s.web.servlet.DispatcherServlet - Null ModelAndView returned to DispatcherServlet with name 'springServlet': assuming HandlerAdapter completed request handling
     10:25:30.444 [http-bio-8080-exec-1] DEBUG o.s.web.servlet.DispatcherServlet - Successfully completed request
     * @param user RequestResponseBodyMethodProcessor将request body中的JSON串反序列化为User对象
     * @return
     */
    @RequestMapping(value = "testPostJson")
    @ResponseBody
    public User testPostJson(@RequestBody User user) {
        logger.info(user.toString());
        return user;
    }

    /**
     * curl -H "Content-Type: application/x-www-form-urlencoded" -d 'addressbook=cat_123&name=damon' "http://localhost:8080/testRequestBody"
     * @param name 注意，POST的request body是整个user=cat_123&name=damon
     *             这样，使用RequestBody拿到的也是完整的String，赋给name，不要以为能够从中挑出name
     * @return addressbook=cat_123&name=damon
     */
    @RequestMapping(value = "testRequestBody")
    @ResponseBody
    public String testRequestBody(@RequestBody String name) {
        logger.info(name);
        return name;
    }

    /**
     * curl -H "Content-Type: application/x-www-form-urlencoded" -d 'name=bob&password=123&married=1&birthday=2016-03-16 12:12:12&address=beijing' "http://localhost:8080/testCustomerObjectAndSimple"
     *
     * @param user
     * @param address
     * @return
     */
    @RequestMapping(value = "testCustomerObjectAndSimple")
    @ResponseBody
    public User testCustomerObjectAndSimple(User user, String address) {
        logger.info(user.toString());
        logger.info(address);

        return user;
    }

    /**
     * curl -H "Content-Type: application/x-www-form-urlencoded" -d 'addressbook=cat_123&address=hebei' "http://localhost:8080/testRequestParamAndSimple"
     * @param user
     * @param address
     * @return
     */
    @RequestMapping(value = "testRequestParamAndSimple")
    @ResponseBody
    public User testRequestParamAndSimple(@RequestParam User user, String address) {
        logger.info(user.toString());
        logger.info(address);

        return user;
    }

    /**
     * curl -H "Content-Type: application/x-www-form-urlencoded" -d 'addressbook=cat_123&name=damon' "http://localhost:8080/testRequestParamAndRequestBody"
     * 混用RequestParam和RequestBody，各管各的
     * 同样，address是从request body中拿到的完整String：addressbook=cat_123&name=damon
     看日志：
     13:49:36.209 [http-bio-8080-exec-4] INFO  com.damon.spring.bean.UserEditor - UserEditor text:cat_123
     13:49:36.210 [http-bio-8080-exec-4] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Reading [java.lang.String] as "application/x-www-form-urlencoded;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@105feeb3]
     13:49:36.210 [http-bio-8080-exec-4] INFO  c.d.s.controller.BindingController - User{name='cat', password='123', married=true, birthday=Fri Mar 18 13:49:36 CST 2016}
     13:49:36.211 [http-bio-8080-exec-4] INFO  c.d.s.controller.BindingController - addressbook=cat_123&name=damon
     13:49:36.213 [http-bio-8080-exec-4] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Written [User{name='cat', password='123', married=true, birthday=Fri Mar 18 13:49:36 CST 2016}] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@73b5f805]
     13:49:36.214 [http-bio-8080-exec-4] DEBUG o.s.web.servlet.DispatcherServlet - Null ModelAndView returned to DispatcherServlet with name 'springServlet': assuming HandlerAdapter completed request handling
     13:49:36.214 [http-bio-8080-exec-4] DEBUG o.s.web.servlet.DispatcherServlet - Successfully completed request
     * @param user 使用自定义的UserEditor将request中的user=cat_123转化为User对象
     * @param address 使用RequestResponseBodyMethodProcessor处理，通过StringHttpMessageConverter将request body的完整字符串赋给address
     * @return
     */
    @RequestMapping(value = "testRequestParamAndRequestBody")
    @ResponseBody
    public User testRequestParamAndRequestBody(@RequestParam User user, @RequestBody String address) {
        logger.info(user.toString());
        logger.info(address);

        return user;
    }

    /**
     * curl -H "Content-Type: text/xml" -H "Accept: application/xml" -d '<?xml version="1.0" encoding="utf-8"?>
     <RatePlan Name="damon">
     <ID>123</ID>
     </RatePlan>' "http://localhost:8080/testXmlBody"
     日志：
     16:09:55.806 [http-bio-8080-exec-3] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Reading [com.damon.spring.bean.RatePlan] as "text/xml;charset=UTF-8" using [org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter@660e8081]
     16:09:55.808 [http-bio-8080-exec-3] INFO  c.d.s.controller.BindingController - RatePlan{id='123', name='damon'}
     16:09:55.810 [http-bio-8080-exec-3] DEBUG o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Written [RatePlan{id='123', name='damon'}] as "application/xml" using [org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter@660e8081]
     * @param ratePlan
     * @return
     */
    @RequestMapping(value = "testXmlBody")
    @ResponseBody
    public RatePlan testXmlBody(@RequestBody RatePlan ratePlan) {
        logger.info(ratePlan.toString());
        return ratePlan;
    }

    /**
     * curl -d "hobbies=basketball" "http://localhost:8080/testCustomerConverter"
     *
     * @param stringArray 使用CustomerStringToStringArray转换器将String转换为String[]
     * @return
     */
    @RequestMapping(value = "testCustomerConverter")
    @ResponseBody
    public StringArray testCustomerConverter(StringArray stringArray) {
        logger.info(stringArray.toString());
        return stringArray;
    }

    //绑定自定义PropertyEditor
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //虽然Spring写了一个DateEditor，但是默认没有注册，这里手动注册下
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), false));
        //把UserEditor扔到了跟User同一个包下，可以省去注册
        //binder.registerCustomEditor(User.class, new UserEditor());
    }
}
