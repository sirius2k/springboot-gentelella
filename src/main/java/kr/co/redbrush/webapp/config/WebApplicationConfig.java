package kr.co.redbrush.webapp.config;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import kr.co.redbrush.webapp.controller.interceptors.SpringSecurityInterceptor;
import kr.co.redbrush.webapp.handlebars.SpringSecurityAuthorizeHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;

@Configuration
public class WebApplicationConfig {
    @Autowired
    private HandlebarsViewResolver handlebarsViewResolver;

    @Autowired
    private SpringSecurityAuthorizeHelper springSecurityAuthorizeHelper;

    @PostConstruct
    public void registerHelper() {
        handlebarsViewResolver.registerHelper(SpringSecurityAuthorizeHelper.NAME, springSecurityAuthorizeHelper);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
