package kr.co.redbrush.webapp.form;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Slf4j
public class SignupFormTest extends FormValidationTestBase {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    private String validId = "testid";
    private String validName = "testid";
    private String validEmail = "test@test.com";
    private String validPassword = "Password123!";

    @BeforeClass
    public static void beforeClass() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void afterClass() {
        validatorFactory.close();
    }

    @Test
    public void testValidation() {
        testValidation(new SignupForm(validId, validName, validEmail, validPassword), 0);
    }

    private Set<ConstraintViolation<SignupForm>> testValidation(SignupForm signupForm, int expectedViolationCount) {
        Set<ConstraintViolation<SignupForm>> violations = validator.validate(signupForm);

        LOGGER.debug("Violations : {}", violations);

        assertThat("Validation test failed. SignupForm = " + signupForm, violations.size(), is(0));

        return violations;
    }
}