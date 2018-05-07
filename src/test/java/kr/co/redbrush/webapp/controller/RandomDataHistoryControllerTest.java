package kr.co.redbrush.webapp.controller;

import kr.co.redbrush.webapp.SpringBootWebApplication;
import kr.co.redbrush.webapp.controller.admin.IndexController;
import kr.co.redbrush.webapp.admin.test.TestVariables;
import kr.co.redbrush.webapp.domain.RandomData;
import kr.co.redbrush.webapp.service.RandomDataService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IndexController.class)
@ContextConfiguration(classes={SpringBootWebApplication.class})
@TestPropertySource(locations= TestVariables.APPLICATION_TEST_PROPERTIES)
@Slf4j
public class RandomDataHistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${time.range.default:120}")
    private Integer defaultTimeRange;

    @MockBean
    private RandomDataService randomDataService;

    private RandomData randomData = new RandomData(1L, 100, 1L, new Date());
    private List<RandomData> randomDataHistory = new ArrayList<RandomData>();
    private Integer timeRangeInMinutes = 180;

    @Before
    public void before() {
    }

    @Test
    public void testIndexWithOutTimeRange() throws Exception {
        when(randomDataService.getLastOne()).thenReturn(randomData);
        when(randomDataService.getListBetween(any(Date.class), any(Date.class))).thenReturn(randomDataHistory);

        this.mockMvc.perform(get("/")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(model().attribute("timeRangeInMinutes", defaultTimeRange ))
                .andExpect(model().attribute("randomData", randomData ))
                .andExpect(model().attribute("randomDataHistory", randomDataHistory ))
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
                .andDo(print());

        verify(randomDataService).getLastOne();
    }

    @Test
    public void testIndexWithGivenTimeRange() throws Exception {
        when(randomDataService.getLastOne()).thenReturn(randomData);
        when(randomDataService.getListBetween(any(Date.class), any(Date.class))).thenReturn(randomDataHistory);

        this.mockMvc.perform(get("/")
                .param("timeRangeInMinutes", timeRangeInMinutes.toString())
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(model().attribute("timeRangeInMinutes", timeRangeInMinutes ))
                .andExpect(model().attribute("randomData", randomData ))
                .andExpect(model().attribute("randomDataHistory", randomDataHistory ))
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
                .andDo(print());

        verify(randomDataService).getLastOne();
    }

    @Test
    public void testIndexWithNoData() throws Exception {
        when(randomDataService.getLastOne()).thenReturn(null);
        when(randomDataService.getListBetween(any(Date.class), any(Date.class))).thenReturn(null);

        this.mockMvc.perform(get("/")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("randomData"))
                .andExpect(model().attributeDoesNotExist("randomDataHistory"))
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
                .andDo(print());

        verify(randomDataService).getLastOne();
    }
}