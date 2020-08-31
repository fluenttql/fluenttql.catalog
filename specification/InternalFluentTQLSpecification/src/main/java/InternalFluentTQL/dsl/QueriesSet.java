package InternalFluentTQL.dsl;

import InternalFluentTQL.fluentInterface.Query.TaintFlowQuery;

import java.util.ArrayList;
import java.util.List;

public class QueriesSet extends FluentTQLSpecificationImpl {
    private String categoryName;
    private List<TaintFlowQuery> taintFlowQueries = new ArrayList<TaintFlowQuery>();

    public QueriesSet(String categoryName) {
        this.categoryName = categoryName;
    }

    public QueriesSet addTaintFlowQuery(TaintFlowQuery taintFlowQuery) {
        taintFlowQueries.add(taintFlowQuery);
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<TaintFlowQuery> getTaintFlowQueries() {
        return taintFlowQueries;
    }
}
