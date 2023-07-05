package com.alert.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.alert.entities.ParametresRarule;
import com.alert.entities.UserLogin;
import com.alerte.remote.UserRemote;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Stateless
public class UserService implements UserRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<UserLogin> getallusers() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from UserLogin u").getResultList();
	}

	@Override
	public void addUser(UserLogin userlogin) {
		// TODO Auto-generated method stub
		System.out.println("adding");
		 UserLogin mergedUser = em.merge(userlogin);
		    em.persist(mergedUser);
	}

	@Override
	public void updateUser(UserLogin userlogin) {
		// TODO Auto-generated method stub
		em.merge(userlogin);
	}

	@Override
	public void deleteUser(UserLogin userlogin) {
		// TODO Auto-generated method stub
		em.remove(em.merge(userlogin));
	}
	


	@Override
	public UserLogin getconnecte(String login, String passwd) {
	    TypedQuery<UserLogin> query = em.createQuery(
	            "SELECT u FROM UserLogin u WHERE u.login = :login AND u.password = :passwd AND u.etat = 'A'",
	            UserLogin.class);
	    query.setParameter("login", login);
	    query.setParameter("passwd", passwd);

	    try {
	        return query.getSingleResult();
	    } catch (NoResultException | NonUniqueResultException e) {
	        // Handle exception or return null/throw custom exception based on your requirements
	        return null;
	    }
	}

	@Override
	public void removeUser(Integer id) {
		UserLogin user=em.find(UserLogin.class, id);
		if(user!=null) {
			em.remove(user);
		}
		
	}

	@Override
	public void editUser(Integer id, UserLogin userlogin) {
		// TODO Auto-generated method stub
		UserLogin user=em.find(UserLogin.class, id);
		if (user!=null) {
			user.setName(userlogin.getName());
			user.setLogin(userlogin.getLogin());
			user.setMail(userlogin.getMail());
			user.setPassword(userlogin.getPassword());
			user.setMatricule(userlogin.getMatricule());
			user.setDomaineControl(userlogin.isDomaineControl());
			user.setRevunueStream(userlogin.isRevunueStream());

			user.setAdminAdd(userlogin.isAdminAdd());
			user.setRaRule(userlogin.isRaRule());
			user.setStat(userlogin.isStat());
			user.setAssignedTo(userlogin.isAssignedTo());
			user.setReAssignedTo(userlogin.isReAssignedTo());
			user.setClosed(userlogin.isClosed());
			
			
			
			em.merge(user);
			
		}
		
		
	}

	@Override
	public UserLogin authenticateUser(String login, String password) {
		try {
            // Hash the password
            MessageDigest mdsha1 = MessageDigest.getInstance("SHA-1");
            mdsha1.update(password.getBytes());
            byte[] digestsha1 = mdsha1.digest();
            String myHashsha1 = javax.xml.bind.DatatypeConverter.printHexBinary(digestsha1).toUpperCase();

            // Query the database for the user with the provided login and hashed password
            TypedQuery<UserLogin> query = em.createQuery("SELECT u FROM UserLogin u WHERE u.login = :login AND u.password = :password", UserLogin.class);
            query.setParameter("login", login);
            query.setParameter("password", myHashsha1);

            List<UserLogin> users = query.getResultList();
            if (!users.isEmpty()) {
                // Return the authenticated user
                return users.get(0);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        // Return null if authentication fails
        return null;
    
	}

}
