package operation;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.RepositoryDBGeneric;

public abstract class AbstractGenericOperation {

    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

    public final void execute(Object param) throws Exception {
        try {
            startTransaction();
            preconditions(param);
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    private void startTransaction() throws Exception {
        ((DBRepository) repository).connect();
    }

    private void commitTransaction() throws Exception {
        ((DBRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DBRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DBRepository) repository).disconnect();
    }

    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;
}
