package operation.product;

import domain.DocumentTemplate;
import domain.GenericEntity;
import domain.Product;
import operation.AbstractGenericOperation;

public class EditProduct extends AbstractGenericOperation {
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Product)) {
            throw new Exception("Object is not valid!");
        }
    }
    
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((GenericEntity) param);

        Product product = (Product) param;
        
        for (DocumentTemplate dt : product.getDocumentTemplates()) {
            repository.edit((GenericEntity) dt);
        }
        
    }
    
}
