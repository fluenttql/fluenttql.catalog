package CatalogSpecifications.PathTraversalAttack.CWE22;

import CatalogSpecifications.Sources.ServletSources;
import InternalFluentTQL.dsl.CONSTANTS.LOCATION;
import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.QueriesSet;
import InternalFluentTQL.dsl.TaintFlowQueryBuilder;
import InternalFluentTQL.fluentInterface.FluentTQLSpecification;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;
import InternalFluentTQL.fluentInterface.Query.TaintFlowQuery;
import InternalFluentTQL.fluentInterface.SpecificationInterface.FluentTQLUserInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal FluentTQL specification for Path-Traversal attack.
 */
public class PathTraversalAttackSpec implements FluentTQLUserInterface {
    /**
     * This is a user defined sanitizer for avoiding path traversal attack. This is not recommended to use this method. This is for example.
     */
    Method sanitizer = new MethodConfigurator("catalog.PathTraversalAttack.PathTraversal: java.lang.String sanitizeForPATH(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    /**
     * This is a required propagator that is used in the second taint flow to achieve path traversal attack.
     */
    Method requiredPropagator = new MethodConfigurator("java.io.File: java.io.File File(java.lang.String)")
            .in().param(0)
            .out().thisObject().configure();

    /**
     * This is the sink in first taint flow where File constructor is not used.
     */
    Method sink1 = new MethodConfigurator("java.io.FileInputStream: java.io.FileInputStream FileInputStream(java.lang.String)")
            .in().param(0).configure();

    /**
     * This is the sink for second taint flow where File constructor is used as a required propagator.
     */
    Method sink2 = new MethodConfigurator("java.io.FileInputStream: java.io.FileInputStream FileInputStream(java.io.File)")
            .in().param(0).configure();

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        /**
         * This is the first taint flow that achieves path traversal attack.
         */
        TaintFlowQuery tf1 = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources)
                .notThrough(sanitizer)
                .to(sink1)
                .report("Path traversal - CWE22!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        TaintFlowQuery tf2 = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources)
                .notThrough(sanitizer)
                .through(requiredPropagator)
                .to(sink2)
                .report("Path traversal attack through File constructor - CWE22!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        //TaintFlowSet for path traversal attack that contains the above both taint flows that achieves path traversal attack.
        QueriesSet pathTraversalTFSet = new QueriesSet("pathTraversalTFSet")
                .addTaintFlowQuery(tf1)
                .addTaintFlowQuery(tf2);

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(pathTraversalTFSet);

        return myFluentTQLSpecs;
    }
}
