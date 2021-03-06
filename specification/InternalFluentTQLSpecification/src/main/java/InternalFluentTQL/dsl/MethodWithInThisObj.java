package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.MethodPackage.Method;

public class MethodWithInThisObj {
    private final MethodImpl method;
    private final InputDeclarationImpl inputDeclaration;

    public MethodWithInThisObj(InputDeclarationImpl inputDeclaration, MethodImpl method) {
        this.method = method;
        this.inputDeclaration = inputDeclaration;
    }

    public MethodWithInRemainingParam param(int parameterID) {
        inputDeclaration.addInput(new ParameterImpl(parameterID));
        return new MethodWithInRemainingParam(inputDeclaration, method);
    }

    public MethodWithInAndOut out() {
        return new MethodWithInAndOut(new OutputDeclarationImpl(), method);
    }

    public Method configure() {
        return method;
    }
}
