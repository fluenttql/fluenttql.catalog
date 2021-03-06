package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.MethodPackage.Method;

public class MethodWithOutThisObjReturnParam {
    private final MethodImpl method;
    private final OutputDeclarationImpl outputDeclaration;

    public MethodWithOutThisObjReturnParam(OutputDeclarationImpl outputDeclaration, MethodImpl method) {
        this.method = method;
        this.outputDeclaration = outputDeclaration;
        method.setOutputDeclaration(this.outputDeclaration);
    }

    public MethodWithOutThisObjReturnParam param(int parameterID) {
        outputDeclaration.addOutput(new ParameterImpl(parameterID));
        return this;
    }

    public Method configure() {
        return method;
    }

    public MethodWithIn in() {
        return new MethodWithIn(new InputDeclarationImpl(), method);
    }
}
