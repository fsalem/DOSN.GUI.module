package dosn.gui.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import dosn.database.dao.UserDao;
import dosn.database.entities.User;
import dosn.database.facade.DatabaseInteractionLayer;
import dosn.utility.general.PropertiesLookup;


@Controller
public class UserpageController {

	
	@Inject
	UserDao userDao;
	
	@Inject 
	UserSessionManager userSessionManager;
	
	@Inject
	DatabaseInteractionLayer databaseInteractionLayer;
	
	@RequestMapping(value = {"/userpage.html"}, method = RequestMethod.GET)
	public ModelMap index() {
		org.springframework.security.core.userdetails.User login = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(login==null) {
			return new ModelMap("redirect:/login.jsp");
		}
		
		User user = userDao.findByName(login.getUsername());
	
		ModelMap retval=new ModelMap();
		retval.put("userName", user.getUserName());
		retval.put("myInterests", databaseInteractionLayer.retrieveInterestsByUser(login.getUsername()));
		retval.put("myFriends", databaseInteractionLayer.retrieveFriendNames(login.getUsername()));
		
		return retval;
		
	
		
	}
	
	@RequestMapping(value = {"/searchResults.html"}, method = RequestMethod.GET)
	public ModelMap getSearchResults() {
		org.springframework.security.core.userdetails.User login = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(login==null) {
			return new ModelMap("redirect:/login.jsp");
		}
		
		User user = userDao.findByName(login.getUsername());
		UserSession userSession = userSessionManager.getUserSessionByUser(user);
		ModelMap retval=new ModelMap();
		retval.put("searchUserResults",userSession.getSearchUserResultList());
		retval.put("searchInterestsResults",userSession.getSearchInterestResultList());
		return retval;
	}
	
	@RequestMapping(value = {"/recommendationResults.html"}, method = RequestMethod.GET)
	public ModelMap getRecommendationResults() {
		org.springframework.security.core.userdetails.User login = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(login==null) {
			return new ModelMap("redirect:/login.jsp");
		}
		
		User user = userDao.findByName(login.getUsername());
		UserSession userSession = userSessionManager.getUserSessionByUser(user);
		ModelMap retval=new ModelMap();
		retval.put("recommendationUserResults",userSession.getRecommendationUserResultList());
		retval.put("recommendationInterestsResults",userSession.getRecommendationInteretsResultList());
		return retval;
	}
	
}
