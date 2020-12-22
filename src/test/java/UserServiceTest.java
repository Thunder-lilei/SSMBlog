import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.po.User;
import pers.lilei.blog.service.UserService;
import pers.lilei.blog.util.BCrypt;

import java.util.Date;


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
		User user = new User("gaoxieen2", "2", "gaoxieen@qq.com", "https://i.loli.net/2020/12/11/Gh9HoUW8qQcZKvV.jpg",
				new Date(), (byte) age, 15149039288L, "高谢恩");
		System.out.println(userService.updateUserSelective(user));
	}
	@Test
	public void passwordAdd() {
		System.out.println(BCrypt.hashpw("1",BCrypt.gensalt()));
	}
}
