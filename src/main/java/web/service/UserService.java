package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);
    void dell(Long deleteId);
    List<User> listUsers();
    User getForId(Long userId);
    User findByUsername(String username);
    void edit(Long Id, User user);
    UserDetails loadUserByUsername(String username);
}
