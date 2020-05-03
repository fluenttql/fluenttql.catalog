package catalog.TrustBoundaryViolation.CWE501;

import catalog.SQLInjection.CWE89.DatabaseForJUnitTest;
import catalog.SQLInjection.CWE89.SQLInjectionUsingServletRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class TrustBoundaryViolationTest {
    private static StringWriter MYSW = null;
    private static PrintWriter MYPW = null;
    private static String MALICIOUSEMPID = "1\' OR 1 = 1 --";
    private static String EMPID = "1";

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    /**
     * This method set up the database server required for testing.
     *
     * @throws ClassNotFoundException If the JDBCDriver class is not found.
     * @throws SQLException           If the processing of SQL fails.
     */
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);
    }


    private void setHttpServlet(String empID) throws IOException {

        when(request.getParameter("user")).thenReturn(empID);

        MYSW = new StringWriter();
        MYPW = new PrintWriter(MYSW);

        when(response.getWriter()).thenReturn(MYPW);
    }

    /**
     * This test case tests for the scenario where attacker is trying SQL Injection
     * on an application that does not uses sanitizer, therefore SQL Injection
     * happens.
     *
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void attackerAccessingTheDataWithoutSanitizer() throws IOException, ServletException {
        setHttpServlet("ranjith");

        TrustBoundaryViolation myServlet = new TrustBoundaryViolation();
        myServlet.doGetWithoutSanitizer(request, response);
        String result = MYSW.getBuffer().toString().trim();
        System.out.println("\nResults when attacker tries to access the data without sanitizer:");
        System.out.println(result);
        System.out.println("Session id = " + response.containsHeader("session-id"));

        when(request.getParameter("session-id")).thenReturn("testing-id");
        myServlet.doGetWithoutSanitizer(request, response);

        result = MYSW.getBuffer().toString().trim();
        System.out.println("\nResults when attacker tries to access the data without sanitizer:");
        System.out.println(result);
    }
}
