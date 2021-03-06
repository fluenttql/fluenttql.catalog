package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.MethodPackage.Method;
import InternalFluentTQL.fluentInterface.TaintFlowPackage.FlowParticipant;

import java.util.ArrayList;
import java.util.List;

public class MethodSet implements FlowParticipant {
    private final String methodSetName;
    private final List<Method> methods = new ArrayList<Method>();

    public MethodSet(String methodSetName) {
        this.methodSetName = methodSetName;
    }

    public MethodSet addMethod(Method method) {
        methods.add(method);
        return this;
    }

    public String getName() {
        return methodSetName;
    }

    public List<Method> getMethods() {
        return methods;
    }
}
