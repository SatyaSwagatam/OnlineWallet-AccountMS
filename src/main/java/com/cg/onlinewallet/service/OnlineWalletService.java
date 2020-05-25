package com.cg.onlinewallet.service;

import java.util.List;

import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletService {

	Double addMoney(Integer userId, Double Amount);
	
	Double showBalance(Integer userId);

	List<String> getUserList(Integer userId, String userStatus);

	String changeUserStatus(Integer adminId, String loginName, String userStatus);

}