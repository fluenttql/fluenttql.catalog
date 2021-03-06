package CatalogSpecifications.Sinks;

import InternalFluentTQL.dsl.MethodConfigurator;
import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.MethodPackage.Method;

/**
 * Multiple Sinks definition for LDAP-Injection
 */
public class LdapSinks {
    // these are sink methods for XPath injection. The list is created based on: https://github.com/julianthome/joanaudit/blob/master/config/sinks.json
    private static final Method sink1 = new MethodConfigurator("javax.naming.directory.DirContext: javax.naming.NamingEnumeration search(java.lang.String, java.lang.String, javax.naming.directory.SearchControls)")
            .in().param(1).configure();

    private static final Method sink2 = new MethodConfigurator("javax.naming.directory.DirContext: javax.naming.NamingEnumeration search(java.lang.String, java.lang.String, java.lang.Object[], javax.naming.directory.SearchControls)")
            .in().param(1).configure();

    private static final Method sink3 = new MethodConfigurator("com.novell.ldap.LDAPConnection: void connect(java.lang.String, int)")
            .in().param(0).configure();

    private static final Method sink4 = new MethodConfigurator("com.novell.ldap.LDAPConnection: com.novell.ldap.LDAPSearchQueue search(java.lang.String, int, java.lang.String, java.lang.String[], boolean)")
            .in().param(2).configure();

    private static final Method sink5 = new MethodConfigurator("com.novell.ldap.LDAPConnection: com.novell.ldap.LDAPSearchQueue search(java.lang.String, int, java.lang.String, java.lang.String[], boolean, com.novell.ldap.LDAPSearchConstraints)")
            .in().param(2).configure();

    private static final Method sink6 = new MethodConfigurator("com.novell.ldap.LDAPConnection: com.novell.ldap.LDAPSearchQueue search(java.lang.String, int, java.lang.String, java.lang.String[], boolean, com.novell.ldap.LDAPSearchQueue, com.novell.ldap.LDAPSearchConstraints)")
            .in().param(2).configure();

    private static final Method sink7 = new MethodConfigurator("com.novell.ldap.LDAPConnection: com.novell.ldap.LDAPSearchQueue search(java.lang.String, int, java.lang.String, java.lang.String[], boolean, com.novell.ldap.LDAPSearchQueue)")
            .in().param(2).configure();

    private static final Method sink8 = new MethodConfigurator("com.unboundid.ldap.sdk.LDAPInterface: com.unboundid.ldap.sdk.SearchResult search(java.lang.String, com.unboundid.ldap.sdk.SearchScope, java.lang.String)")
            .in().param(2).configure();


    public static MethodSet sinksLdapinjection = new MethodSet("sinksLdapinjection")
            .addMethod(sink1)
            .addMethod(sink2)
            .addMethod(sink3)
            .addMethod(sink4)
            .addMethod(sink5)
            .addMethod(sink6)
            .addMethod(sink7)
            .addMethod(sink8);
}
