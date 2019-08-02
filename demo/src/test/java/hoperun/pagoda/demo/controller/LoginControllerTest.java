package hoperun.pagoda.demo.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.bean.UserResponse;
import hoperun.pagoda.demo.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;
    @InjectMocks
    private LoginController controller;
    @Autowired
    private LoginService loginService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private UserResponse user = new UserResponse();

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        user.setToken("ass");
        Mockito.when(loginService.login("testuser", "123456")).thenReturn(user);
    }

    @Test
    public void LoginTest() throws Exception {
        BaseResponse<UserResponse> user1 = controller.login(new UserRequest("testuser", "123456"));
        Assert.assertEquals(this.user.getToken(), user1.getData().getToken());

    }
}
