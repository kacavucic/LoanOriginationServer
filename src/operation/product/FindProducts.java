package operation.product;

import domain.DocumentTemplate;
import domain.GenericEntity;
import java.util.ArrayList;
import java.util.List;

import domain.Product;
import operation.AbstractGenericOperation;

public class FindProducts extends AbstractGenericOperation {

    private List<GenericEntity> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Product)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getBySpecificCondition((GenericEntity) param);

        for (GenericEntity entityProduct : list) {
            Product product = (Product) entityProduct;
            DocumentTemplate documentTemplate = new DocumentTemplate();
            documentTemplate.setProduct(product);

            List<GenericEntity> entityDocumentTemplates = repository.getByCondition((GenericEntity) documentTemplate);
            List<DocumentTemplate> documentTemplates = new ArrayList<>();
            for (GenericEntity entityDocumentTemplate : entityDocumentTemplates) {
                DocumentTemplate dt = (DocumentTemplate) entityDocumentTemplate;
                dt.setProduct(product);
                documentTemplates.add(dt);
            }
            product.setDocumentTemplates(documentTemplates);
        }
    }

    public List<GenericEntity> getList() {
        return list;
    }

}
