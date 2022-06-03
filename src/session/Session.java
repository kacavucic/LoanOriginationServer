package session;

import domain.GenericEntity;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private static Session instance;
    List<User> users;

    public Session() {
        users = new ArrayList<>();
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void addUser(GenericEntity entity) {
        if (entity instanceof User) {
            if (!users.contains(entity)) {
                users.add((User) entity);
            }
        }

    }

    public List<User> getAllUsers() {
        return users;
    }
    
    public int getNumberOfOnlineUsers(){
        return users.size();
    }

    public boolean removeUser(GenericEntity entity) {
        boolean ok = false;
        User user = (User) entity;

        if (users.contains(user)) {
            users.remove(user);
            ok = true;

        }
        return ok;
    }

    public void logoutAllUsers() {
        users = new ArrayList<>();
    }
}
