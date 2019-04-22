package im.vec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan("im.vec")
@EnableTransactionManagement
@MapperScan("im.vec.*.dao")
public class AppStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }

    //部署时用
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(AppStarter.class);
    }

}
