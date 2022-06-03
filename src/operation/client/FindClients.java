package operation.client;

import domain.Client;
import domain.Employed;
import domain.GenericEntity;
import domain.Student;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

public class FindClients extends AbstractGenericOperation {

    private List<GenericEntity> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Client)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<GenericEntity> helpList = repository.getBySpecificCondition((GenericEntity) param);
        list = new ArrayList<>();
        for (GenericEntity entityClient : helpList) {
            Client client = (Client) entityClient;

            switch (client.getType()) {
                case STUDENT:
                    Client student = new Student();
                    student.copyClient(client);
                    
                    GenericEntity entityStudent = repository.getByID((GenericEntity) student);
                    
                    Client studentToReturn = (Student) entityStudent;
                    studentToReturn.copyClient(client);
                    
                    list.add(studentToReturn);
                    break;
                case EMPLOYED:
                    Client employed = new Employed();
                    employed.copyClient(client);
                    
                    GenericEntity entityEmployed = repository.getByID((GenericEntity) employed);
                    
                    Client employedToReturn = (Employed) entityEmployed;
                    employedToReturn.copyClient(client);
                    
                    list.add(employedToReturn);
                    break;
                default:
                    System.out.println("Not student, nor employed");
            }
        }

    }

    public List<GenericEntity> getList() {
        return list;
    }
}
