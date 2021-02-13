import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import pers.lilei.blog.constant.MessageConstant;
import pers.lilei.blog.po.User;
import pers.lilei.blog.pojo.UserBaseInfoPojo;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class UserServiceTest extends BaseTest{
	private UserService userService;
	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Test
	public void selectByUserName(){
		User user;
		user = userService.selectByUserName("lilei");
		if(user != null) {
			System.out.println("user:"+user.toString());
		}else {
			System.out.println("查询失败！");
		}
	}
	@Test
	public void selectByEmail() {
		User user;
		user = userService.selectByEmail("lilei.kuge@qq.com");
		if(user != null) {
			System.out.println("user:"+user.toString());
		}else {
			System.out.println("查询失败！");
		}
	}
	@Test
	public void selectByTel() {
		User user;
		user = userService.selectByTel(15149039299L);
		if(user != null) {
			System.out.println("user:"+user.toString());
		}else {
			System.out.println("查询失败！");
		}
	}
	@Test
	public void addUserSelective() {
		int age = 22;
		User user = new User("高学博", "1", "gaoxuebo@qq.com", "https://i.loli.net/2020/12/11/Gh9HoUW8qQcZKvV.jpg",
				new Date(), (byte) age, 15149039277L, "高学博");
//		User user = new User("高学博", "1", null, "https://i.loli.net/2020/12/11/Gh9HoUW8qQcZKvV.jpg",
//				new Date(), (byte) age, null, "高学博");
		System.out.println(userService.addUserSelective(user));
	}
	@Test
	public void deleteUserByUserId() {
		Long userId = Long.parseLong("2");
		System.out.println(userService.deleteUserByUserId(userId));
	}
	@Test
	public void updateUserSelective() {
		int age = 23;
		User user = new User("gaoxieen2", "1", "gaoxieen@qq.com", "https://i.loli.net/2020/12/11/Gh9HoUW8qQcZKvV.jpg",
				new Date(), (byte) age, 15149039288L, "高谢恩");
		user.setUserId(3L);
		System.out.println(userService.updateUserSelective(user));
	}
	@Test
	public void passwordAdd() {
		System.out.println(BCrypt.hashpw("1",BCrypt.gensalt()));
	}
	@Test
	public void updatePasswordByCode(){
		Integer code = 1234;
		User user = new User();
		user.setUserPassword("1");
		user.setUserTelephoneNumber(15149039299L);
		//验证码校验
		if (code == null && !code.equals(1234)) {
			System.out.println("请填写正确的验证码！");
		}
		if (user.getUserPassword() == null) {
			System.out.println("请填写密码！");
		}
		User selectUser;
		if (user.getUserTelephoneNumber() != null) {
			selectUser = userService.selectByTel(user.getUserTelephoneNumber());
		}else if (user.getUserEmail() != null) {
			selectUser = userService.selectByEmail(user.getUserEmail());
		}else {
			selectUser = null;
		}

		if (selectUser != null) {
			selectUser.setUserPassword(user.getUserPassword());
			selectUser.setTimeNull();
			if (!userService.updateUserSelective(selectUser).equals(0)) {
				System.out.println(MessageConstant.MESSAGE_SUCCESS);
			}else {
				System.out.println("密码修改失败！");
			}
		}else {
			System.out.println("电话或邮箱未匹配！");
		}
	}
	@Test
		public void selectUserBaseInfoByKey() {
		PageInfo<UserBaseInfoPojo> pageInfo = userService.selectUserBaseInfoByKey(1, 10, "l");
		System.out.println(pageInfo);
	}
	@Test
	public void selectAllUserBaseInfo() {
		PageInfo<UserBaseInfoPojo> pageInfo = (PageInfo<UserBaseInfoPojo>) userService.selectAllUserBaseInfo(1, 10);
		System.out.println(pageInfo);
	}
//	@Test
//	public void getAllByUserId() {
//		List<Long> userIdList = new ArrayList<>();
//		userIdList.add(1L);
//		userIdList.add(3L);
//		PageInfo<UserBaseInfoPojo> pageInfo = userService.getAllByUserId(1, 10, userIdList);
//		System.out.println(pageInfo);
//	}
	@Test
	public void selectUserBaseInfoByKeyWithoutMine() {
		PageInfo<UserBaseInfoPojo> pageInfo = userService.selectUserBaseInfoByKeyWithoutMine(1, 10, "我", 3L);
		System.out.println(pageInfo);
	}
	@Test
	public void setCode() {
		for (int i=0;i<10;i++) {
			Integer code = (int)((Math.random()*9+1)*100000);
			System.out.println(code.toString());
		}
	}
}
