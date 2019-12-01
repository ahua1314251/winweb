package org.winker.winweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class MvcConfigurationLayout extends WebMvcConfigurationSupport {
    @Bean
    VelocityConfig velocityConfig() throws IOException {
        VelocityConfigurerCustom velocityConfig = new VelocityConfigurerCustom();
//		<property name="configLocation" value="classpath:/config/velocity.properties" />
        ResourceLoader resourceLoader =  new PathMatchingResourcePatternResolver(MvcConfigurationLayout.class.getClass().getClassLoader());
        velocityConfig.setResourceLoaderPath("/templates/");
        velocityConfig.setResourceLoader(resourceLoader);
        Properties properties = new Properties();
        properties.load(MvcConfigurationLayout.class.getResourceAsStream("/config/velocity.properties"));
        velocityConfig.setVelocityProperties(properties);
        return velocityConfig;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        VelocityLayoutViewResolver resolver = new VelocityLayoutViewResolver();
        resolver.setLayoutUrl("/templates/layout/layout.html");

        resolver.setSuffix(".html");
        resolver.setCache(false);
        resolver.setContentType("text/html;charset=UTF-8");
        registry.viewResolver(resolver);


    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(false).
                ignoreAcceptHeader(false).
                useJaf(false).
                defaultContentType(MediaType.TEXT_HTML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/static/dist/");
//
//
//            registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}
