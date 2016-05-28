package dosn.gui.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.springframework.stereotype.Controller;

import dosn.database.dao.InterestDao;
import dosn.database.dao.PotentielServerDao;
import dosn.database.dao.UserDao;
import dosn.database.entities.Interest;
import dosn.database.entities.PotentielServer;
import dosn.database.entities.User;


@Singleton
@Controller
public class InitDatabase {

	@Inject
	UserDao userDao;
	
	@Inject 
	InterestDao interestDao;
	
	@Inject 
	PotentielServerDao potentielServerDao;
	
	
	@PostConstruct
	public void init(){
		
		/*
		try{
			potentielServerDao.deleteServer(potentielServerDao.findAllPotentielServer().get(0));
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		PotentielServer server = new PotentielServer();
		server.setServerAddress("http://localhost:8080/");
		server.setServerScore(1.0);
		potentielServerDao.addServer(server);
		
		
		System.out.println("init database");
		
		Interest i1 = new Interest();
		i1.setInterestName("Football");
		interestDao.addInterest(i1);
		Interest i2 = new Interest();
		i2.setInterestName("Beach Volleyball");
		interestDao.addInterest(i2);
		Interest i3 = new Interest();
		i3.setInterestName("Walking");
		interestDao.addInterest(i3);
		Interest i4 = new Interest();
		i4.setInterestName("Traveling");
		interestDao.addInterest(i4);
		Interest i5 = new Interest();
		i5.setInterestName("Bicycling");
		interestDao.addInterest(i5);
		Interest i6 = new Interest();
		i6.setInterestName("Cooking");
		interestDao.addInterest(i6);
		Interest i7 = new Interest();
		i7.setInterestName("Swimming");
		interestDao.addInterest(i7);
		Interest i8 = new Interest();
		i8.setInterestName("Dancing");
		interestDao.addInterest(i8);
		
		Set<Interest> interests = new HashSet<Interest>();
		interests.add(i1);
		interests.add(i2);
		interests.add(i3);
		
		User user = new User();
		user.setUserName("Shikhar Singh");
		user.setUserPassword("shikhar");
		user.setUserEmail("test@test.de");
		user.setTInterests(interests);
		userDao.addUser(user);
		
		Set<Interest> interests2 = new HashSet<Interest>();
		interests2.add(i1);
		interests2.add(i5);
		interests2.add(i6);
		interests2.add(i7);
		
		User user2 = new User();
		user2.setUserName("Farouk Salem");
		user2.setUserPassword("farouk");
		user2.setUserEmail("test@test.de");
		user2.setTInterests(interests2);
		userDao.addUser(user2);
		
		Set<Interest> interests3 = new HashSet<Interest>();
		interests3.add(i4);
		interests3.add(i8);
		interests3.add(i2);
		interests3.add(i1);
		
		User user3 = new User();
		user3.setUserName("Mahmoud Khodier");
		user3.setUserPassword("mahmoud");
		user3.setUserEmail("test@test.de");
		user3.setTInterests(interests3);
		userDao.addUser(user3);
		
		
		Set<Interest> interests4 = new HashSet<Interest>();
		interests4.add(i7);
		interests4.add(i3);
		interests4.add(i5);
		interests4.add(i6);
		
		User user4 = new User();
		user4.setUserName("Markus Beckmann");
		user4.setUserPassword("markus");
		user4.setUserEmail("test@test.de");
		user4.setTInterests(interests4);
		userDao.addUser(user4);

*/
	
	}
	
	
}
