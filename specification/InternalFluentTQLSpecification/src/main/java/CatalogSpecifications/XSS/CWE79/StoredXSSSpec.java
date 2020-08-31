package CatalogSpecifications.XSS.CWE79;

import CatalogSpecifications.Sinks.ServletSinks;
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
 * Internal FluentTQL specification for Stored-XSS.
 */
public class StoredXSSSpec implements FluentTQLUserInterface {
    /*
     * For Stored XSS below are the two workarounds to have fluentTQL specifications.
     * 1. Use the SQL Injection fluentTQL specification to avoid storing the malicious comments into database.
     * 2. Use the below specification to avoid sending the malicious comments from database to client.
     *
     * For the actual solution, there is a need to changing the fluentTQL language in future.
     */

    /**
     * getLastComment is a source that returns the sensitive data from the database.
     */
    Method source = new MethodConfigurator("catalog.XSS.CWE79.StoredXSS: java.lang.String getLastComment()")
            .out().returnValue().configure();

    /**
     * encodeForHTML is a OWASP sanitizer that encodes the HTML related data. Therefore, flow should go through this method to avoid vulnerability.
     */
    Method sanitizer = new MethodConfigurator("org.owasp.esapi.Encoder: java.lang.String encodeForHTML(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    /**
     * decodeForHTML is a OWASP de-sanitizer that decodes the HTML related data back to HTML entities. Therefore, flow should not go though this method to avoid
     * vulnerability.
     */
    Method deSanitizer = new MethodConfigurator("org.owasp.esapi.Encoder: java.lang.String decodeForHTML(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        TaintFlowQuery myTF = new TaintFlowQueryBuilder()
                .from(source)
                .through(deSanitizer)
                .notThrough(sanitizer)
                .to(ServletSinks.servletSinks)
                .report("Stored XSS - CWE79!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(myTF);

        return myFluentTQLSpecs;
    }
}
