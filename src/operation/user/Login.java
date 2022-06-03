package operation.user;

import domain.GenericEntity;
import domain.User;
import operation.AbstractGenericOperation;

public class Login extends AbstractGenericOperation {

    GenericEntity object = null;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof User)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        object = repository.getByID((GenericEntity) param);
    }

    public GenericEntity getObject() {
        return object;
    }

}
