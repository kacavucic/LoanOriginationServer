package operation.loanapplication;

import domain.Document;
import domain.GenericEntity;
import domain.LoanApplication;
import operation.AbstractGenericOperation;

public class CreateLoanApplication extends AbstractGenericOperation {

    long index = -1;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof LoanApplication)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        LoanApplication loanApplication = (LoanApplication) param;
        index = repository.add((GenericEntity) loanApplication);

        loanApplication.setId(index);

        long documentID = 1;
        for (Document document : loanApplication.getDocuments()) {
                document.setId(documentID++);
                repository.addWithBlob((GenericEntity) document);
        }
    }

    public Long getIndex() {
        return index;
    }

}
