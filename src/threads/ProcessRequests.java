package threads;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.GenericEntity;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.User;
import session.Session;
import view.coordinator.MainCoordinator;

public class ProcessRequests extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;
    User user;

    public ProcessRequests(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!isInterrupted() && !socket.isClosed()) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        ///////////////////
                        case LOGIN:
                             try {
                            GenericEntity object = Controller.getInstance().login((GenericEntity) request.getArgument());

                            if (Session.getInstance().getAllUsers().contains(object)) {
                                response.setException(new Exception("User "
                                        + object + " is already logged in!\n"));
                            } else {
                                response.setResult(object);
                                user = (User) object;
                                Session.getInstance().addUser(user);
                                MainCoordinator.getInstance().getFrmMainController().refreshTbl();

                            }
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case LOGOUT:
                            try {
                            boolean ok = Controller.getInstance().logout((GenericEntity) request.getArgument());
                            response.setResult(ok);
                            MainCoordinator.getInstance().getFrmMainController().refreshTbl();

                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case SAVE_PRODUCT:
                            try {
                            long index = Controller.getInstance().saveProduct((GenericEntity) request.getArgument());
                            response.setResult(index);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case GET_ALL_PRODUCTS:
                            try {
                            List<GenericEntity> products = Controller.getInstance().getAllProducts();
                            response.setResult(products);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case FIND_PRODUCTS:
                            try {
                            List<GenericEntity> products = Controller.getInstance().findProducts((GenericEntity) request.getArgument());
                            response.setResult(products);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case EDIT_PRODUCT:
                            try {
                            Controller.getInstance().editProduct((GenericEntity) request.getArgument());
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case DEACTIVATE_PRODUCT:
                            try {
                            Controller.getInstance().deactivateProduct((GenericEntity) request.getArgument());
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case ACTIVATE_PRODUCT:
                            try {
                            Controller.getInstance().activateProduct((GenericEntity) request.getArgument());
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case REGISTER_CLIENT:
                            try {
                            long index = Controller.getInstance().registerClient((GenericEntity) request.getArgument());
                            response.setResult(index);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case GET_ALL_CLIENTS:
                           try {
                            List<GenericEntity> lecturers = Controller.getInstance().getAllClients();
                            response.setResult(lecturers);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case FIND_CLIENTS:
                            try {
                            List<GenericEntity> clients = Controller.getInstance().findClients((GenericEntity) request.getArgument());
                            response.setResult(clients);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case EDIT_CLIENT:
                             try {
                            Controller.getInstance().editClient((GenericEntity) request.getArgument());
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case CREATE_LOAN_APPLICATION:
                            try {
                            long index = Controller.getInstance().createLoanApplication((GenericEntity) request.getArgument());
                            response.setResult(index);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case GET_ALL_LOAN_APPLICATIONS:
                           try {
                            List<GenericEntity> loanApplications = Controller.getInstance().getAllLoanApplications();
                            response.setResult(loanApplications);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case GET_CREDIT_BUREAU_REPORT:
                            try {
                            GenericEntity object = Controller.getInstance().getCreditBureauReport((GenericEntity) request.getArgument());
                            response.setResult(object);

                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case GET_FRAUD_REPORT:
                            try {
                            GenericEntity object = Controller.getInstance().getFraudReport((GenericEntity) request.getArgument());
                            response.setResult(object);

                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case PROCESS_LOAN_APPLICATION:
                            try {
                            Controller.getInstance().processLoanApplication((GenericEntity) request.getArgument());
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                        ///////////////////
                        case FIND_LOAN_APPLICATIONS:
                            try {
                            List<GenericEntity> loanApplications = Controller.getInstance().findLoanApplications((GenericEntity) request.getArgument());
                            response.setResult(loanApplications);
                        } catch (Exception ex) {
                            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                            response.setException(ex);
                        }
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (SocketException ex) {
                Logger.getLogger(ProcessRequests.class.getName()).log(Level.WARNING, "Client closed the connection forcefully. Logging out the user.", ex);
                try {
                    Controller.getInstance().logout(user);
                    MainCoordinator.getInstance().getFrmMainController().refreshTbl();
                    socket.close();
                    Logger.getLogger(ProcessRequests.class.getName()).log(Level.INFO, "User logged out successfully", ex);
                } catch (Exception e) {
                    Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, "Error while logging out the user", ex);
                }
            } catch (Exception ex) {
                Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
