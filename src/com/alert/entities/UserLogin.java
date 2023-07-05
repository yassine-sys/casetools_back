package com.alert.entities;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userlogin", schema = "casemanagement")
public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -706130025067732026L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "u_id")
	private Integer id;

	private String name;
	private String login;
	private String mail;
	private String password;
	private String matricule;
	private String etat;
	private Timestamp createdDate;
	private String createdBy;
	private boolean domaineControl;
	private boolean revunueStream;
	private boolean HistoriqueAlert;
	private boolean adminAdd;
	private boolean raRule;
	private boolean stat;
	private boolean assignedTo;
	private boolean reAssignedTo;
	private boolean closed;
	
	private String authToken; // Add a field to store the authentication token
    private Date tokenExpiration;
    
    
    
    public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(Date tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}

	public void generateAuthToken() {
        // Generate a unique token
        String token = generateUniqueToken();

        // Set the token in the user object
        setAuthToken(token);

        // Set the token expiration date (e.g., 1 hour from now)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date expirationDate =  calendar.getTime();
        setTokenExpiration(expirationDate);
    }

    // Validate the token
    public boolean validateAuthToken() {
        // Retrieve the token from the user object
        String token = getAuthToken();

        // Check if the token exists and is valid
        return isTokenValid(token);
    }

    // Generate a unique token
    private String generateUniqueToken() {
        // Generate a random token using a secure cryptographic algorithm (e.g., UUID or JWT)
        String token = UUID.randomUUID().toString();

        // Modify the token if necessary (e.g., remove dashes or encode it)

        return token;
    }

    // Check if the token is valid
    private boolean isTokenValid(String token) {
        // Check if the token exists and is not expired
        if (token == null || token.isEmpty() || getTokenExpiration() == null) {
            return false;
        }

        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate =  currentCalendar.getTime();
        return currentDate.before(getTokenExpiration());
    }

	  
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDomaineControl() {
		return domaineControl;
	}

	public void setDomaineControl(boolean domaineControl) {
		this.domaineControl = domaineControl;
	}

	public boolean isRevunueStream() {
		return revunueStream;
	}

	public void setRevunueStream(boolean revunueStream) {
		this.revunueStream = revunueStream;
	}

	public boolean isHistoriqueAlert() {
		return HistoriqueAlert;
	}

	public void setHistoriqueAlert(boolean historiqueAlert) {
		HistoriqueAlert = historiqueAlert;
	}

	public boolean isAdminAdd() {
		return adminAdd;
	}

	public void setAdminAdd(boolean adminAdd) {
		this.adminAdd = adminAdd;
	}

	public boolean isRaRule() {
		return raRule;
	}

	public void setRaRule(boolean raRule) {
		this.raRule = raRule;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public boolean isAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(boolean assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isReAssignedTo() {
		return reAssignedTo;
	}

	public void setReAssignedTo(boolean reAssignedTo) {
		this.reAssignedTo = reAssignedTo;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}
