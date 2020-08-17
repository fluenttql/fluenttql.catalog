package Sinks;

import de.fraunhofer.iem.secucheck.InternalFluentTQL.dsl.MethodConfigurator;
import de.fraunhofer.iem.secucheck.InternalFluentTQL.dsl.MethodSet;
import de.fraunhofer.iem.secucheck.InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Sinks definition for Servlet applications
 *
 * @author Ranjith Krishnamurthy
 */
public class ServletSinks {
    //Below are the few sink methods from Servlet application.

    private static final Method sink1 = new MethodConfigurator("java.io.PrintWriter: java.io.PrintWriter append(java.lang.String)")
            .in().param(0).configure();

    private static final Method sink2 = new MethodConfigurator("java.io.PrintWriter: void print(java.lang.String)")
            .in().param(0).configure();

    private static final Method sink3 = new MethodConfigurator("java.io.PrintWriter: void println(java.lang.String)")
            .in().param(0).configure();

    public static MethodSet servletSinks = new MethodSet("servletSinks")
            .addMethod(sink1)
            .addMethod(sink2)
            .addMethod(sink3);
}
