package CatalogSpecifications.NoSQLInjection.CWE943;

import InternalFluentTQL.dsl.CONSTANTS.LOCATION;
import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.TaintFlowQueryBuilder;
import InternalFluentTQL.fluentInterface.FluentTQLSpecification;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;
import InternalFluentTQL.fluentInterface.Query.TaintFlowQuery;
import InternalFluentTQL.fluentInterface.SpecificationInterface.FluentTQLUserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal FluentTQL specification for No-SQL-Injection with multiple sources.
 */
public class NoSQLInjectionWithMultipleSources implements FluentTQLUserInterface {
    //First source that takes userName from the user.
    Method source1 = new MethodConfigurator("catalog.NoSQLInjection.CWE943.NoSQLInjectionWithTwoSources: java.lang.String getUserName()")
            .out().returnValue().configure();

    //Second source that takes old password from the user.
    Method source2 = new MethodConfigurator("catalog.NoSQLInjection.CWE943.NoSQLInjectionWithTwoSources: java.lang.String getOldPassword()")
            .out().returnValue().configure();

    //Third souce that takes new password from the user.
    Method source3 = new MethodConfigurator("catalog.NoSQLInjection.CWE943.NoSQLInjectionWithTwoSources: java.lang.String getNewPassword()")
            .out().returnValue().configure();

    //sanitizeForMongoDB is user defined simple sanitizer for mongodb.
    Method sanitizer = new MethodConfigurator("catalog.NoSQLInjection.CWE943.NoSQLInjectionWithTwoSources: java.lang.String sanitizeForMongoDB(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    //put is a method that the data flow has to go through after sanitizer to form a filer to update the password. 
    //If the data flow goes through this method before sanitizer then there will be a security vulnerability.
    Method requiredPropagator1 = new MethodConfigurator("com.mongodb.BasicDBObject: com.mongodb.BasicDBObject put(java.lang.String, java.lang.String)")
            .in().param(1)
            .out().thisObject().configure();

    //this put is a method that the data flow has to go through after sanitizer to form a new BasicDBObject password to update the password in mongodb. 
    //If the data flow goes through this method before sanitizer then there will be a security vulnerability.
    Method requiredPropagator2 = new MethodConfigurator("com.mongodb.BasicDBObject: com.mongodb.BasicDBObject put(java.lang.String, com.mongodb.BasicDBObject)")
            .in().param(1)
            .out().thisObject().configure();

    //updateOne is a sink that updates the password.
    Method sink = new MethodConfigurator("com.mongodb.client.MongoCollection: com.mongodb.client.FindIterable updateOne(com.mongodb.BasicDBObject, com.mongodb.BasicDBObject)")
            .in().param(0).param(1).configure();

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        TaintFlowQuery noSQLWithMultipleSourcesSpec = new TaintFlowQueryBuilder()
                .from(source1).notThrough(sanitizer).through(requiredPropagator1).to(sink)
                .and()
                .from(source2).notThrough(sanitizer).through(requiredPropagator1).to(sink)
                .and()
                .from(source3).notThrough(sanitizer).through(requiredPropagator2).to(sink)
                .report("There is a No-SQL-Injection (CWE943) with multiple sources")
                .at(LOCATION.SINK)
                .build();

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(noSQLWithMultipleSourcesSpec);

        return myFluentTQLSpecs;
    }
}
