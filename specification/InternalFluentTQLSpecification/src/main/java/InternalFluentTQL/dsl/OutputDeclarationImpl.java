package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.InputOutput.Output;
import InternalFluentTQL.fluentInterface.InputOutput.OutputDeclaration;
import InternalFluentTQL.fluentInterface.InputOutput.ThisObject;

import java.util.ArrayList;
import java.util.List;

class OutputDeclarationImpl implements OutputDeclaration {
    private List<Output> outputs = new ArrayList<Output>();

    public List<Output> getOutputs() {
        return outputs;
    }

    public void addOutput(Output output) {
        if (output instanceof ThisObjectImpl) {
            for (Output itr : outputs) {
                if (itr instanceof ThisObject)
                    return;
            }

            outputs.add(new ThisObjectImpl());
        } else if (output instanceof ReturnImpl) {
            for (Output itr : outputs) {
                if (itr instanceof ReturnImpl)
                    return;
            }

            outputs.add(new ReturnImpl());
        } else {
            outputs.add(output);
        }
    }
}
