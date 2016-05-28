package dosn.gui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dosn.database.entities.User;
import dosn.utility.json.InterestJSON;
import dosn.utility.json.UserJSON;

public class UserSession {

	private User sessionUser;
	private UUID uniqueSessionID = UUID.randomUUID();
	private List<UserJSON> searchUserResultList = new ArrayList<UserJSON>();
	private List<UserJSON> recommendationUserResultList = new ArrayList<UserJSON>();
	private List<InterestJSON> searchInterestResultList = new ArrayList<InterestJSON>();
	private List<InterestJSON> recommendationInteretsResultList = new ArrayList<InterestJSON>();
	
	
	
	public void addUserSearchResults(List<UserJSON> searchResults){
		try{
		for(UserJSON user:searchResults){
			if(!searchUserResultList.contains(user) && !user.getUsername().equals(sessionUser.getUserName())){
				searchUserResultList.add(user);
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void addInterestSearchResults(List<InterestJSON> searchResults){
		try{
		for(InterestJSON interest:searchResults){
			if(!searchInterestResultList.contains(interest)){
				searchInterestResultList.add(interest);
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void addUserRecommendationResults(List<UserJSON> searchResults){
		try{
		for(UserJSON user:searchResults){
			if(!recommendationUserResultList.contains(user) && !user.getUsername().equals(sessionUser.getUserName())){
				recommendationUserResultList.add(user);
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void addInterestRecommendationResults(List<InterestJSON> searchResults){
		try{
		for(InterestJSON interest:searchResults){
			if(!recommendationInteretsResultList.contains(interest)){
				recommendationInteretsResultList.add(interest);
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public UUID getUniqueSessionID() {
		return uniqueSessionID;
	}
	public void setUniqueSessionID(UUID uniqueSessionID) {
		this.uniqueSessionID = uniqueSessionID;
	}
	public User getUser() {
		return sessionUser;
	}
	public void setUser(User user) {
		this.sessionUser = user;
	}
	public List<UserJSON> getSearchUserResultList() {
		return searchUserResultList;
	}
	public void setSearchUserResultList(List<UserJSON> searchUserResultList) {
		this.searchUserResultList = searchUserResultList;
	}
	public List<UserJSON> getRecommendationUserResultList() {
		return recommendationUserResultList;
	}
	public void setRecommendationUserResultList(
			List<UserJSON> recommendationUserResultList) {
		this.recommendationUserResultList = recommendationUserResultList;
	}
	public List<InterestJSON> getSearchInterestResultList() {
		return searchInterestResultList;
	}
	public void setSearchInterestResultList(
			List<InterestJSON> searchInterestResultList) {
		this.searchInterestResultList = searchInterestResultList;
	}
	public List<InterestJSON> getRecommendationInteretsResultList() {
		return recommendationInteretsResultList;
	}
	public void setRecommendationInteretsResultList(
			List<InterestJSON> recommendationInteretsResultList) {
		this.recommendationInteretsResultList = recommendationInteretsResultList;
	}
	
	
	
	
}
