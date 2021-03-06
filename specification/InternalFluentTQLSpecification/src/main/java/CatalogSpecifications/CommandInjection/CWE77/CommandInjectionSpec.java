package CatalogSpecifications.CommandInjection.CWE77;

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
 * Internal FluentTQL specification for Command-Injection.
 */
public class CommandInjectionSpec implements FluentTQLUserInterface {

    /*
     * encodeForOS is OWASP sanitizer that encodes the string to avoid command injection. This method
     * has to be called before the requiredPropagator to avoid the security vulnerability.
     */
    Method sanitizer = new MethodConfigurator("org.owasp.esapi.Encoder: java.lang.String encodeForOS(org.owasp.esapi.codecs.Codec, java.lang.String)")
            .in().param(1)
            .out().returnValue().configure();

    /*
     * ProcessBuilder constructor should be called to run the command, but first arguments should be sanitized to
     * avoid the security vulnerability.
     */
    Method requiredPropagator = new MethodConfigurator("java.lang.ProcessBuilder: java.lang.ProcessBuilder ProcessBuilder(java.lang.String[])")
            .in().param(0)
            .out().thisObject().configure();

    /*
     * Sink.
     */
    Method sink = new MethodConfigurator("java.lang.ProcessBuilder: java.lang.Process start()")
            .in().thisObject().configure();

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        TaintFlowQuery commandInjectionSpecification = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources)
                .notThrough(sanitizer)
                .through(requiredPropagator)
                .to(sink)
                .report("Command Injection - CWE77!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(commandInjectionSpecification);

        return myFluentTQLSpecs;
    }
}
