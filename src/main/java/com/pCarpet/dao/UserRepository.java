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
	
		User save(User user);	
		boolean existsByUsername(String username);

}
