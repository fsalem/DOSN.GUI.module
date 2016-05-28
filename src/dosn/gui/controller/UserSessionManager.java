package dosn.gui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import dosn.database.dao.UserDao;
import dosn.database.entities.User;
import dosn.utility.json.UserJSON;


@Component
@EnableScheduling
@Singleton
public class UserSessionManager {

	private HashMap<UUID,UserSession> userSessions=new HashMap<UUID, UserSession>(); //Key=Long=AccountId, Value=AccountSessionInfo 

	@Inject
	UserDao userDao;
	
	//Add a UserSession for each user in lokal Database
	@PostConstruct
	public void init(){
		List<User> users = userDao.findAllUser();
		for(User user:users){
			UserSession userSession = new UserSession();
			userSession.setUser(user);
			addUserSession(userSession);
		}
		
		
	}
	
	
	
	public UserSession getUserSession(UUID uuid){
		synchronized(userSessions){
			return userSessions.get(uuid);
		}
	}
	
	public void addUserSession(UserSession userSession){
		synchronized(userSessions){
			if(userSession!=null)
				this.userSessions.put(userSession.getUniqueSessionID(), userSession);
		}
	}

	public void removeUserSession(UUID id){
		synchronized(userSessions){
			this.userSessions.remove(id);
		}
	}
	
	public UserSession getUserSessionByUser(User user){
		synchronized(userSessions){
			for(UserSession session: userSessions.values()){
				if(session.getUser().equals(user)){
					return session;
				}
			}
			
			
		}
		return null;
	}
	
}
