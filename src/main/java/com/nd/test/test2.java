package com.nd.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
 * 测试Controller层
 * springmvc单元测试--使用spring-test
 * 
 * 引入包：junit-4.12  spring-test-4.3.2.RELEASE
 * 
 * mock测试就是在测试过程中，对于某些不容易构造或者不容易获取的对象，用一个虚拟的对象来创建以便测试的测试方法。
 * simple方法中的代码中的方法：
  perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；

  andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；

  andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台；
 * 
 * 详细学习：http://sishuok.com/forum/posts/list/7981.html
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)    //spring-test测试套件
@WebAppConfiguration   //容器
@ContextConfiguration({"classpath*:/spring.xml","classpath*:/springmvc.xml"})    //配置文件,两个容器都要加载
public class test2 {

	//容器
    @Autowired  
    private WebApplicationContext wac;
		
	 // 模拟request,response  
    private MockHttpServletRequest request;  
    private MockHttpServletResponse response;   
    
    // 模拟mvc
    private MockMvc mockMvc; 
    

     /*
      * 这里写需要测试的controller  
      */
  
    
    

    
     // 执行测试方法之前初始化模拟request,response，mockMvc
    @Before    
    public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();   
        mockMvc =MockMvcBuilders.webAppContextSetup(wac).build(); //得到模拟环境     
    }   
    
    /*
     * 测试请求和返回
     */
   /* @Test
    public void t1(){
    	Product p=new Product();
    	p.setName("冰箱");
    	 
    	request.setParameter("userName", "admin");
    	request.setParameter("password", "1");
    	request.setAttribute("aa", "ddd");
        Assert.assertEquals("before/product/product_list",productController.list(null, p)) ;  
    }*/
    
    
    /*
     * 测试URL路径，常用，可查看参数和返回值是否正确
     */
    
    @Test  
    public void t2() throws Exception {  
        mockMvc.perform((
        		post("/topic/getlist.do")
        		.characterEncoding("UTF-8")
        		.param("1")
//        		.param("psw", "abc")
        		
        		))        		
                .andDo(print()                			
                ).andReturn();  
  
    } 
    
    
}
