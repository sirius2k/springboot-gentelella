package kr.co.redbrush.webapp.controller.interceptors;

import kr.co.redbrush.webapp.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
public class SpringSecurityInterceptorTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private SpringSecurityInterceptor springSecurityInterceptor = new SpringSecurityInterceptor();

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse reponse;

    @Mock
    private ModelAndView modelAndView;

    @Mock
    private Object handler;

    private Account account = new Account();

    @Before
    public void before() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(account);
    }

    @Test
    public void testPostHandle() throws Exception {
        SecurityContextHolder.setContext(securityContext);

        springSecurityInterceptor.postHandle(request, reponse, handler, modelAndView);

        verify(modelAndView).addObject("principal", account);
    }

    @Test
    public void postHandleWithoutAuthentication() throws Exception {
        springSecurityInterceptor.postHandle(request, reponse, handler, modelAndView);

        verify(modelAndView, times(0)).addObject(eq("principal"), any());
    }
}