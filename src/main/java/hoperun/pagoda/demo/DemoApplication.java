package hoperun.pagoda.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * start class.
 * @author zhangxiqin
 *
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class DemoApplication {

    /**
     * start method.
     * @param args parameters
     */
    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    //
    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    // return builder.sources(DemoApplication.class);
    // }
}
