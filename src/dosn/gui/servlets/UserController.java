package dosn.gui.servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import dosn.database.dao.UserDao;
import dosn.database.entities.User;
import dosn.database.facade.DatabaseInteractionLayer;
import dosn.gui.controller.UserSession;
import dosn.gui.controller.UserSessionManager;
import dosn.utility.general.Helper;
import dosn.utility.general.PropertiesLookup;
import dosn.utility.json.RRequestJSON;
import dosn.utility.json.SRResponseJSON;

@Controller
public class UserController {

	@Inject
	UserDao userDao;

	@Inject
	UserSessionManager userSessionManager;

	@Inject
	DatabaseInteractionLayer databaseInteractionLayer;

	@PostConstruct
	public void init() {
		System.out.println("init UserController");
	}

	@RequestMapping(value = { "/gui/searchResponse" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void receiveSearchResults(@RequestBody SRResponseJSON searchResponse) {
		System.out.println("UserController: receiveSearchResults:"
				+ searchResponse.getUsers().toString() + "  msgID: "
				+ searchResponse.getMessageUID());

		UserSession userSession = userSessionManager.getUserSession(UUID
				.fromString(searchResponse.getMessageUID()));

		if (searchResponse.getUsers() != null) {
			userSession.addUserSearchResults(searchResponse.getUsers());
		}

		if (searchResponse.getInterests() != null) {
			userSession.addInterestSearchResults(searchResponse.getInterests());
			System.out.println("interests: "
					+ searchResponse.getInterests().toString());
		}
	}

	@RequestMapping(value = { "/gui/recommendationResponse" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void receiveRecommendationResults(
			@RequestBody SRResponseJSON response) {
		System.out.println("UserController: receiveRecommendationResults:"
				+ response.getUsers().toString() + "  msgID: "
				+ response.getMessageUID());

		UserSession userSession = userSessionManager.getUserSession(UUID
				.fromString(response.getMessageUID()));

		
		
		if (response.getUsers() != null) {
			userSession.getRecommendationUserResultList().clear();
			userSession.addUserRecommendationResults(response.getUsers());
		}

		if (response.getInterests() != null) {
			userSession.getRecommendationInteretsResultList().clear();
			userSession.addInterestRecommendationResults(response
					.getInterests());
		}
	}

	@RequestMapping(value = { "/gui/startSearch" }, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded; charset=UTF-8")
	@ResponseStatus(value = HttpStatus.OK)
	public String startSearchRequest(HttpServletRequest request,
			@RequestParam String searchWord,@RequestParam String searchMethod ) throws ServletException,
			IOException {
		System.out.println("UserController: startSearchRequest | Interests: "
				+ searchWord);
		Principal login = request.getUserPrincipal();

		if (login == null) {
			return "/login.jsp";
		}
		User user = userDao.findByName(login.getName());
		UserSession userSession = userSessionManager.getUserSessionByUser(user);
		userSession.getSearchUserResultList().clear();
		userSession.getSearchInterestResultList().clear();

		String host = request.getLocalAddr();
		String scheme = request.getScheme();
		int port = request.getLocalPort();
		String context = request.getContextPath();
		String guiReceiveURI = PropertiesLookup.getGuiSearchResponseUri();
		String responseURI = scheme + "://" + host + ":" + port + "" + context+"/"
				+ guiReceiveURI;

		System.out.println(" user: " + user.getUserName() + " SessionID: "
				+ userSession.getUniqueSessionID().toString() + " ContextURL:"
				+ responseURI);

		String serviceURI = null;
		String params = "";
		//Search in UserName or Interests List? Select Service URI and set Post Parameter Values
		if(searchMethod.equals(PropertiesLookup.getSearchInUserName())){
			//search in User Name
			serviceURI = PropertiesLookup.getSearchAndPropagateByUsernameUri();
			params = "username=" + searchWord;
		}else if(searchMethod.equals(PropertiesLookup.getSearchInInterests())){
			//search in User Interests
			serviceURI = PropertiesLookup.getSearchAndPropagateByInterestsUri();
			params = "interests=" + searchWord;
		}
		
		URL url = new URL(PropertiesLookup.getServerUrl()
				+ serviceURI);
		
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		
		params += "&msgID=" + userSession.getUniqueSessionID().toString();
		params += "&responsURI=" + responseURI;
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream());
		out.write(params);
		out.close();

		if (HttpStatus.OK.equals(connection.getResponseCode())) {
			System.out.println("status code: ok");
		} else {
			System.out.println("status code: " + connection.getResponseCode());
		}

		return null;
	}

	@RequestMapping(value = { "/gui/startRecommendation" }, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String startRecommendationRequest(HttpServletRequest request)
			throws ServletException, IOException {
		System.out.println("UserController: startRecommendationRequest ");
		Principal login = request.getUserPrincipal();

		if (login == null) {
			return "/login.jsp";
		}

		User user = userDao.findByName(login.getName());
		UserSession userSession = userSessionManager.getUserSessionByUser(user);
		// Clear old results
		userSession.getRecommendationUserResultList().clear();

		List<String> interestList = databaseInteractionLayer
				.retrieveInterestsByUser(user.getUserName());

		String interests = "";
		for (String i : interestList) {
			interests += i + ",";
		}
		if(interests.length()>0){
			interests = interests.substring(0, interests.length()-1);
		}
		
		String host = request.getLocalAddr();
		String scheme = request.getScheme();
		int port = request.getLocalPort();
		String context = request.getContextPath();
		String guiReceiveURI = PropertiesLookup
				.getGuiRecommendationResponseUri();

		String responseURI = scheme + "://" + host + ":" + port + "" + context + "/"
				+ guiReceiveURI;

		System.out.println(" user: " + user.getUserName() + "  Interests: "
				+ interests + ", SessionID: "
				+ userSession.getUniqueSessionID().toString() + " ContextURL:"
				+ responseURI);

		URL url = new URL(PropertiesLookup.getServerUrl()
				+ PropertiesLookup.getRecommendationStartURI());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		
		
		//change sessionId to new msg id
		userSessionManager.removeUserSession(userSession.getUniqueSessionID());
		userSession.setUniqueSessionID(UUID.randomUUID());
		userSession.getRecommendationInteretsResultList().clear();
		userSession.getRecommendationUserResultList().clear();
		userSessionManager.addUserSession(userSession);
		
		String msgID = userSession.getUniqueSessionID().toString();
		String userID = user.getUserName();
		Integer friendLevel = 0;
		Integer maxFriendLevel = 2;
		Integer maxResults = 5;
		Boolean friendsInculded = false;
		Double minSimilarityScore = 0.1;
	
		
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream());
		out.write(Helper.buildJSONRecommendationRequest(responseURI,interestList,msgID,userID,friendLevel,maxFriendLevel,maxResults,minSimilarityScore,friendsInculded));
		out.close();


		if (HttpStatus.OK.equals(connection.getResponseCode())) {
			System.out.println("status code: ok");
		} else {
			System.out.println("status code: " + connection.getResponseCode());
		}

		return null;
	}

}
