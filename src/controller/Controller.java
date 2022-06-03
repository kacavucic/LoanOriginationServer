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
        AbstractGenericOperation so = new Login();
        so.execute(entity);
        return ((Login) so).getObject();
    }

    // LOGOUT
    public boolean logout(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new Logout();
        so.execute(entity);
        return ((Logout) so).getOK();
    }

    // SAVE PRODUCT
    public Long saveProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new SaveProduct();
        so.execute(entity);
        return ((SaveProduct) so).getIndex();
    }

    // GET ALL PRODUCTS
    public List<GenericEntity> getAllProducts() throws Exception {
        AbstractGenericOperation so = new GetAllProducts();
        so.execute(new Product());
        return ((GetAllProducts) so).getList();
    }

    // FIND PRODUCTS
    public List<GenericEntity> findProducts(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new FindProducts();
        so.execute(entity);
        return ((FindProducts) so).getList();
    }

    // EDIT PRODUCT
    public void editProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new EditProduct();
        so.execute(entity);
    }

    // DEACTIVATE PRODUCT
    public void deactivateProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new EditProduct();
        so.execute(entity);
    }

    // ACTIVATE PRODUCT
    public void activateProduct(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new EditProduct();
        so.execute(entity);
    }

    // REGISTER CLIENT
    public long registerClient(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new RegisterClient();
        so.execute(entity);
        return ((RegisterClient) so).getIndex();
    }

    // GET ALL CLIENTS
    public List<GenericEntity> getAllClients() throws Exception {
        AbstractGenericOperation so = new GetAllClients();
        so.execute(new Client());
        return ((GetAllClients) so).getList();
    }

    // FIND CLIENTS
    public List<GenericEntity> findClients(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new FindClients();
        so.execute(entity);
        return ((FindClients) so).getList();
    }

    // EDIT CLIENT
    public void editClient(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new EditClient();
        so.execute(entity);
    }

    // CREATE LOAN APPLICATION
    public Long createLoanApplication(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new CreateLoanApplication();
        so.execute(entity);
        return ((CreateLoanApplication) so).getIndex();
    }

    // GET ALL LOAN APPLICATIONS
    public List<GenericEntity> getAllLoanApplications() throws Exception {
        AbstractGenericOperation so = new GetAllLoanApplications();
        so.execute(new LoanApplication());
        return ((GetAllLoanApplications) so).getList();
    }

    // GET CREDIT BUREAU REPORT
    public GenericEntity getCreditBureauReport(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new GetCreditBureauReport();
        so.execute(entity);
        return ((GetCreditBureauReport) so).getObject();
    }

    // GET FRAUD REPORT
    public GenericEntity getFraudReport(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new GetFraudReport();
        so.execute(entity);
        return ((GetFraudReport) so).getObject();
    }

    // PROCESS LOAN APPLICATION
    public void processLoanApplication(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new ProcessLoanApplication();
        so.execute(entity);
    }

    // FIND LOAN APPLICATIONS
    public List<GenericEntity> findLoanApplications(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new FindLoanApplications();
        so.execute(entity);
        return ((FindLoanApplications) so).getList();
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
