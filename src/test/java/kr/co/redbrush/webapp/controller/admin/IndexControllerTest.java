package kr.co.redbrush.webapp.controller.admin;

import kr.co.redbrush.webapp.SpringBootWebApplication;
import kr.co.redbrush.webapp.controller.admin.IndexController;
import kr.co.redbrush.webapp.test.TestVariables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IndexController.class)
@ContextConfiguration(classes ={ SpringBootWebApplication.class})
@TestPropertySource(locations = TestVariables.APPLICATION_TEST_PROPERTIES)
@Slf4j
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void before() {
    }

    @Test
    public void testIndexWithoutAuthentication() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testIndex() throws Exception {
        String expectedContent = "Springboot Web Service";

        this.mockMvc.perform(get("/").accept(MediaType.ALL))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andExpect(model().attribute("title", expectedContent));
    }
}
