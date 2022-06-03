package operation.product;

import domain.DocumentTemplate;
import domain.GenericEntity;
import domain.Product;
import operation.AbstractGenericOperation;

public class SaveProduct extends AbstractGenericOperation {
    
    long index = -1;
    
    public long getIndex() {
        return index;
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Product)) {
            throw new Exception("Object is not valid!");
        }
    }
    
    @Override
    protected void executeOperation(Object param) throws Exception {
        Product product = (Product) param;
        index = repository.add((GenericEntity) product);
        
        product.setId(index);
        
        long documentTemplateID = 1;
        for (DocumentTemplate documentTemplate : product.getDocumentTemplates()) {
            documentTemplate.setId(documentTemplateID++);
            repository.add((GenericEntity) documentTemplate);
        }
    }
    
}
