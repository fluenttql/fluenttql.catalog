package CatalogSpecifications.SQLInjection.CWE89;

import CatalogSpecifications.Sinks.PreparedStatementSinks;
import CatalogSpecifications.Sources.ServletSources;
import InternalFluentTQL.dsl.CONSTANTS.LOCATION;
import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.dsl.TaintFlowQueryBuilder;
import InternalFluentTQL.fluentInterface.FluentTQLSpecification;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;
import InternalFluentTQL.fluentInterface.Query.TaintFlowQuery;
import InternalFluentTQL.fluentInterface.SpecificationInterface.FluentTQLUserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal FluentTQL specification for SQL-Injection with prepared statements.
 */
public class SQLiWithPreparedStatementsSpec implements FluentTQLUserInterface {
    /**
     * encodeForSQL is a OWASP sanitizer that encodes the SQL related data. Therefore, flow should go through this method to avoid vulnerability.
     */
    Method sanitizer = new MethodConfigurator("org.owasp.esapi.Encoder: java.lang.String encodeForSQL(org.owasp.esapi.codecs.Codec, java.lang.String)")
            .in().param(1)
            .out().returnValue().configure();

    /*
     * Below are the 6 required propagator that has to be called to create a PreparedStatement object.
     */
    Method requiredPropagator1 = new MethodConfigurator("java.sql.Connection: java.sql.PreparedStatement prepareStatement(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    Method requiredPropagator2 = new MethodConfigurator("java.sql.Connection: java.sql.PreparedStatement prepareStatement(java.lang.String, int)")
            .in().param(0)
            .out().returnValue().configure();

    Method requiredPropagator3 = new MethodConfigurator("java.sql.Connection: java.sql.PreparedStatement prepareStatement(java.lang.String, java.lang.String[])")
            .in().param(0)
            .out().returnValue().configure();

    Method requiredPropagator4 = new MethodConfigurator("java.sql.Connection: java.sql.PreparedStatement prepareStatement(java.lang.String, int[])")
            .in().param(0)
            .out().returnValue().configure();

    Method requiredPropagator5 = new MethodConfigurator("java.sql.Connection: java.sql.PreparedStatement prepareStatement(java.lang.String, int, int)")
            .in().param(0)
            .out().returnValue().configure();

    Method requiredPropagator6 = new MethodConfigurator("java.sql.Connection: java.sql.PreparedStatement prepareStatement(java.lang.String, int, int, int)")
            .in().param(0)
            .out().returnValue().configure();

    MethodSet requiredPropagator = new MethodSet("requiredPropagator")
            .addMethod(requiredPropagator1)
            .addMethod(requiredPropagator2)
            .addMethod(requiredPropagator3)
            .addMethod(requiredPropagator4)
            .addMethod(requiredPropagator5)
            .addMethod(requiredPropagator6);

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        TaintFlowQuery myTF = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources)
                .notThrough(sanitizer)
                .through(requiredPropagator)
                .to(PreparedStatementSinks.prepSinks)
                .report("SQL-Injection, even though its prepared statement - CWE89!!!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(myTF);

        return myFluentTQLSpecs;
    }
}
