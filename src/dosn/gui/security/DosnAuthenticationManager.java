package dosn.gui.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dosn.database.dao.UserDao;
import dosn.database.entities.User;


@Service("dosnAuthenticationManager")
public class DosnAuthenticationManager  implements UserDetailsService {

	
	@Inject
	private UserDao daoUser;

	@PostConstruct
	public void init(){
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
	
	
		User userEntity = daoUser.findByName(username);

			if(userEntity==null) {
				throw new UsernameNotFoundException("User error");
				
			}
			
			String password = userEntity.getUserPassword();

			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			

			org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
					username, password, enabled, accountNonExpired,
					credentialsNonExpired, accountNonLocked, authorities);
			System.out.println("login user: " + user.getUsername());
			return user;
		
	}
	
}
