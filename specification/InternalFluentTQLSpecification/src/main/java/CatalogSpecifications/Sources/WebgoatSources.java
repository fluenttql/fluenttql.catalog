package CatalogSpecifications.Sources;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Source definition for Webgoat
 */
public class WebgoatSources {
    private static final Method source1 = new MethodConfigurator("org.owasp.webgoat.session.ParameterParser: java.lang.String getRawParameter(java.lang.String, java.lang.String)")
            .out().returnValue().configure();

    private static final Method source2 = new MethodConfigurator("org.owasp.webgoat.session.ParameterParser: java.lang.String getStringParameter(java.lang.String, java.lang.String)")
            .out().returnValue().configure();

    private static final Method source3 = new MethodConfigurator("org.owasp.webgoat.session.ParameterParser: int getIntParameter(java.lang.String)")
            .out().returnValue().configure();

    private static final Method source4 = new MethodConfigurator("org.owasp.webgoat.session.ParameterParser: java.lang.String[] getParameterValues(java.lang.String)")
            .out().returnValue().configure();


    public static MethodSet webgoatSources = new MethodSet("webgoatSources")
            .addMethod(source1)
            .addMethod(source2)
            .addMethod(source3)
            .addMethod(source4);
}
