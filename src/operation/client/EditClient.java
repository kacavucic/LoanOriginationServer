package operation.client;

import domain.Client;
import domain.GenericEntity;
import operation.AbstractGenericOperation;

public class EditClient extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Client)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Client client = (Client) param; // client is already student/employed
        Client c = new Client(); // c is pure Client
        c.copyClient(client);
        repository.edit((GenericEntity) c);
        repository.edit((GenericEntity) client);
    }
}
