package kr.co.redbrush.webapp.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.redbrush.webapp.admin.SpringBootWebApplication;
import kr.co.redbrush.webapp.admin.domain.RandomValue;
import kr.co.redbrush.webapp.admin.service.RandomDataService;
import kr.co.redbrush.webapp.admin.test.TestVariables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RandomGeneratorController.class)
@ContextConfiguration(classes={SpringBootWebApplication.class})
@TestPropertySource(locations= TestVariables.APPLICATION_TEST_PROPERTIES)
@Slf4j
public class RandomGeneratorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RandomGeneratorController randomGeneratorController;

    @Value("${generator.startInclusive:0}")
    private int startInclusive;

    @Value("${generator.endInclusive:100}")
    private int endInclusive;

    @MockBean
    private RandomDataService randomDataService;

    private ObjectMapper mapper = new ObjectMapper();
    private RandomValue randomValue = new RandomValue(100);

    @Before
    public void before() {
    }

    @Test
    public void testGenerateRandom() throws Exception {
        when(randomDataService.generateRandom(startInclusive, endInclusive)).thenReturn(randomValue);

        String expectedContent = mapper.writeValueAsString(randomValue);

        this.mockMvc.perform(get("/random/generate")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent))
                .andDo(print());
    }
}