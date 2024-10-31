package com.ssafy.model.service;

import java.util.List;
import java.util.Objects;

import com.ssafy.model.dao.UserInfoDao;
import com.ssafy.model.dao.UserInfoDaoImpl;
import com.ssafy.model.dto.UserInfo;
import com.ssafy.util.PasswordUtil;

public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoDao userInfoDao = UserInfoDaoImpl.getInstance();
	private static UserInfo loginUser;

	private static UserInfoService userInfoService = new UserInfoServiceImpl();

	private UserInfoServiceImpl() {
	}

	public static UserInfoService getInstance() {
		return userInfoService;
	}

	@Override
	public boolean addUser(UserInfo user) {
		// 비밀번호 해시화
		String hashedPassword = PasswordUtil.hashPassword(user.getUserPassword());
		user.setUserPassword(hashedPassword); // 해시된 비밀번호로 설정

		if (userInfoDao.insertUser(user) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfo modifyUserName(String userEmail, String modifyName) {
		UserInfo user = userInfoDao.selectByUserEmail(userEmail);

		System.out.println(userEmail + " " + user);
		if (Objects.nonNull(user)) {
			if (userInfoDao.updateUserName(userEmail, modifyName) != 0) {
				user.setUserName(modifyName);
				return user;
			}
			return null;
		}
		return null;
	}

	@Override
	public boolean deleteUser(String userEmail) {
		UserInfo user = userInfoDao.selectByUserEmail(userEmail);

		if (Objects.nonNull(user)) {
			if (userInfoDao.deleteMember(userEmail) != 0) {
				return true;
			}
			System.out.println("hi");
			return false;
		}
		System.out.println("[오류] 해당 회원의 정보가 올바르지 않거나 로그인한 사용자가 아닙니다.");
		return false;
	}

	@Override
	public List<UserInfo> findAll() {
		return userInfoDao.selectAll();
	}

	@Override
	public List<UserInfo> findAllByUserName(String searchName) {
		return userInfoDao.selectAllByUserName(searchName);
	}

	@Override
	public UserInfo modifyPwd(String userEmail, String userName, String modifyPwd) {
		UserInfo user = userInfoDao.selectByUserEmail(userEmail);

		if (Objects.nonNull(user)) {
			// 비밀번호 해시화
			String hashedPassword = PasswordUtil.hashPassword(modifyPwd);
			if (userInfoDao.updateUserPwd(userEmail, userName, hashedPassword) != 0) {
				user.setUserPassword(hashedPassword);
				return user;
			}
			return null;
		}

		return null;
	}

	@Override
	public UserInfo findByUserEmail(String userEmail) {
		return userInfoDao.selectByUserEmail(userEmail);
	}

	@Override
	public UserInfo login(String userEmail, String password) {
		UserInfo user = userInfoDao.selectByUserEmail(userEmail);

		if (user != null) {
			// 입력된 비밀번호와 저장된 비밀번호 해시값 비교
			String hashedPassword = PasswordUtil.hashPassword(password);
			if (hashedPassword.equals(user.getUserPassword())) {
				return user; // 비밀번호가 일치하면 로그인 성공
			}
		}

		System.out.println("[오류] 로그인 실패 : 일치하는 회원이 없습니다.");
		return null;
	}

	@Override
	public void logout() {
		loginUser = null;
	}

	@Override
	public boolean isLoginUser(UserInfo user) {
		if (user != null && loginUser != null && user.getUserEmail().equals(loginUser.getUserEmail())
				&& user.getUserPassword().equals(loginUser.getUserPassword())) {
			return true;
		}

		return false;
	}

}
