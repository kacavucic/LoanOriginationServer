package operation.user;

import domain.GenericEntity;
import domain.User;
import operation.AbstractGenericOperation;
import session.Session;

public class Logout extends AbstractGenericOperation {

    boolean ok = false;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof User)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        User user = (User) param;
        
        ok = Session.getInstance().removeUser((GenericEntity) user);
    }

    public boolean getOK() {
        return ok;
    }
}
