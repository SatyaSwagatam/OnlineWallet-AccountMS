package com.cg.onlinewallet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;

@Transactional
@SpringBootApplication
public class OnlineWalletApplication{ //implements CommandLineRunner {
	@Autowired
	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(OnlineWalletApplication.class, args);
	}
/*
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	    			
			WalletAccount wa1 = new WalletAccount(1000.00, list1, status.active);
			WalletAccount wa2 = new WalletAccount(1000.00, list2, status.non_active);
			WalletAccount wa3 = new WalletAccount(0.0, status.active);
			WalletAccount wa4 = new WalletAccount(0.0, status.non_active);
			WalletAccount wa5 = new WalletAccount(0.0, status.non_active);
			
			em.persist(wa1);
			em.persist(wa2);
			em.persist(wa3);
			em.persist(wa4);
			em.persist(wa5);
			
			WalletUser wu1 = new WalletUser("Satya Swagatam Panda", "Satya@123", "9011223344", "satyaswagatam@gmail.com",type.user, wa1);
			WalletUser wu2 = new WalletUser("Karan Gupta","Karan@123","9876543210","karangupta9439@gmail.com",type.user,wa2);
			WalletUser wu3 = new WalletUser("Admin", "Admin@123", "1234567890", "Admin@gmail.com", type.admin, wa3);
			WalletUser wu4 = new WalletUser("User1", "User1@123", "7865432101", "User1@gmail.com", type.user, wa4);
			WalletUser wu5 = new WalletUser("User2", "User2@123", "7987654310", "User2@gmail.com", type.user, wa5);
			
			em.persist(wu1);
			em.persist(wu2);
			em.persist(wu3);
			em.persist(wu4);
			em.persist(wu5);
			
	}
    */
}