import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginAppTest {

    private LoginApp loginApp;

    @Before
    public void setUp() {
        loginApp = new LoginApp();
    }

    @Test
    public void testValidLogin() {

        String userName = loginApp.authenticateUser("jarar");


        assertNotNull("Username should not be null for a valid login", userName);


        assertEquals("Welcome message should contain the correct username", "jarar", userName);
    }

    @Test
    public void testInvalidEmail() {

        String userName = loginApp.authenticateUser("invalid.email@example.com");

        assertNull("Username should be null for an invalid email", userName);
    }

    @Test
    public void testInvalidPassword() {

        String userName = loginApp.authenticateUser("valid.email@example.com");

        assertNull("Username should be null for an incorrect password", userName);
    }

    @Test
    public void testEmptyFields() {

        String userName = loginApp.authenticateUser("");

        assertNull("Username should be null if email is empty", userName);
    }

    @Test
    public void testSqlInjectionAttempt() {

        String userName = loginApp.authenticateUser("' OR '1'='1");

        assertNull("Username should be null for an SQL injection attempt", userName);
    }
}
