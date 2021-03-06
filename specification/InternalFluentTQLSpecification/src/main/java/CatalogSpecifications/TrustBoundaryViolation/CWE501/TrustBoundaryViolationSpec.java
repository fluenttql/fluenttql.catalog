package CatalogSpecifications.TrustBoundaryViolation.CWE501;

import CatalogSpecifications.Sources.ServletSources;
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
 * Internal FluentTQL specification for Trust-Boundary violation.
 */
public class TrustBoundaryViolationSpec implements FluentTQLUserInterface {
    /*
     * Note: This is an example of trust boundary violation. To avoid trust boundary violation, it is better never to send user input to session object.
     */

    /*
     * This is a sanitizer for this example that authenticate the username and then returns the valid username. If authentication fails then
     * it returns the null so that user input will not be directly set in session object to avoid trust boundary violation.
     */
    Method sanitizer = new MethodConfigurator("catalog.TrustBoundaryViolation.CWE501.TrustBoundaryViolation: java.lang.String authenticate(java.lang.String, java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    //sink
    Method sink = new MethodConfigurator("javax.servlet.http.HttpSession: void setAttribute(java.lang.String, java.lang.Object)")
            .in().param(1).configure();

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        TaintFlowQuery myTF = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources)
                .notThrough(sanitizer)
                .to(sink)
                .report("Trust Boundary Violation here!!!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(myTF);

        return myFluentTQLSpecs;
    }
}
