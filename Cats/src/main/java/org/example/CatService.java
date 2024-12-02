package org.example;

import entity.Cat;
import entity.CatDTO;
import entity.KeyCatDTOS;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ComponentScan("org.example")
public class CatService {
    @Autowired
    private CatRepository catRepository;
    @Autowired
    private KafkaTemplate<String, CatDTO> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Map<Integer, List<CatDTO>>> arrayListKafkaTemplate;

    @KafkaListener(topics = "findCat", groupId = "catsgroupid")
    public CatDTO findCatByID(int id){
        Cat cat = catRepository.findById(id).get();
        CatDTO catDTO = new CatDTO(cat.getName(), cat.getBreed(), cat.getColor(), cat.getDate(), cat.getCatID(), cat.getOwner_id());
        kafkaTemplate.send("findCat", catDTO);
        return catDTO;
    }

    @KafkaListener(topics = "insertCat", groupId = "catsgroupid")
    public void insertCat(Cat Cat){
        catRepository.save(Cat);
    }

    @KafkaListener(topics = "deleteCat", groupId = "catsgroupid")
    //@Transactional
    public void deleteCat(int id){
        var cat = catRepository.findById(id).get();
        catRepository.deleteById(id);
    }

    @KafkaListener(topics = "addFriend", groupId = "catsgroupid")
    @Transactional
    public void addFriend(List<Integer> cats){
        int catid = cats.get(0);
        int catid2 = cats.get(1);
        //Cat cat = catRepository.getById(catid);
        Cat cat = catRepository.findById(catid).get();
        Cat cat2 = catRepository.findById(catid2).get();
        cat.getFriends().add(cat2);
        cat2.getFriends().add(cat);
        catRepository.save(cat);
        //catRepository.save(cat2);
    }
    @KafkaListener(topics = "findAll", groupId = "catsgroupid")
    public List<CatDTO> findAll(int ownerId){
        List<CatDTO> cats = catRepository.findAll().stream().filter((x)-> x.getOwner_id() == ownerId)
                .map((cat)-> new CatDTO(cat.getName(), cat.getBreed(), cat.getColor(), cat.getDate(), cat.getCatID(), cat.getOwner_id())).toList();
//        ArrayList<CatDTO> cats = (ArrayList<CatDTO>) CatRepository.findAll().stream().filter((x)-> x.getOwner_id() == ownerId)
//                .map((cat)-> new CatDTO(cat.getName(), cat.getBreed(), cat.getColor(), cat.getDate(), cat.getCatID(), cat.getOwner_id())).toList();
        //arrayListKafkaTemplate.send()
        //arrayListKafkaTemplate.send("responseFindAll", String.valueOf(ownerId), cats);

        Map<Integer, List<CatDTO>> listMap = new HashMap<>();
        listMap.put(ownerId, cats);
        arrayListKafkaTemplate.send("responseFindAll", listMap);
        arrayListKafkaTemplate.flush();
        return cats;
    }



}
