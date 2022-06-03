package controller;

import constant.Constant;
import domain.Client;
import domain.GenericEntity;

import java.io.IOException;
import java.util.List;

import domain.LoanApplication;
import domain.Product;
import operation.AbstractGenericOperation;
import operation.fraudreport.GetFraudReport;
import operation.creditbureaureport.GetCreditBureauReport;
import operation.client.EditClient;
import operation.client.FindClients;
import operation.client.RegisterClient;
import operation.client.GetAllClients;
import operation.user.Logout;
import operation.user.Login;
import operation.loanapplication.CreateLoanApplication;
import operation.loanapplication.FindLoanApplications;
import operation.loanapplication.ProcessLoanApplication;
import operation.loanapplication.GetAllLoanApplications;
import operation.product.EditProduct;
import operation.product.FindProducts;
import operation.product.GetAllProducts;
import operation.product.SaveProduct;
import repository.Repository;
import repository.db.impl.RepositoryDBGeneric;
import server.Server;
import session.Session;
import util.PropertiesLoader;


public class Controller {

    private static Controller controller;
    private Server server;
    private Repository repository;

    private Controller() {
        repository = new RepositoryDBGeneric();
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    // LOGIN
    public GenericEntity login(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new Login();
        genericOperation.execute(entity);
        return ((Login) genericOperation).getObject();
    }

    // LOGOUT
    public boolean logout(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new Logout();
        genericOperation.execute(entity);
        return ((Logout) genericOperation).getOK();
    }

    // SAVE PRODUCT
    public Long saveProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new SaveProduct();
        genericOperation.execute(entity);
        return ((SaveProduct) genericOperation).getIndex();
    }

    // GET ALL PRODUCTS
    public List<GenericEntity> getAllProducts() throws Exception {
        AbstractGenericOperation genericOperation = new GetAllProducts();
        genericOperation.execute(new Product());
        return ((GetAllProducts) genericOperation).getList();
    }

    // FIND PRODUCTS
    public List<GenericEntity> findProducts(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new FindProducts();
        genericOperation.execute(entity);
        return ((FindProducts) genericOperation).getList();
    }

    // EDIT PRODUCT
    public void editProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new EditProduct();
        genericOperation.execute(entity);
    }

    // DEACTIVATE PRODUCT
    public void deactivateProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new EditProduct();
        genericOperation.execute(entity);
    }

    // ACTIVATE PRODUCT
    public void activateProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new EditProduct();
        genericOperation.execute(entity);
    }

    // REGISTER CLIENT
    public long registerClient(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new RegisterClient();
        genericOperation.execute(entity);
        return ((RegisterClient) genericOperation).getIndex();
    }

    // GET ALL CLIENTS
    public List<GenericEntity> getAllClients() throws Exception {
        AbstractGenericOperation genericOperation = new GetAllClients();
        genericOperation.execute(new Client());
        return ((GetAllClients) genericOperation).getList();
    }

    // FIND CLIENTS
    public List<GenericEntity> findClients(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new FindClients();
        genericOperation.execute(entity);
        return ((FindClients) genericOperation).getList();
    }

    // EDIT CLIENT
    public void editClient(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new EditClient();
        genericOperation.execute(entity);
    }

    // CREATE LOAN APPLICATION
    public Long createLoanApplication(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new CreateLoanApplication();
        genericOperation.execute(entity);
        return ((CreateLoanApplication) genericOperation).getIndex();
    }

    // GET ALL LOAN APPLICATIONS
    public List<GenericEntity> getAllLoanApplications() throws Exception {
        AbstractGenericOperation genericOperation = new GetAllLoanApplications();
        genericOperation.execute(new LoanApplication());
        return ((GetAllLoanApplications) genericOperation).getList();
    }

    // GET CREDIT BUREAU REPORT
    public GenericEntity getCreditBureauReport(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new GetCreditBureauReport();
        genericOperation.execute(entity);
        return ((GetCreditBureauReport) genericOperation).getObject();
    }

    // GET FRAUD REPORT
    public GenericEntity getFraudReport(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new GetFraudReport();
        genericOperation.execute(entity);
        return ((GetFraudReport) genericOperation).getObject();
    }

    // PROCESS LOAN APPLICATION
    public void processLoanApplication(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new ProcessLoanApplication();
        genericOperation.execute(entity);
    }

    // FIND LOAN APPLICATIONS
    public List<GenericEntity> findLoanApplications(GenericEntity entity) throws Exception {
        AbstractGenericOperation genericOperation = new FindLoanApplications();
        genericOperation.execute(entity);
        return ((FindLoanApplications) genericOperation).getList();
    }

    public void startServer() throws IOException {
        server = new Server(Integer.parseInt(PropertiesLoader.getInstance().getProperty(Constant.SERVER_PORT)));
        server.start();
    }

    public void stopServer() throws IOException {
        server.stopServer();
        Session.getInstance().logoutAllUsers();
    }


}
