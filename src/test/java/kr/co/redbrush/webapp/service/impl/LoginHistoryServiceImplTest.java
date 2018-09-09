package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.AccessHistory;
import kr.co.redbrush.webapp.repository.LoginHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@Slf4j
public class LoginHistoryServiceImplTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    public LoginHistoryServiceImpl loginHistoryService = new LoginHistoryServiceImpl();

    @Mock
    private LoginHistoryRepository loginHistoryRepository;

    private AccessHistory accessHistory;
    private Long id = 1L;
    private LocalDateTime loginDate = LocalDateTime.now();

    @Before
    public void before() {
        accessHistory = new AccessHistory();
        accessHistory.setId(id);
        accessHistory.setLoginDate(loginDate);
    }

    @Test
    public void testInsert() {
        when(loginHistoryRepository.save(accessHistory)).thenReturn(accessHistory);

        AccessHistory expectedAccessHistory = loginHistoryService.insert(accessHistory);

        LOGGER.debug("accessHistory : {}", expectedAccessHistory);

        assertThat("Unexpected value.", accessHistory, is(expectedAccessHistory));
    }
}