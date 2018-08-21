package kr.co.redbrush.webapp.config;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import kr.co.redbrush.webapp.handlebars.SpringSecurityHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class WebApplicationConfig {
    @Autowired
    private HandlebarsViewResolver handlebarsViewResolver;

    @Autowired
    private SpringSecurityHelper springSecurityHelper;

    @PostConstruct
    public void registerHelper() {
        handlebarsViewResolver.registerHelper(SpringSecurityHelper.NAME, springSecurityHelper);
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
