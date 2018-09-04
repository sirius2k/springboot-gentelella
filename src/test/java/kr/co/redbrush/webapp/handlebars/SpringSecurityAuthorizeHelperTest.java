package kr.co.redbrush.webapp.handlebars;

import com.github.jknack.handlebars.Options;
import kr.co.redbrush.webapp.SpringBootWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@TestPropertySource(locations="classpath:application-test.properties")
@Slf4j
public class SpringSecurityAuthorizeHelperTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Autowired
    private SpringSecurityAuthorizeHelper helper;

    @Mock
    private Object context;

    @Mock
    private Options options;

    @Mock
    private Options.Buffer buffer;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Before
    public void before() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    public void testApplyWithoutAuthentication() throws Exception {
        String optionInverse = "inverse";

        when(context.toString()).thenReturn("hasRole('ROLE_ADMIN')");
        when(options.buffer()).thenReturn(buffer);
        when(options.inverse()).thenReturn(optionInverse);

        Object object = helper.apply(context, options);

        assertThat("Unexpected value.", object, is(buffer));

        verify(buffer).append(optionInverse);
    }

    @Test
    public void testApplyWithAuthentication() throws Exception {
        String optionFn = "fn";
        SecurityContextHolder.setContext(securityContext);

        when(context.toString()).thenReturn("hasRole('ROLE_ADMIN')");
        when(options.buffer()).thenReturn(buffer);
        when(options.inverse()).thenReturn(optionFn);

        Object object = helper.apply(context, options);

        assertThat("Unexpected value.", object, is(buffer));

        verify(buffer).append(optionFn);
    }
}