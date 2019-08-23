package hoperun.pagoda.demo.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.LoginResponse;
import hoperun.pagoda.demo.bean.UserRequest;
import hoperun.pagoda.demo.service.impl.LoginServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    @InjectMocks
    private LoginController controller;

    @Mock
    private LoginServiceImpl loginService;

    private LoginResponse user = new LoginResponse();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user.setToken("ass");
        Mockito.when(this.loginService.login(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
    }

    @Test
    public void LoginTest() throws Exception {
        BaseResponse<LoginResponse> user1 = controller.login(new UserRequest("testuser", "123456"));
        Assert.assertEquals(this.user.getToken(), user1.getData().getToken());

    }
}
