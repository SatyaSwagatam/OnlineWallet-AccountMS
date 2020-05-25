/**
* @author:Satya Swagatam Panda
* Description: This is a service class which is providing the functionality like adding money to the account,checking balance available in the account,getting the user list,
* changing the user status
*/

package com.cg.onlinewallet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlinewallet.dao.*;
import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;
import com.cg.onlinewallet.exceptions.*;

@Transactional
@Service
public class OnlineWalletServiceImpl implements OnlineWalletService {

	public OnlineWalletServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private OnlineWalletDao onlineWalletDao;

/**
* Method: getUserList 
* Description: To return the list of email's of the user according to user status provided.
* @param adminId:Admin's userId
* @param userstatus: user status
* @returns List<String>: List containing the email's of the user based on their userStatus either active or non_active.
* @throws UnauthorizedAccessException:it is raised if the account associated with adminId is not an admin type
* @throws WrongValueException:it is raised if the variable userStatus is other then values active and non_active 
* Created By - Satya Swagatam Panda
*/
	@Override
	public List<String> getUserList(Integer adminId, String userStatus) {

		WalletUser admin = onlineWalletDao.getUser(adminId);
		if (admin.getUserType() == type.admin)
			throw new UnauthorizedAccessException("You are not authorized to perform this task");
		if (userStatus.equalsIgnoreCase(new String("non_active")))
			return onlineWalletDao.getNonActiveUserList();
		else if (userStatus.equalsIgnoreCase(new String("active")))
			return onlineWalletDao.getActiveUserList();
		throw new WrongValueException("not a criteria to fetch user details");
	}
/**
* Method: changeUserStatus 
* Description: Changes the status of account of user from active to non-active and other-way around
* @param adminId:Admin's userId
* @param email:User's email whose status has to be changed
* @param userstatus:user status
* @throws UnauthorizedAccessException:if the user associated with adminId is not a admin type
* @throws InvalidException:it is raised if there is no user associated with the email provided
* @throws UnauthorizedAccessException: it is raised if the user associated with the email is admin type
* @throws WrongValueException:it is raised if the variable userStatus is other then values active and non_active 
* Created By - Satya Swagatam Panda
*/
	@Override
	public String changeUserStatus(Integer adminId, String email, String userStatus) {

		WalletUser admin = onlineWalletDao.getUser(adminId);
		if (admin.getUserType() == type.admin)
			throw new UnauthorizedAccessException("You are Authorized to perform this task");
		if (onlineWalletDao.checkUserByEmail(email) == false)
			throw new InvalidException("There is no user with this LoginName. Please Enter a valid LoginName");
		WalletUser user = onlineWalletDao.getUserByEmail(email);
		if (user.getUserType() == type.admin)
			throw new UnauthorizedAccessException("Can't perform Task, Unauthorized Access");
		if (userStatus.equals(new String("non_active")))
			user.getAccountDetail().setUserStatus(status.non_active);
		else if (userStatus.equals(new String("active"))) {
			user.getAccountDetail().setUserStatus(status.active);
		} else
			throw new WrongValueException("The Status code does not exist");
		return user.getEmail();
	}

/**
* Method: addMoney 
* Description: Increments the balance of the user balance with the amount provided
* @param userId:User's userId
* @param amount:amount which has to be incremented 
* Created By - Satya Swagatam Panda
*/
	
	@Override
	public Double addMoney(Integer userId, Double Amount) {
		//WalletUser user = onlineWalletDao.getUser(userId);
		//WalletAccount account = user.getAccountDetail();
		//Integer accountId = user.getAccountDetail().getAccountID();
		//WalletAccount account = onlineWalletDao.getAccount(accountId);
		WalletAccount account = getWalletAccount(userId);
		Double balance = account.getAccountBalance();
		balance += Amount;
		account.setAccountBalance(balance);
		return account.getAccountBalance();
	}

	private WalletAccount getWalletAccount(Integer userId) {
		WalletUser user = onlineWalletDao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		return account;
	}
/**
* Method: showBalance 
* Description: fetches and returns the balance of the user
* @param userId: User's userid
* @returns Double: Balance fetched from the user account 
* Created By - Satya Swagatam Panda
*/
	@Override
	public Double showBalance(Integer userId) {
		//WalletUser user = onlineWalletDao.getUser(userId);
		//WalletAccount account = user.getAccountDetail();
		WalletAccount account = getWalletAccount(userId);
		return account.getAccountBalance();
	}
}