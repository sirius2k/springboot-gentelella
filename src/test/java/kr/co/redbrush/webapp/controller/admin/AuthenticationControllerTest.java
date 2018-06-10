package kr.co.redbrush.webapp.controller.admin;

import kr.co.redbrush.webapp.SpringBootWebApplication;
import kr.co.redbrush.webapp.test.TestVariables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AuthenticationController.class)
@ContextConfiguration(classes ={ SpringBootWebApplication.class})
@TestPropertySource(locations = TestVariables.APPLICATION_TEST_PROPERTIES)
@Slf4j
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    protected MockHttpSession session;

    @Before
    public void before() {
        session = new MockHttpSession();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testLoginForm() throws Exception {
        this.mockMvc.perform(get("/login/form").accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testLoginFormWithErrorParameter() throws Exception {
        this.mockMvc.perform((get("/login/form").accept(MediaType.ALL)).param("error", "true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", true));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testLoginFormWithBadCredential() throws Exception {
        String sessionKey = "SPRING_SECURITY_LAST_EXCEPTION";
        String exceptionMessage = "Bad Credential";

        session.setAttribute(sessionKey, new BadCredentialsException("Bad Credential"));

        this.mockMvc.perform((get("/login/form").accept(MediaType.ALL)).param("error", "true").session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("errorMessage", exceptionMessage))
                .andExpect(model().attribute("error", true));
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testLogin() throws Exception {
        this.mockMvc.perform((post("/login").accept(MediaType.ALL).param("id", "admin").param("password", "admin").with(csrf())))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(request().sessionAttribute("SPRING_SECURITY_LAST_EXCEPTION", instanceOf(BadCredentialsException.class)));
    }
}
