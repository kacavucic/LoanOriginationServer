package operation.product;

import domain.GenericEntity;
import domain.Product;
import operation.AbstractGenericOperation;

// NOT IN USE
public class DeactivateProduct extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Product)) {
            throw new Exception("Object is not valid!");
        }
    }
    
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.deactivate((GenericEntity) param);
    }
    
}
