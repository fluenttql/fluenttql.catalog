package CatalogSpecifications.LDAPInjection.CWE90;

import CatalogSpecifications.Sinks.LdapSinks;
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
 * Internal FluentTQL specification for LDAP-Injection.
 */
public class LDAPInjectionSpec implements FluentTQLUserInterface {
    /*
     * encodeForLDAP is OWASP sanitizer that encodes the string to avoid LDAP-injection.
     */
    Method sanitizer = new MethodConfigurator("org.owasp.esapi.Encoder: java.lang.String encodeForLDAP(java.lang.String)")
            .in().param(0)
            .out().returnValue().configure();

    /**
     * Returns the Internal FluentTQL specification
     *
     * @return Internal FluentTQL specification
     */
    public List<FluentTQLSpecification> getFluentTQLSpecification() {
        TaintFlowQuery ldapInjectionSpecification = new TaintFlowQueryBuilder()
                .from(ServletSources.servletSources).notThrough(sanitizer)
                .to(LdapSinks.sinksLdapinjection)
                .and()
                .from(ServletSources.servletSources).notThrough(sanitizer)
                .to(LdapSinks.sinksLdapinjection)
                .report("LDAP-Injection CWE-90!")
                .at(LOCATION.SOURCEANDSINK)
                .build();

        List<FluentTQLSpecification> myFluentTQLSpecs = new ArrayList<FluentTQLSpecification>();
        myFluentTQLSpecs.add(ldapInjectionSpecification);

        return myFluentTQLSpecs;
    }
}
