//import org.example.entity.Cat;
//import org.example.dao.CatDAO;
//import org.example.services.CatService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CatServiceTest {
//    @InjectMocks
//    CatService catService;
//    @Mock
//    CatDAO catDAO;
//    @Autowired
//    MockMvc mockMvc;
//
//    ArrayList<Cat> cats;
//
//    @BeforeEach
//    public void setup(){
//        cats = new ArrayList<>();
//        cats.add(new Cat("Fox", "siam", "Yellow", new Date(2010, 1, 1), 0));
//        cats.add(new Cat("Wolf", "siam", "Black", new Date(2020, 1, 1), 1));
//        cats.add(new Cat("Alex", "siam", "Red", new Date(2011, 1, 1), 3));
//
//
////        catService.insertCat(new Cat());).thenAnswer(x-> {
////            return cats.get(x.getArgument(0));
////        });
//    }
//
//    @Test
//    public void findCatByIDTest(){
//
//        when(catService.findCatByID(anyInt())).thenAnswer(x-> {
//            return cats.get(x.getArgument(0));
//        });
//        Assertions.assertEquals("Fox", catService.findCatByID(0).getName());
//    }
//    @Test
//    public void insertCatTest(){
//        Cat cat = new Cat("akl", "siam", "Black", new Date(2020, 1, 1), 1);
//        catService.insertCat(cat);
//        verify(catDAO).insertCat(cat);
//    }
//
//    @Test
//    public void deleteCatTest(){
//        Cat cat = new Cat("akl", "siam", "Black", new Date(2020, 1, 1), 1);
//        catService.deleteCat(cat.getCatID());
//        verify(catDAO).deleteCat(cat);
//    }
//
//    @Test
//    public void addFriendTest(){
//        Cat cat = new Cat("akl", "siam", "Black", new Date(2020, 1, 1), 1);
//        Cat cat2 = new Cat("Fox", "siam", "Yellow", new Date(2020, 1, 1), 1);
//        catService.addFriend(cat.getCatID(), cat2.getCatID());
//        verify(catDAO).addFriend(cat, cat2);
//    }
//}
