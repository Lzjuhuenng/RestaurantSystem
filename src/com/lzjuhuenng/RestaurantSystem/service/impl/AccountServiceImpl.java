package com.lzjuhuenng.RestaurantSystem.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.lzjuhuenng.RestaurantSystem.dao.IAccountDao;
import com.lzjuhuenng.RestaurantSystem.dao.impl.AccountDaoMysqlImpl;
import com.lzjuhuenng.RestaurantSystem.entery.Account;
import com.lzjuhuenng.RestaurantSystem.entery.Page;
import com.lzjuhuenng.RestaurantSystem.service.IAccountService;

public class AccountServiceImpl implements IAccountService{

	public AccountServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	Account acc = null;
	IAccountDao accDao = new AccountDaoMysqlImpl();
	List<Account> accList = null;
	
	/**
	 * �û���¼������ͨ������Dao��鿴���˺��Ƿ���ڣ��Լ������Ƿ�ƥ�䣬һ���˺ź�����ƥ����Dao�㷵�ص�Accountʵ������session ͬʱ����true
	 * @param request
	 * @return �����˺�ƥ��ɹ�����true �����ɹ��򷵻�false
	 */
	public int Login(HttpServletRequest request){
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		acc = accDao.getAccount(account);
		if(acc.getAccount() != null){
			if(acc.getPassword().equals(password)){
				HttpSession session = request.getSession();
				session.setAttribute("account",acc );
				return acc.getRole();
			}
		}
		return -1;
	}
	public boolean addAccountIntoDB(HttpServletRequest request){
		System.out.println(request.getCharacterEncoding());
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		
		String ac_role = request.getParameter("role");
		int role = Integer.parseInt(ac_role);
		acc = new Account();
		acc.setAccount(account);
		acc.setPassword(password);
		acc.setUsername(username);
		acc.setRole(role);
		return accDao.insertAccount(acc);
	}
	public boolean delAccountFromDB(HttpServletRequest request){
		acc = new Account();
		acc.setId(Integer.parseInt(request.getParameter("id")));
		return accDao.deleteAccount(acc);
	}
	
	public boolean updateAccount(HttpServletRequest request){
		acc = new Account();
		acc.setId(Integer.parseInt(request.getParameter("id")));
		acc.setAccount(request.getParameter("account"));
		acc.setPassword(request.getParameter("password"));
		acc.setUsername(request.getParameter("username"));
		acc.setRole(Integer.parseInt(request.getParameter("role")));
		return accDao.updateAccount(acc);
	}
	
	public List<Account> queryAccountFromDB(HttpServletRequest request){
		acc = new Account();
		if(request.getParameter("id")!=null && !"".equals(request.getParameter("id"))){
			acc.setId(Integer.parseInt(request.getParameter("id")));
		}
		if(request.getParameter("account")!=null && !"".equals(request.getParameter("account"))){
			acc.setAccount(request.getParameter("account"));
		}
		if(request.getParameter("username")!=null && !"".equals(request.getParameter("username"))){
		acc.setUsername(request.getParameter("username"));
		}
		if(request.getParameter("role")!=null &&! "".equals(request.getParameter("role"))){
			acc.setRole(Integer.parseInt(request.getParameter("role")));
		}
		accList = accDao.queryAccount(acc);
		accList.add(acc);
		return accList;
	}
	
	public Page<Account> queryAccountFromDBByPage(HttpServletRequest request){
		Page<Account> accPage = new Page<Account>();
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("account"); 
		acc = new Account();
		acc.setUserRole(user.getRole());
		if(request.getParameter("id")!=null && !"".equals(request.getParameter("id"))){
			acc.setId(Integer.parseInt(request.getParameter("id")));
		}
		if(request.getParameter("account")!=null && !"".equals(request.getParameter("account"))){
			acc.setAccount(request.getParameter("account"));
		}
		if(request.getParameter("username")!=null && !"".equals(request.getParameter("username"))){
			acc.setUsername(request.getParameter("username"));
		}
		if(request.getParameter("role")!=null &&! "".equals(request.getParameter("role"))){
			acc.setRole(Integer.parseInt(request.getParameter("role")));
		}
		accPage.setQueryAttr(acc);
		if(request.getParameter("pageNo")!=null &&! "".equals(request.getParameter("pageNo"))){
			accPage.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		accPage = accDao.queryAccountByPage(accPage);
		accPage.getResultList().add(acc);
		return accPage;
	}
	
}
