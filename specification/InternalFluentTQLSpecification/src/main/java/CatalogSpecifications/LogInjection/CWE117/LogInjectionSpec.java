package CatalogSpecifications.LogInjection.CWE117;

import CatalogSpecifications.Sinks.LogInjectionSinks;
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
 * Internal FluentTQL specification for Log-Injection.
 */
public class LogInjectionSpec implements FluentTQLUserInterface {
    /**
     * encodeForURL is a OWASP sanitizer that encodes the URL. This encodes all the new line and carriage return,
     * therefore Log-Injection will be avoided.
     */
    Method sanitizer = new MethodConfigurator("org.owasp.esapi.Encoder: java.lang.String encodeForURL(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    /**
     * decodeFromURL is a OWASP de-sanitizer that decodes the URL. This decodes all the new line and carriage return,
     * therefore this method must be avoided before calling Log-Injection sinks.
     */
    Method deSanitizer = new MethodConfigurator("org.owasp.esapi.Encoder: java.lang.String decodeFromURL(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        TaintFlowQuery logInjectionSpec1 = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources)
                .notThrough(sanitizer)
                .through(deSanitizer)
                .to(LogInjectionSinks.logInjectionSinks)
                .report("Log-Injection CWE-117!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        TaintFlowQuery logInjectionSpec2 = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources)
                .notThrough(sanitizer)
                .to(LogInjectionSinks.logInjectionSinks)
                .report("Log-Injection CWE-117!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(logInjectionSpec1);
        myFluentTQLSpecs.add(logInjectionSpec2);

        return myFluentTQLSpecs;
    }
}
