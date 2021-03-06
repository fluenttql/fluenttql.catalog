package CatalogSpecifications.Sinks;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Sinks definition for XML.
 */
public class XMLSinks {
    // these are sink methods for XPath injection. The list is created based on: https://github.com/julianthome/joanaudit/blob/master/config/sinks.json

    private static final Method sink1 = new MethodConfigurator("org.xml.sax.XMLReader: void parse(org.xml.sax.InputSource)")
            .in().param(0).configure();

    private static final Method sink2 = new MethodConfigurator("org.apache.http.client.methods.HttpPost: void setEntity(org.apache.http.HttpEntity)")
            .in().param(0).configure();

    private static final Method sink3 = new MethodConfigurator("org.apache.http.client.methods.HttpPost: void setHeader(java.lang.String, java.lang.String)")
            .in().param(1).configure(); // first argument is key, second argument is value


    private static final Method sink4 = new MethodConfigurator("javax.xml.parsers.DocumentBuilder: org.w3c.dom.Document parse(org.xml.sax.InputSource)")
            .in().param(0).configure();

    public static MethodSet sinksXMLinjection = new MethodSet("sinksXMLinjection")
            .addMethod(sink1)
            .addMethod(sink2)
            .addMethod(sink3)
            .addMethod(sink4);
}
