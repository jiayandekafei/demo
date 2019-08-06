package hoperun.pagoda.demo.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hoperun.pagoda.demo.bean.UserResponse;
import hoperun.pagoda.demo.service.impl.LoginServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    @InjectMocks
    private LoginServiceImpl loginService;

    private UserResponse user = new UserResponse();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user.setToken("ass");
        Mockito.when(this.loginService.login(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
    }

}
