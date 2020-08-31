package InternalFluentTQL.fluentInterface.MethodPackage;

import InternalFluentTQL.dsl.MethodSet;
import InternalFluentTQL.fluentInterface.InputOutput.InputDeclaration;
import InternalFluentTQL.fluentInterface.InputOutput.OutputDeclaration;
import InternalFluentTQL.fluentInterface.TaintFlowPackage.FlowParticipant;

/**
 * Interface for Method
 */
public interface Method extends FlowParticipant {

    /**
     * Returns the Method signature
     *
     * @return Method signature
     */
    String getSignature();

    /**
     * Returns the MethodSet
     *
     * @return MethodSet
     */
    MethodSet getMethodSet();

    /**
     * Returns the InputDeclaration
     *
     * @return InputDeclaration
     */
    InputDeclaration getInputDeclaration();

    /**
     * Returns the OutputDeclaration
     *
     * @return OutputDeclaration
     */
    OutputDeclaration getOutputDeclaration();
}
