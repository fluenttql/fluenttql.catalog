package CatalogSpecifications.Sinks;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Sinks definition for SQL.
 */
public class SQLSinks {
    //Below are the few sink methods from SQL.

    private static final Method sink1 = new MethodConfigurator("java.sql.Statement: java.sql.ResultSet executeQuery(java.lang.String)")
            .in().param(0).configure();

    private static final Method sink2 = new MethodConfigurator("java.sql.Statement: int executeUpdate(java.lang.String)")
            .in().param(0).configure();

    private static final Method sink3 = new MethodConfigurator("java.sql.Statement: int executeQuery(java.lang.String, int)")
            .in().param(0).configure();

    private static final Method sink4 = new MethodConfigurator("java.sql.Statement: long executeLargeUpdate(java.lang.String)")
            .in().param(0).configure();

    private static final Method sink5 = new MethodConfigurator("java.sql.Statement: boolean execute(java.lang.String)")
            .in().param(0).configure();

    public static MethodSet sqlSinks = new MethodSet("sqlSinks")
            .addMethod(sink1)
            .addMethod(sink2)
            .addMethod(sink3)
            .addMethod(sink4)
            .addMethod(sink5);
}
