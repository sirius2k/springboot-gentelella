package kr.co.redbrush.webapp.controller;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import kr.co.redbrush.webapp.SpringBootWebApplication;
import kr.co.redbrush.webapp.admin.test.TestVariables;
import kr.co.redbrush.webapp.controller.admin.IndexController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IndexController.class)
@ContextConfiguration(classes ={ SpringBootWebApplication.class})
@TestPropertySource(locations = TestVariables.APPLICATION_TEST_PROPERTIES)
@Slf4j
@Ignore("Need to implement controller base test")
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // TODO : Add MockBean fork mocking
    //@MockBean
    //private AuthenticationService authenticationService;

    @Before
    public void before() {
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setPrefix("classpath:templates/");
        viewResolver.setSuffix(".hbs");

        mockMvc = MockMvcBuilders.standaloneSetup(new IndexController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    @Ignore("Need to resolve ViewResolver problem with Handlebars")
    public void testIndex() throws Exception {
        String expectedContent = "Springboot Web Service";

        this.mockMvc.perform(get("/").accept(MediaType.ALL))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(expectedContent)))
            /*
            TODO : Remove after any other examples were added.
            .andExpect(model().attribute("timeRangeInMinutes", defaultTimeRange ))
            .andExpect(model().attribute("randomData", randomData ))
            .andExpect(model().attribute("randomDataHistory", randomDataHistory ))
            */
            .andExpect(view().name("index"));
    }
}
