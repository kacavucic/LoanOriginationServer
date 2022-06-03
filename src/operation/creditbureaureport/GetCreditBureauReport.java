package operation.creditbureaureport;

import domain.CreditBureauReport;
import domain.GenericEntity;
import operation.AbstractGenericOperation;

public class GetCreditBureauReport extends AbstractGenericOperation {

    GenericEntity object = null;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof CreditBureauReport)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            object = repository.getByID((GenericEntity) param);
        } catch (Exception ex) {
            object = null;
        }
    }

    public GenericEntity getObject() {
        return object;
    }

}
