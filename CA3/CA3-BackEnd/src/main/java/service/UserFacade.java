package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entity.User;
import entity.Role;
import exceptions.AuthenticationException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    //Default EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ca3pu");
    private static final UserFacade instance = new UserFacade();

    private UserFacade() {
    }

    public static UserFacade getInstance() {
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public Boolean checkExist(String username) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
        } finally {
            em.close();
        }
        return user == null;
    }
    
    public Boolean register(String username, String password) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()));
            user.addRole(em.find(Role.class, "user"));
            em.getTransaction().begin();
            em.persist(user);
        } finally {
            em.close();
        }
        return true;
    }
    
    public Boolean register(String username, String password, String role) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()));
            user.addRole(em.find(Role.class, role));
            em.getTransaction().begin();
            em.persist(user);
        } finally {
            em.close();
        }
        return true;
    }

}
