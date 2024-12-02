package org.example;

import entity.CatDTO;
import entity.KeyCatDTOS;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@ComponentScan("org.example.dao")
public class OwnerService {
    private final Map<Integer, CompletableFuture<List<CatDTO>>> completableFutureMap = new HashMap<>();
    @Autowired
    private OwnerRepository ownerDAO;
    @Autowired
    private KafkaTemplate<String, Owner> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Integer> kafkaTemplateint;
//    @Autowired
//    private KafkaTemplate<String, Owner> kafkaTemplate;

    //@KafkaListener(topics = "findOwner", groupId = "testgroupid")
    public Optional<Owner> findOwnerByID(int id) {
        Optional<Owner> owner = ownerDAO.findById(id);
        //kafkaTemplate.send("findOwner", owner.get());
        return ownerDAO.findById(id);
    }

    //@KafkaListener(topics = "insertOwner", groupId = "testgroupid")
    public int insertOwner(Owner owner) {
        ownerDAO.save(owner);
        return owner.getId();
    }

    //@KafkaListener(topics = "deleteOwner", groupId = "testgroupid")
    public void deleteOwner(Owner owner) {
        ownerDAO.delete(owner);
    }

    @KafkaListener(topics = "responseFindAll", groupId = "catsgroupid")
    //public void responseFindAll(int ownerId, List<CatDTO> cats){
    public void responseFindAll(Map keyCatDTOS) {
        var keyset = keyCatDTOS.keySet().toArray();
        var V = completableFutureMap.remove(Integer.valueOf((String) keyset[0]));
        if (V != null) {
            V.complete((List<CatDTO>) keyCatDTOS.get(keyset[0]));
        }
        //return null;
    }

    public CompletableFuture<List<CatDTO>> findOwnerCats(int id) {
        CompletableFuture<List<CatDTO>> listCompletableFuture = new CompletableFuture<>();
        completableFutureMap.put(id, listCompletableFuture);
        var r = kafkaTemplateint.send("findAll", id);
        return listCompletableFuture;
    }

}
