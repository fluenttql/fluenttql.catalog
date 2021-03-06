package CatalogSpecifications.Sinks;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Sinks definition for Prepared-Statements
 */
public class PreparedStatementSinks {
    private static final Method sink1 = new MethodConfigurator("java.sql.PreparedStatement: java.sql.ResultSet executeQuery()")
            .in().thisObject().configure();

    private static final Method sink2 = new MethodConfigurator("java.sql.PreparedStatement: java.sql.ResultSet executeQuery(java.lang.String)")
            .in().thisObject().param(0).configure();

    private static final Method sink3 = new MethodConfigurator("java.sql.PreparedStatement: boolean execute()")
            .in().thisObject().configure();

    private static final Method sink4 = new MethodConfigurator("java.sql.PreparedStatement: boolean execute(java.lang.String)")
            .in().thisObject().param(0).configure();

    public static MethodSet prepSinks = new MethodSet("PrepSinks")
            .addMethod(sink1)
            .addMethod(sink2)
            .addMethod(sink3)
            .addMethod(sink4);
}
