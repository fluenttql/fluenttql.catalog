package InternalFluentTQL.dsl;

public class MethodWithInAndOut {
    private final MethodImpl method;
    private final OutputDeclarationImpl outputDeclaration;

    public MethodWithInAndOut(OutputDeclarationImpl outputDeclaration, MethodImpl method) {
        this.method = method;
        this.outputDeclaration = outputDeclaration;
    }

    public MethodWithInAndOutParam param(int parameterID) {
        outputDeclaration.addOutput(new ParameterImpl(parameterID));
        return new MethodWithInAndOutParam(outputDeclaration, method);
    }

    public MethodWithInAndOutReturn returnValue() {
        outputDeclaration.addOutput(new ReturnImpl());
        return new MethodWithInAndOutReturn(outputDeclaration, method);
    }

    public MethodWithInAndOutThisObj thisObject() {
        outputDeclaration.addOutput(new ThisObjectImpl());
        return new MethodWithInAndOutThisObj(outputDeclaration, method);
    }
}
