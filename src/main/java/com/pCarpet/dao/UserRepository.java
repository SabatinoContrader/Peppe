package com.pCarpet.dao;

import com.pCarpet.model.User;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long>
{		
		User findByUsernameAndPassword(String username,String password);
	
		//User findByUsername(String username);
		//User save(User user);	  	  
		
//		@Modifying
//	    @Query("UPDATE Utente SET password=?1 WHERE username=?2")
//		void updateUtente(String newpassword, String username);
}
