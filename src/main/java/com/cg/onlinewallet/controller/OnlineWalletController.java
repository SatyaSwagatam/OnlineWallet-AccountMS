/**
* @author:Satya Swagatam Panda
* Description: This is a controller class which handles the web requests made by the user and then maps it according to the required handler method.
*/
package com.cg.onlinewallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.service.*;
@CrossOrigin(origins="*")
@RestController
public class OnlineWalletController {

	@Autowired
	private OnlineWalletService onlineWalletService;

	public OnlineWalletController() {
		// TODO Auto-generated constructor stub
	}
    
	@RequestMapping("/home")
	public String check() {
		return "WORKING";
	}
	
/**
* Method:addMoney
* Description:To map the request of user for adding money
* @param userId:User's Id
* @param amount:Amount to be added
* @returns Entity:After adding money to the account it will return the amount added into the account.
* Created By-Satya Swagatam Panda
*/
	@PutMapping("/addmoney/{userId}")
	public ResponseEntity<Double> addMoney(@PathVariable("userId") Integer userId, Double amount) {
		Double balance=onlineWalletService.addMoney(userId, amount);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}
/**
* Method:showBalance
* Description:To map the request of user for showing the balance available in the account
* @param userId:User's Id
* @returns Entity: The balance that is available in the account.
* Created By-Satya Swagatam Panda
*/
	@GetMapping("/showbalance/{userId}")
	public ResponseEntity<Double> showBalance(@PathVariable("userId") Integer userId) {
		Double balance = onlineWalletService.showBalance(userId);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}

/**
* Method:getUserList
* Description:To map the request of admin for accessing the list of users who are either active or inactive users
* @param userId:User's Id
* @param userStatus:User's account status
* @returns Entity:This returns the list of users having active or inactive accounts
* Created By-Satya Swagatam Panda
*/
	@GetMapping("/admin/{adminId}/userlist")
	public ResponseEntity<List<String>> getUserList(@PathVariable("adminId") Integer userId, String userStatus) {
		List<String> userList = onlineWalletService.getUserList(userId, userStatus);
		return new ResponseEntity<List<String>>(userList, HttpStatus.OK);
	}
/**
* Method:changeUserStatus
* Description:To map the request of admin to change the status of user's account to active or inactive
* @param userId:Admin's Id
* @param email:User's email
* @param userStatus: User's account status
* @returns Entity:This will change the user's account status and return that information
* Created By-Satya Swagatam Panda
*/
	@GetMapping("/admin/{adminId}/changestatus")
	public ResponseEntity<String> changeUserStatus(@PathVariable("adminId") Integer adminId, String email,
			String userStatus) {
		String returnEmail=onlineWalletService.changeUserStatus(adminId, email, userStatus);
		return new ResponseEntity<String>(returnEmail, HttpStatus.OK);
	}
}