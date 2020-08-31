package CatalogSpecifications.Sinks;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Sinks definition for Log-Injection
 */
public class LogInjectionSinks {
    //Below are few of the list of sinks for Log-Injection.

    private static final Method sink1 = new MethodConfigurator("java.util.logging.Logger: void info(java.lang.String)")
            .in().param(0).configure();

    private static final Method sink2 = new MethodConfigurator("java.util.logging.Logger: void log(java.util.logging.Level, java.lang.String)")
            .in().param(1).configure();

    private static final Method sink3 = new MethodConfigurator("java.util.logging.Logger: void entering(java.lang.String, java.lang.String)")
            .in().param(0).param(1).configure();

    private static final Method sink4 = new MethodConfigurator("java.util.logging.Logger: void exiting(java.lang.String, java.lang.String)")
            .in().param(0).param(1).configure();

    public static MethodSet logInjectionSinks = new MethodSet("logInjectionSinks")
            .addMethod(sink1)
            .addMethod(sink2)
            .addMethod(sink3)
            .addMethod(sink4);
}
