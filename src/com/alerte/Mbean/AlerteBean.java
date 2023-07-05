package com.alerte.Mbean;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.DatatypeConverter;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.PrimeFaces;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.alert.entities.*;
import com.alert.services.UserService;
import com.alerte.remote.AlerteInterface;
import com.alerte.remote.UserRemote;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/case")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(MediaType.APPLICATION_JSON)
@ManagedBean(name = "alerteBean")
public class AlerteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5517382624956917473L;

	@EJB
	AlerteInterface alerteInterface;

	private RevenueStream rev;
	private DomainControl dom;
	private DomainControl domselected;
	private RevenueStream rev1;
	private DomainControl dom1;
	private Rarule rule;
	private Rarule rule1;
	private ReportRarule rep;
	private ReportRarule rep1;
	private ParametresRarule param;
	private ParametresRarule param1;
	private AlerteRa alert;
	private AlerteRa alert1;
	private HistoriqueAlerte hist;
	private HistoriqueAlerte hist1;
	private UserLogin user1 = new UserLogin();
	private UserLogin user2 = new UserLogin();
	private List<UserLogin> users = new ArrayList<>();
	private List<UserLogin> filtreduser = new ArrayList<>();
	private UserLogin user3 = new UserLogin();
	@EJB
	UserRemote userRemote;
	
	

	@GET
	@Path("/allrapports")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallapports() {
		List<RepRapport> raportList = alerteInterface.getAllrapports();

		// Convert the list to JSON
		String jsonData = convertListToJsonraports(raportList);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	private String convertListToJsonraports(List<RepRapport> raportList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(raportList);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/listrev")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallrev() {
		List<RevenueStream> revenueList = alerteInterface.getAll();

		// Convert the list to JSON
		String jsonData = convertListToJson(revenueList);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	private String convertListToJson(List<RevenueStream> revenueList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(revenueList);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/updaterev")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifier(String jsonData) {

		RevenueStream rev = convertJsonToObject(jsonData);
		alerteInterface.updateRevenueStream(rev);
		revenueList = alerteInterface.getAll();

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(String jsonData) {
		System.out.println(jsonData);
		RevenueStream rev = convertJsonToObject(jsonData);

		alerteInterface.addRevenueStream(rev);

		revenueList = alerteInterface.getAll();

	}

	@DELETE
	@Path("/deleterev")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStream(RevenueStream rev) {
		alerteInterface.deleteRevenueStream(rev);
		revenueList = alerteInterface.getAll();
		String jsonData = convertListToJson(revenueList);

		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteEntity(@PathParam("id") Long id) {
		alerteInterface.removeRevenue(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("/editrev/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRevenue(@PathParam("id") Long id, String jsonData) {
		RevenueStream rev = convertJsonToObject(jsonData);
		alerteInterface.updateRevenue(id, rev);
		revenueList = alerteInterface.getAll();
		return Response.status(Response.Status.OK).build();

	}

	private RevenueStream convertJsonToObject(String jsonData) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(jsonData, RevenueStream.class);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/adddom")
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouterDom(String jsonData) {
		System.out.println(jsonData);
		DomainControl dom = convertJsonToObjectDomainControl(jsonData);

		alerteInterface.addDomain(dom);

		domainList = alerteInterface.getAllDomain();

	}

	@PUT
	@Path("/editdom/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDomainControl(@PathParam("id") Long id, String jsonData) {
		DomainControl dom = convertJsonToObjectDomainControl(jsonData);
		alerteInterface.updateDomainControl(id, dom);
		domainList = alerteInterface.getAllDomain();
		return Response.status(Response.Status.OK).build();

	}

//	@DELETE
//	@Path("/deletedom")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void deletedom(String jsonData) {
//		DomainControl dom = convertJsonToObjectDomainControl(jsonData);
//		alerteInterface.deleteDomain(dom);
//		domainList = alerteInterface.getAllDomain();
//	}

	@DELETE
	@Path("/deletedom/{id}")
	public Response deletedom(@PathParam("id") Long id) {
		alerteInterface.removeDomain(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST
	@Path("/updatedom")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifierdom(String jsonData) {

		DomainControl dom = convertJsonToObjectDomainControl(jsonData);
		alerteInterface.updateDomain(dom);
		domainList = alerteInterface.getAllDomain();

	}

	@GET
	@Path("/listdom")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getalldom() {
		List<DomainControl> domainList = alerteInterface.getAllDomain();

		// Convert the list to JSON
		String jsonData = convertListDomToJson(domainList);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	private String convertListDomToJson(List<DomainControl> domainList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(domainList);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	private DomainControl convertJsonToObjectDomainControl(String jsonData) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(jsonData, DomainControl.class);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

//	@DELETE
//	@Path("/deleterarule")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void deleterarule(String jsonData) {
//		Rarule rarule = convertJsonToObjectRarule(jsonData);
//		alerteInterface.deleteRarule(rarule);
//		rarules = alerteInterface.getAllRarules();
//	}

	@DELETE
	@Path("/deleterarule/{id}")
	public Response deleterarule(@PathParam("id") Long id) {
		alerteInterface.removeRarule(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("/editrarule/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updaterarule(@PathParam("id") Long id, String jsonData) {
		Rarule rule = convertJsonToObjectRarule(jsonData);
		alerteInterface.updateRarule(id, rule);
		rarules = alerteInterface.getAllRarules();
		return Response.status(Response.Status.OK).build();

	}

	@POST
	@Path("/addrarule")
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouterRarule(String jsonData/* , @Context HttpServletRequest request */) {
		// HttpSession session = request.getSession();
		// String username = session.getAttribute("username").toString();
		System.out.println(jsonData);
		Rarule rarule = convertJsonToObjectRarule(jsonData);
		// rarule.setNomUtilisateur(username);

		alerteInterface.addRarule(rarule);

		rarules = alerteInterface.getAllRarules();

	}

	@GET
	@Path("/listrarule")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallrarule() {
		List<Rarule> raruleList = alerteInterface.getAllRarules();

		// Convert the list to JSON
		String jsonData = convertListToJsonRaRule(raruleList);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	private String convertListToJsonRaRule(List<Rarule> raruleList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(raruleList);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	private Rarule convertJsonToObjectRarule(String jsonData) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(jsonData, Rarule.class);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

//	public void ajouterRarule() {
//		HttpSession session = Util.getSession();
//		rule.setNomUtilisateur(session.getAttribute("username").toString());
//		alerteInterface.addRarule(rule);
//		rarules = alerteInterface.getAllRarules();
//	}
//
//	public void deleteRarule() {
//		alerteInterface.deleteRarule(rule1);
//		rarules = alerteInterface.getAllRarules();
//	}
//
//	public void modifierRarule() {
//
//		alerteInterface.updateRarule(rule1);
//		rarules = alerteInterface.getAllRarules();
//
//	}

//	@DELETE
//	@Path("/deleteparam")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void deleteparam(String jsonData) {
//		ParametresRarule param = convertJsonToObjectParam(jsonData);
//		alerteInterface.deleteParametre(param);
//		params = alerteInterface.getAllParametres();
//	}

	@DELETE
	@Path("/deleteparam/{id}")
	public Response deleteparam(@PathParam("id") Long id) {
		alerteInterface.removeParametre(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST
	@Path("/updateparam")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateparam(String jsonData) {

		ParametresRarule param = convertJsonToObjectParam(jsonData);
		alerteInterface.updatePrametre(param);
		params = alerteInterface.getAllParametres();

	}

	@PUT
	@Path("/editparam/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editparam(@PathParam("id") Long id, String jsonData) {
		ParametresRarule param = convertJsonToObjectParam(jsonData);
		alerteInterface.updateParametre(id, param);
		params = alerteInterface.getAllParametres();
		return Response.status(Response.Status.OK).build();

	}

	@POST
	@Path("/addparam")
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouterParam(String jsonData) {
		System.out.println(jsonData);
		ParametresRarule param = convertJsonToObjectParam(jsonData);

		alerteInterface.addParametre(param);

		params = alerteInterface.getAllParametres();

	}

	@GET
	@Path("/listparam")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallparam() {
		List<ParametresRarule> ParamList = alerteInterface.getAllParametres();

		// Convert the list to JSON
		String jsonData = convertListToJsonParam(ParamList);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	@GET
	@Path("/listalertes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getalertes() {
		alertes = alerteInterface.getAllAlertes();
		// Convert the list to JSON
		String jsonData = convertListToJsonParamlistalert(alertes);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}
	
	@PUT
	@Path("/closealert/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response closealert(@PathParam("id") Long id, String jsonData) {
		AlerteRa alerts = convertJsonToObjectAlert(jsonData);
		alerteInterface.closeAlert(id, alerts);
		alertes = alerteInterface.getAllAlertes();
		return Response.status(Response.Status.OK).build();

	}
	
	

	private String convertListToJsonParamlistalert(List<AlerteRa> alerts) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(alerts);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}
		private AlerteRa convertJsonToObjectAlert(String jsonData) {

			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.readValue(jsonData, AlerteRa.class);
			} catch (Exception e) {
				// Handle the exception appropriately
				e.printStackTrace();
				return null;
			}
		}
	

	private String convertListToJsonParam(List<ParametresRarule> ParamList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(ParamList);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	private ParametresRarule convertJsonToObjectParam(String jsonData) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(jsonData, ParametresRarule.class);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

//	public void ajouterParam() {
//		System.out.println(param.getFonction());
//		System.out.println(param.getIdParametre());
//		System.out.println(param.getSeuil());
//		System.out.println(param.getIdRule());
//		alerteInterface.addParametre(param);
//		params = alerteInterface.getAllParametres();
//	}
//
//	public void deleteParam() {
//		alerteInterface.deleteParametre(param1);
//		params = alerteInterface.getAllParametres();
//	}
//
//	public void modifierParam() {
//		alerteInterface.updatePrametre(param1);
//		params = alerteInterface.getAllParametres();
//
//	}

	@DELETE
	@Path("/deletereports/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletereports(@PathParam("id") Long id) {
		alerteInterface.removeReport(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST
	@Path("/updatereports")
	@Produces(MediaType.APPLICATION_JSON)
	public void updatereports(String jsonData) {

		ReportRarule reprule = convertJsonToObjectReportRarule(jsonData);
		alerteInterface.updateReport(reprule);
		reports = alerteInterface.getAllReport();

	}

	@PUT
	@Path("/editreport/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editreport(@PathParam("id") Long id, String jsonData) {
		ReportRarule rep = convertJsonToObjectReportRarule(jsonData);
		alerteInterface.updateRaport(id, rep);
		reports = alerteInterface.getAllReport();
		return Response.status(Response.Status.OK).build();

	}

	@POST
	@Path("/addreportrarule")
	@Produces(MediaType.APPLICATION_JSON)
	public void addreportrarule(String jsonData) {
		System.out.println(jsonData);
		ReportRarule reprule = convertJsonToObjectReportRarule(jsonData);

		alerteInterface.addReport(reprule);

		reports = alerteInterface.getAllReport();

	}

	@GET
	@Path("/reportrarule")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallReportRarule() {
		List<ReportRarule> ReportRaruleList = alerteInterface.getAllReport();

		// Convert the list to JSON
		String jsonData = convertListToJsonReportRarule(ReportRaruleList);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	private String convertListToJsonReportRarule(List<ReportRarule> ReportRaruleList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(ReportRaruleList);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	private ReportRarule convertJsonToObjectReportRarule(String jsonData) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(jsonData, ReportRarule.class);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

//	public void ajouterReport() {
//		alerteInterface.addReport(rep);
//
//		reports = alerteInterface.getAllReport();
//	}
//
//	public void deleteRep() {
//		alerteInterface.deleteReport(rep1);
//		reports = alerteInterface.getAllReport();
//	}
//
//	public void modifierRep() {
//
//		alerteInterface.updateReport(rep1);
//		reports = alerteInterface.getAllReport();
//
//	}

	public void ajouter() {
		alerteInterface.addRevenueStream(rev);
		revenueList = alerteInterface.getAll();
	}

	public void showmsg2() {
		System.out.println("-------------- " + user2.getName());
	}

	public void showdata() {

		System.out.println(param.getIdRule());

	}

	public void showdata2() {

		System.out.println(param.getIdParametre());
	}

	@POST
	@Path("/add")
	public Response addUser(UserLogin user) {
		// Set user state, created date, and created by
		user.setEtat("A");
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		user.setCreatedDate(currentTimestamp);

		// Hash the password
		try {
			MessageDigest mdsha1 = MessageDigest.getInstance("SHA-1");
			mdsha1.update(user.getPassword().getBytes());
			byte[] digestsha1 = mdsha1.digest();
			String myHashsha1 = javax.xml.bind.DatatypeConverter.printHexBinary(digestsha1).toUpperCase();
			user.setPassword(myHashsha1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// Return an error response if hashing fails
			return Response.serverError().build();
		}

		// Call the method to add the user (assuming userRemote is an instance of a
		// remote service)
		userRemote.addUser(user);

		// Get all users (assuming userRemote is an instance of a remote service)
		List<UserLogin> users = userRemote.getallusers();

		// Return a success response with the updated user list
		return Response.ok(users).build();
	}
	
	 @POST
	    @Path("/auth")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response loginUser(UserLogin user) {
	        // Authenticate the user
	        UserLogin authenticatedUser = userRemote.authenticateUser(user.getLogin(), user.getPassword());

	        if (authenticatedUser != null) {
	            // Generate and store the authentication token
	            authenticatedUser.generateAuthToken();
	            userRemote.addUser(authenticatedUser);

	            // Return the authenticated user with the token
	            return Response.ok(authenticatedUser).build();
	        } else {
	            // Return an error response if authentication fails
	            return Response.status(Response.Status.UNAUTHORIZED).build();
	        }
	    }

	@PUT
	@Path("/edituser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") Integer id, String jsonData) {
		UserLogin u = convertJsonToObjectUser(jsonData);
		userRemote.editUser(id, u);
		users = userRemote.getallusers();
		return Response.ok(users).build();
	}

	// usermanager ********************************************
	HttpSession session = null;

	@DELETE
	@Path("/deleteuser")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteuser(String jsonData) {
		UserLogin us = convertJsonToObjectUser(jsonData);
		userRemote.deleteUser(us);
		users = userRemote.getallusers();
	}

	@POST
	@Path("/updateuser")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateuser(String jsonData) {

		UserLogin us = convertJsonToObjectUser(jsonData);
		userRemote.updateUser(us);
		users = userRemote.getallusers();

	}

	@GET
	@Path("/userlist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallUsers() {
		List<UserLogin> UserList = userRemote.getallusers();
		// Convert the list to JSON
		String jsonData = convertListToJsonUser(UserList);

		// Return the JSON data
		return Response.ok(jsonData).header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your
																									// Angular
																									// application's URL
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept").build();
	}

	// Convert the list to JSON
	// String jsonData = convertListToJsonReportRarule(ReportRaruleList);

	// Return the JSON data
//		 return Response.ok(jsonData)
//	                .header("Access-Control-Allow-Origin", "http://localhost:4200") // Replace with your Angular application's URL
//	                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
//	                .header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
//	                .build();

	private String convertListToJsonUser(List<UserLogin> UserList) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(UserList);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}

	private UserLogin convertJsonToObjectUser(String jsonData) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(jsonData, UserLogin.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void adduser() throws IOException {
		user1.setEtat("A");
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		user1.setCreatedDate(currentTimestamp);
		session = Util.getSession();
		UserLogin u = (UserLogin) session.getAttribute("user.account");
		user1.setCreatedBy(u.getLogin());

		MessageDigest mdsha1;
		try {
			mdsha1 = MessageDigest.getInstance("SHA-1");
			mdsha1.update(user1.getPassword().getBytes());
			byte[] digestsha1 = mdsha1.digest();
			String myHashsha1 = DatatypeConverter.printHexBinary(digestsha1).toUpperCase();
			user1.setPassword(myHashsha1);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userRemote.addUser(user1);

		users = userRemote.getallusers();
		user1 = new UserLogin();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/page/usermanager/users.jsf");
	}

	public void updateuser() {
		userRemote.updateUser(user2);
		users = userRemote.getallusers();
		user2 = new UserLogin();
	}

	public void deleteuser() {

		userRemote.deleteUser(user2);
		users = userRemote.getallusers();
		user2 = new UserLogin();

	}

	@DELETE
	@Path("/deleteuser/{id}")
	public Response deleteuser(@PathParam("id") Integer id) {
		userRemote.removeUser(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	
	

	

	// end of user manager ********************************************

	public AlerteRa getAlert() {
		return alert;
	}

	public void setAlert(AlerteRa alert) {
		this.alert = alert;
	}

	public AlerteRa getAlert1() {
		return alert1;
	}

	public void setAlert1(AlerteRa alert1) {
		this.alert1 = alert1;
	}

	public HistoriqueAlerte getHist() {
		return hist;
	}

	public void setHist(HistoriqueAlerte hist) {
		this.hist = hist;
	}

	public HistoriqueAlerte getHist1() {
		return hist1;
	}

	public void setHist1(HistoriqueAlerte hist1) {
		this.hist1 = hist1;
	}

	public Rarule getRule() {
		return rule;
	}

	public void setRule(Rarule rule) {
		this.rule = rule;
	}

	public Rarule getRule1() {
		return rule1;
	}

	public void setRule1(Rarule rule1) {
		this.rule1 = rule1;
	}

	public ReportRarule getRep() {
		return rep;
	}

	public void setRep(ReportRarule rep) {
		this.rep = rep;
	}

	public ReportRarule getRep1() {
		return rep1;
	}

	public void setRep1(ReportRarule rep1) {
		this.rep1 = rep1;
	}

	public ParametresRarule getParam() {
		return param;
	}

	public void setParam(ParametresRarule param) {
		this.param = param;
	}

	public ParametresRarule getParam1() {
		return param1;
	}

	public void setParam1(ParametresRarule param1) {
		this.param1 = param1;
	}

	public RevenueStream getRev1() {
		return rev1;
	}

	public void setRev1(RevenueStream rev1) {
		this.rev1 = rev1;
	}

	public DomainControl getDom1() {
		return dom1;
	}

	public void setDom1(DomainControl dom1) {
		this.dom1 = dom1;
	}

	private List<ParametresRarule> params2 = new ArrayList<ParametresRarule>();
	private List<RevenueStream> revenueList = new ArrayList<RevenueStream>();
	private List<DomainControl> domainList = new ArrayList<DomainControl>();
	private List<Rarule> rarules = new ArrayList<Rarule>();
	private List<ParametresRarule> params = new ArrayList<ParametresRarule>();
	private List<ReportRarule> reports = new ArrayList<ReportRarule>();
	private List<AlerteRa> alertes = new ArrayList<AlerteRa>();
	private List<HistoriqueAlerte> histList = new ArrayList<HistoriqueAlerte>();
	private List<RepRapport> repRaports = new ArrayList<RepRapport>();
	private List<Function> functions = new ArrayList<Function>();
	private List<Flow> flows = new ArrayList<Flow>();

	private String inf = "<";
	private String sup = ">";
	private String egal = "=";

	public List<RepRapport> getRepRaports() {
		return repRaports;
	}

	public void setRepRaports(List<RepRapport> repRaports) {
		this.repRaports = repRaports;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public List<AlerteRa> getAlertes() {
		return alertes;
	}

	public void setAlertes(List<AlerteRa> alertes) {
		this.alertes = alertes;
	}

	public List<HistoriqueAlerte> getHistList() {
		return histList;
	}

	public void setHistList(List<HistoriqueAlerte> histList) {
		this.histList = histList;
	}

	public List<Rarule> getRarules() {
		return rarules;
	}

	public void setRarules(List<Rarule> rarules) {
		this.rarules = rarules;
	}

	public List<ParametresRarule> getParams() {
		return params;
	}

	public void setParams(List<ParametresRarule> params) {
		this.params = params;
	}

	public List<ReportRarule> getReports() {
		return reports;
	}

	public void setReports(List<ReportRarule> reports) {
		this.reports = reports;
	}

	private AlerteRa alertselected = new AlerteRa();

	public void showmsgalert() {
		System.out.println(alertselected.getDateDetection());
	}

	public void assigned() {

	}

	public void reassignedTO() {

	}

	public void close() {

	}

	private int sumAlert = 0;

	@PostConstruct
	public void init() {
		alertselected = new AlerteRa();
		user1 = new UserLogin();
		user2 = new UserLogin();
		users = new ArrayList<UserLogin>();
		inf = "<";
		sup = ">";
		egal = "=";
		params = new ArrayList<>();
		rev = new RevenueStream();
		dom = new DomainControl();
		rule = new Rarule();
		param = new ParametresRarule();
		rep = new ReportRarule();
		alert = new AlerteRa();
		hist = new HistoriqueAlerte();
		rev1 = new RevenueStream();
		dom1 = new DomainControl();
		rule1 = new Rarule();
		param1 = new ParametresRarule();
		rep1 = new ReportRarule();
		alert1 = new AlerteRa();
		hist1 = new HistoriqueAlerte();
		flows = alerteInterface.getAllFlow();
		repRaports = alerteInterface.getAllrapports();
		functions = alerteInterface.getAllFunction();
		revenueList = alerteInterface.getAll();
		domainList = alerteInterface.getAllDomain();
		rarules = alerteInterface.getAllRarules();
		params = alerteInterface.getAllParametres();
		reports = alerteInterface.getAllReport();
		alertes = alerteInterface.getAllAlertes();

		for (int i = 0; i <= alertes.size() - 1; i++) {
			String descriptionRule = alerteInterface.descriptioRule(alertes.get(i).getIdRule());
			alertes.get(i).setDesc(descriptionRule);

		}
		sumAlert = 0;
		sumAlert = alertes.size();
		histList = alerteInterface.getAllHist();

		users = userRemote.getallusers();

	}

	public List<Flow> getFlows() {
		return flows;
	}

	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	public List<RevenueStream> getRevenueList() {
		return revenueList;
	}

	public void setRevenueList(List<RevenueStream> revenueList) {
		this.revenueList = revenueList;
	}

	public List<DomainControl> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<DomainControl> domainList) {
		this.domainList = domainList;
	}

	public RevenueStream getRev() {
		return rev;
	}

	public void setRev(RevenueStream rev) {
		this.rev = rev;
	}

	public DomainControl getDom() {
		return dom;
	}

	public void setDom(DomainControl dom) {
		this.dom = dom;
	}

	public void delete() {
		alerteInterface.deleteRevenueStream(rev1);
		revenueList = alerteInterface.getAll();
	}

	public void ajouterDom() {
		alerteInterface.addDomain(dom);
		domainList = alerteInterface.getAllDomain();
	}

	public void deleteDom() {
		alerteInterface.deleteDomain(dom1);
		domainList = alerteInterface.getAllDomain();
	}

	public void modifierDom() {

		alerteInterface.updateDomain(dom1);
		domainList = alerteInterface.getAllDomain();

	}

	public void ajouterRarule() {
		HttpSession session = Util.getSession();
		rule.setNomUtilisateur(session.getAttribute("username").toString());
		alerteInterface.addRarule(rule);
		rarules = alerteInterface.getAllRarules();
	}

	public void deleteRarule() {
		alerteInterface.deleteRarule(rule1);
		rarules = alerteInterface.getAllRarules();
	}

	public void modifierRarule() {

		alerteInterface.updateRarule(rule1);
		rarules = alerteInterface.getAllRarules();

	}

	public void ajouterParam() {
		System.out.println(param.getFonction());
		System.out.println(param.getIdParametre());
		System.out.println(param.getSeuil());
		System.out.println(param.getIdRule());
		alerteInterface.addParametre(param);
		params = alerteInterface.getAllParametres();
	}

	public void deleteParam() {
		alerteInterface.deleteParametre(param1);
		params = alerteInterface.getAllParametres();
	}

	public void modifierParam() {
		alerteInterface.updatePrametre(param1);
		params = alerteInterface.getAllParametres();

	}

	public void ajouterReport() {
		alerteInterface.addReport(rep);

		reports = alerteInterface.getAllReport();
	}

	public void deleteRep() {
		alerteInterface.deleteReport(rep1);
		reports = alerteInterface.getAllReport();
	}

	public void modifierRep() {

		alerteInterface.updateReport(rep1);
		reports = alerteInterface.getAllReport();

	}

	public void ajouterAlert() {
		alerteInterface.addAlerte(alert);
		alertes = alerteInterface.getAllAlertes();
	}

	public void deleteAlert() {
		alerteInterface.deleteAlerte(alert1);
		;
		alertes = alerteInterface.getAllAlertes();
	}

	public void modifierAlrte() {

		alerteInterface.updatAlerte(alert1);
		alertes = alerteInterface.getAllAlertes();

	}

	public void ajouterHist() {
		alerteInterface.addHist(hist);
		;
		histList = alerteInterface.getAllHist();
	}

	public void deleteHist() {
		alerteInterface.deleteHist(hist1);
		histList = alerteInterface.getAllHist();
	}

	public void modifierHist() {

		alerteInterface.updateHist(hist1);
		histList = alerteInterface.getAllHist();

	}

	public List<ParametresRarule> getParams2() {
		return params2;
	}

	public void setParams2(List<ParametresRarule> params2) {
		this.params2 = params2;
	}

	public String getInf() {
		return inf;
	}

	public void setInf(String inf) {
		this.inf = inf;
	}

	public String getSup() {
		return sup;
	}

	public void setSup(String sup) {
		this.sup = sup;
	}

	public String getEgal() {
		return egal;
	}

	public void setEgal(String egal) {
		this.egal = egal;
	}

	public DomainControl getDomselected() {
		return domselected;
	}

	public void setDomselected(DomainControl domselected) {
		this.domselected = domselected;
	}

	public UserLogin getUser1() {
		return user1;
	}

	public void setUser1(UserLogin user1) {
		this.user1 = user1;
	}

	public UserLogin getUser2() {
		return user2;
	}

	public void setUser2(UserLogin user2) {
		this.user2 = user2;
	}

	public List<UserLogin> getUsers() {
		return users;
	}

	public void setUsers(List<UserLogin> users) {
		this.users = users;
	}

	public List<UserLogin> getFiltreduser() {
		return filtreduser;
	}

	public void setFiltreduser(List<UserLogin> filtreduser) {
		this.filtreduser = filtreduser;
	}

	public UserLogin getUser3() {
		return user3;
	}

	public void setUser3(UserLogin user3) {
		this.user3 = user3;
	}

	public AlerteRa getAlertselected() {
		return alertselected;
	}

	public void setAlertselected(AlerteRa alertselected) {
		this.alertselected = alertselected;
	}

	public int getSumAlert() {
		return sumAlert;
	}

	public void setSumAlert(int sumAlert) {
		this.sumAlert = sumAlert;
	}
	
	@POST
	@Path("/assignAlertUser")
	@Produces(MediaType.APPLICATION_JSON)
	public void assignAlertUser(String jsonData) {
		System.out.println(jsonData);
		Alertuser alertuser = convertJsonToObjectAlertUser(jsonData);

		alerteInterface.assignAlertUser(alertuser);

		

	}
	
	private Alertuser convertJsonToObjectAlertUser(String jsonData) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(jsonData, Alertuser.class);
		} catch (Exception e) {
			// Handle the exception appropriately
			e.printStackTrace();
			return null;
		}
	}
}
