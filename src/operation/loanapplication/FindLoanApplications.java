package operation.loanapplication;

import domain.*;

import java.util.ArrayList;
import java.util.List;

import operation.AbstractGenericOperation;

public class FindLoanApplications extends AbstractGenericOperation {

   private List<GenericEntity> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof LoanApplication)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getBySpecificCondition((GenericEntity) param);

        for (GenericEntity entityLoanApplication : list) {
            LoanApplication loanApplication = (LoanApplication) entityLoanApplication;

            Document document = new Document();
            document.setLoanApplication(loanApplication);
            List<GenericEntity> entityDocuments = repository.getByCondition((GenericEntity) document);
            List<Document> documents = new ArrayList<>();
            for (GenericEntity entityDocument : entityDocuments) {
                Document d = (Document) entityDocument;
                d.setLoanApplication(loanApplication);
                documents.add(d);
            }
            loanApplication.setDocuments(documents);

            GenericEntity entityClient = repository.getByID((GenericEntity) loanApplication.getClient());
            loanApplication.setClient((Client) entityClient);

            GenericEntity entityProduct = repository.getByID((GenericEntity) loanApplication.getProduct());
            loanApplication.setProduct((Product) entityProduct);

            try {
                GenericEntity entityCreditBureauReport = repository.getByID((GenericEntity) loanApplication.getCreditBureauReport());
                loanApplication.setCreditBureauReport((CreditBureauReport) entityCreditBureauReport);
            } catch (Exception ex) {
                loanApplication.setCreditBureauReport(null);
            }

            try {
                GenericEntity entityFraudReport = repository.getByID((GenericEntity) loanApplication.getFraudReport());
                loanApplication.setFraudReport((FraudReport) entityFraudReport);
            } catch (Exception ex) {
                loanApplication.setFraudReport(null);
            }
        }
    }

    public List<GenericEntity> getList() {
        return list;
    }
}
