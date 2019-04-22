package im.vec.config;

import cc.eamon.open.permission.mvc.PermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2018-10-27 19:49:50
 */
@Configuration
public class RoleMethodConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RoleMethodChecker roleMethodChecker(){
        return new RoleMethodChecker();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor(roleMethodChecker()))
                .addPathPatterns("/**");

        super.addInterceptors(registry);
    }

//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver getCommonsMultipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(20971520);
//        multipartResolver.setMaxInMemorySize(1048576);
//        return multipartResolver;
//    }
}
