package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.MethodPackage.Method;

public class MethodWithOutParamReturn {
    private final MethodImpl method;
    private final OutputDeclarationImpl outputDeclaration;

    public MethodWithOutParamReturn(OutputDeclarationImpl outputDeclaration, MethodImpl method) {
        this.method = method;
        this.outputDeclaration = outputDeclaration;
        method.setOutputDeclaration(this.outputDeclaration);
    }

    public MethodWithOutThisObjReturnParam thisObject() {
        outputDeclaration.addOutput(new ThisObjectImpl());
        return new MethodWithOutThisObjReturnParam(outputDeclaration, method);
    }

    public MethodWithOutParamReturn param(int parameterID) {
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
