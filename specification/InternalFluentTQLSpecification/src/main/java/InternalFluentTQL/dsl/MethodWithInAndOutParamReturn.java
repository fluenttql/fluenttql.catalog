package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.MethodPackage.Method;

public class MethodWithInAndOutParamReturn {
    private final MethodImpl method;
    private final OutputDeclarationImpl outputDeclaration;

    public MethodWithInAndOutParamReturn(OutputDeclarationImpl outputDeclaration, MethodImpl method) {
        this.method = method;
        this.outputDeclaration = outputDeclaration;
        method.setOutputDeclaration(this.outputDeclaration);
    }

    public MethodWithInAndOutThisObjReturnParam thisObject() {
        outputDeclaration.addOutput(new ThisObjectImpl());
        return new MethodWithInAndOutThisObjReturnParam(outputDeclaration, method);
    }

    public MethodWithInAndOutParamReturn param(int parameterID) {
        outputDeclaration.addOutput(new ParameterImpl(parameterID));
        return this;
    }

    public Method configure() {
        return method;
    }
}
