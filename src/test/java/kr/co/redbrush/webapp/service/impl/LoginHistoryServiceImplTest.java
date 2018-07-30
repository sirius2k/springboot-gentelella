package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.LoginHistory;
import kr.co.redbrush.webapp.repository.LoginHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

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

    private LoginHistory loginHistory;
    private Long id = 1L;
    private Date loginDate = new Date();

    @Before
    public void before() {
        loginHistory = new LoginHistory();
        loginHistory.setId(id);
        loginHistory.setLoginDate(loginDate);
    }

    @Test
    public void testInsert() {
        when(loginHistoryRepository.save(loginHistory)).thenReturn(loginHistory);

        LoginHistory expectedLoginHistory = loginHistoryService.insert(loginHistory);

        LOGGER.debug("loginHistory : {}", expectedLoginHistory);

        assertThat("Unexpected value.", loginHistory, is(expectedLoginHistory));
    }
}