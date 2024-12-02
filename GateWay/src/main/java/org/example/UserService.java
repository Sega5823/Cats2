package org.example;

import entity.CatDTO;
import entity.Owner;
import org.example.UserException;
import org.example.UserRepository;
import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@ComponentScan("org.example.dao")
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private KafkaTemplate<String, User> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Integer> kafkaTemplateint;
    @Autowired
    private KafkaTemplate<String, List<Integer>> kafkaTemplateIntList;

    private final Map<Integer, CompletableFuture<List<CatDTO>>> completableFutureMap = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user != null){
            return user;
        }
        throw new UsernameNotFoundException("user is not found");
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void insertUser(User user) throws UserException {
        if (userRepository.findByUsername(user.getUsername()) != null){
            throw new UserException("username already busy");
        }
        userRepository.save(user);
    }

    @KafkaListener(topics = "responseFindAll", groupId = "catsgroupid")
    public void responseFindAll(Map keyCatDTOS) {
        var keyset = keyCatDTOS.keySet().toArray();
        var V = completableFutureMap.remove(Integer.valueOf((String) keyset[0]));
        if (V != null) {
            V.complete((List<CatDTO>) keyCatDTOS.get(keyset[0]));
        }
        //return null;
    }

    public CompletableFuture<List<CatDTO>> findCats(int id) {
        CompletableFuture<List<CatDTO>> listCompletableFuture = new CompletableFuture<>();
        completableFutureMap.put(id, listCompletableFuture);
        var r = kafkaTemplateint.send("findAll", id);
        return listCompletableFuture;
    }

    public void deleteCat(int id){
        kafkaTemplateint.send("deleteCat", id);
    }

    public void addFriend(int cat1, int cat2){
        List<Integer> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        kafkaTemplateIntList.send("addFriend", cats);
    }


    public void addOwner(User user, int owner_id, Date date) throws UserException {
//        Owner owner = ownerService.findOwnerByID(owner_id).get();
//        if (owner == null){
//            owner = ownerService.findOwnerByID(ownerService.insertOwner(new Owner(user.getUsername(), date))).get();
//        }
//        user.setOwner(owner);
//        owner.setUser(user);
//        ownerRepository.save(owner);
//        userRepository.save(user);
    }
}
