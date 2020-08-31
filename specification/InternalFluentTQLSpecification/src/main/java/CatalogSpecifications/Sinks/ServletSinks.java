package CatalogSpecifications.Sinks;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Sinks definition for Servlet applications
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
