import com.frame.core.webapp.filter.GeneralFilter;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/3.
 */
@SpringBootApplication
@Configuration
@ImportResource({"classpath*:config/spring*.xml"})
@ComponentScan("com.frame.notexist")
@ControllerAdvice
public class Application extends SpringBootServletInitializer {

    @Bean
    public FilterRegistrationBean getGeneralFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new GeneralFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean getOpenSessionInViewFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new OpenSessionInViewFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean getConfigurableSiteMeshFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new ConfigurableSiteMeshFilter(){
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
                builder.addDecoratorPath("/**", "/decorator")
                        .addExcludedPath("/resources**")
                        .addExcludedPath("**ajax**")
                        .addExcludedPath("/resources**")
                        .addExcludedPath("/login");
            }
        });
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
