import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lilei.blog.po.UserFriend;
import pers.lilei.blog.service.UserFriendService;

import java.util.List;

/**
 * <h3>SSMBlog</h3>
 * <p>用户好友测试方法</p>
 *
 * @author : 李雷
 * @date : 2021-01-06 12:59
 **/
public class UserFriendServiceTest extends BaseTest{
    @Autowired
    UserFriendService userFriendService;
    @Test
    public void getAllFriendIdByUserId() {
        List<Long> friendIdList = userFriendService.getAllFriendIdByUserId(1L);
        friendIdList.forEach(temp-> System.out.println(temp));
    }
    @Test
    public void deleteFriend() {
        System.out.println(userFriendService.deleteFriend(1L, 3L));
    }
    @Test
    public void updateFriendNickName() {
        UserFriend userFriend = new UserFriend();
        userFriend.setUserId(1L);
        userFriend.setUserFriendId(3L);
        userFriend.setUserNickname("小王");
        System.out.println(userFriendService.updateFriendNickNameByUserIdAndUserFriendId(userFriend));
    }
}
