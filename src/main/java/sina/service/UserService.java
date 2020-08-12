package sina.service;

import sina.domain.User;

import java.util.List;

public interface UserService {

    boolean register(User user);


    public List<User> findAll();


    User login(String telephone, String password);

    String updateIntroduce(String introduce,int uid);

    User findDetailOne(int uid);

    String updateUsername(String username, int uid);
}
