package org.example;

import entity.CatDTO;
import entity.Owner;
import org.example.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

//    public OwnerController() {
//        this.ownerService = new OwnerService();
//    }

    @GetMapping("/findOwnerByID")
    public ResponseEntity<Owner> findOwnerByID(@RequestParam(name = "id") int id) {
            //int id = 3;
            var Cats = ownerService.findOwnerCats(id);
            List<CatDTO> catDTOList;
            try {
                catDTOList = Cats.get(15, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
            Owner owner = ownerService.findOwnerByID(id).get();
            owner.setCatDTOSet(catDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(owner);
    }
    //http://localhost:8080/api/owners/findOwnerByID?id=1

    @PostMapping("/insertOwner")
    public void insertOwner(@RequestParam String name, @RequestParam Date date){
        Owner Owner = new Owner(name, date);
        ownerService.insertOwner(Owner);
    }

//    @DeleteMapping("/deleteOwner")
//    public void deleteOwner(@RequestParam int id){
//        ownerService.deleteOwner(this.findOwnerByID(id));
//    }
}
