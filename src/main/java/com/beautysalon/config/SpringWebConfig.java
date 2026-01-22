package com.beautysalon.config;

//import com.beautysalon.webmvc.CartFormatter;
import com.beautysalon.webmvc.CategoryFormatter;
import com.beautysalon.webmvc.ProductFormatter;
import com.beautysalon.webmvc.ServiceEntityFormater;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration
@EnableWebMvc
@ComponentScan
public class SpringWebConfig implements ApplicationContextAware, WebMvcConfigurer {

    private ApplicationContext applicationContext;


    public void setApplicationContext(final @NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addFormatter(categoryFormatter());
        registry.addFormatter(productFormatter());
        registry.addFormatter(serviceEntityFormater());
        registry.addFormatter(dateFormatter());
//        registry.addFormatter(cartFormater());
    }

    @Bean
    public CategoryFormatter categoryFormatter() {
        return new CategoryFormatter();
    }

    @Bean
    public ProductFormatter productFormatter() {
        return new ProductFormatter();
    }

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    @Bean
    public ServiceEntityFormater serviceEntityFormater() {
        return new ServiceEntityFormater();
    }
//    @Bean
//    public CartFormatter cartFormater() {
//        return new CartFormatter();
//    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/users/login");
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(springSecurityDialect());
        templateEngine.addDialect(layoutDialect());
        templateEngine.addDialect(new Java8TimeDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }


    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
