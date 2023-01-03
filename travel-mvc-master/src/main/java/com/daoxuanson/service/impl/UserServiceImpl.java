//package com.daoxuanson.service.impl;
//
//import com.daoxuanson.entity.Role;
//import com.daoxuanson.entity.User;
//import com.daoxuanson.model.request.UserRequest;
//import com.daoxuanson.repository.RoleRepository;
//import com.daoxuanson.repository.UserRepository;
//import com.daoxuanson.service.UserService;
//import com.daoxuanson.util.SecurityUtil;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class UserServiceImpl implements UserService {
//    private UserRepository userRepository;
//    private RoleRepository roleRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    public void insert(UserRequest userRequest) {
//        User user = new User();
//        BeanUtils.copyProperties(userRequest, user);
//        Set<Role> roles = new HashSet<Role>();
//        for (Long id : userRequest.getIds()) {
//            Role role = roleRepository.findOne(id);
//            roles.add(role);
//        }
//        user.setRoles(roles);
//        user.setCreatedDate(new Date());
//        user.setCreatedBy(SecurityUtil.getUserName());
//        userRepository.save(user);
//
//    }
//
//    @Override
//    public void update(UserRequest userRequest) {
//        User user = new User();
//        BeanUtils.copyProperties(userRequest, user);
//        user.setUserName(userRepository.findUserNameById(userRequest.getId()).getUserName());
//        user.setCreatedDate(userRepository.findUserNameById(userRequest.getId()).getCreatedDate());
//        Set<Role> roles = new HashSet<>();
//        for (Long id : userRequest.getIds()) {
//            Role role = roleRepository.findOne(id);
//            roles.add(role);
//        }
//        user.setRoles(roles);
//        user.setModifiedDate(new Date());
//        user.setModifiedBy(SecurityUtil.getUserName());
//        userRepository.save(user);
//    }
//
//    @Override
//    public void delete(Long id) {
//        User user = userRepository.findOne(id);
//        for (Role role : user.getRoles()) {
//            role.getUsers().remove(user);
//        }
//        userRepository.delete(id);
//    }
//
//    @Override
//    public User findUserByUserNameAndPassword(String userName, String password) {
//        return userRepository.findUserByUserNameAndPassword(userName,password);
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User findUserById(Long id) {
//        return userRepository.findOne(id);
//    }
//}
