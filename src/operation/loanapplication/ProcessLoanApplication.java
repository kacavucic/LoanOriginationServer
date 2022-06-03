package operation.loanapplication;

import domain.GenericEntity;
import domain.LoanApplication;
import operation.AbstractGenericOperation;

public class ProcessLoanApplication extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof LoanApplication)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((GenericEntity) param);

    }
}
