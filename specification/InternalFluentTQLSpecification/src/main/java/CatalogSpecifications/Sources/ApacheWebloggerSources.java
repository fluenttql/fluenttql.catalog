package CatalogSpecifications.Sources;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Source definition for Apache web-logger
 */
public class ApacheWebloggerSources {
    private static final Method source1 = new MethodConfigurator("org.apache.roller.weblogger.pojos.Weblog: java.lang.String getName()")
            .out().returnValue().configure();

    private static final Method source2 = new MethodConfigurator("org.apache.roller.weblogger.pojos.Weblog: java.lang.String getTagline()")
            .out().returnValue().configure();

    private static final Method source3 = new MethodConfigurator("org.apache.roller.weblogger.pojos.Weblog: java.lang.String getEmailAddress()")
            .out().returnValue().configure();

    private static final Method source4 = new MethodConfigurator("org.apache.roller.weblogger.pojos.WeblogEntryComment: java.lang.String getContent()")
            .out().returnValue().configure();

    private static final Method source5 = new MethodConfigurator("org.apache.roller.weblogger.pojos.WeblogEntryComment: java.lang.String getId()")
            .out().returnValue().configure();

    private static final Method source6 = new MethodConfigurator("org.apache.roller.weblogger.config.WebloggerRuntimeConfig: java.lang.String getProperty(java.lang.String)")
            .out().returnValue().configure();

    public static MethodSet apacheWebloggerSources = new MethodSet("webgoatSources")
            .addMethod(source1)
            .addMethod(source2)
            .addMethod(source3)
            .addMethod(source4)
            .addMethod(source5)
            .addMethod(source6);
}
