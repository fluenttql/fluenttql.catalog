package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.Query.TaintFlowQuery;

public class TaintFlowWithReportLocation {
    private final TaintFlowQueryImpl taintFlowQuery;

    public TaintFlowWithReportLocation(TaintFlowQuery taintFlowQuery) {
        this.taintFlowQuery = (TaintFlowQueryImpl) taintFlowQuery;
    }

    public TaintFlowQuery build() {
        return taintFlowQuery;
    }
}
