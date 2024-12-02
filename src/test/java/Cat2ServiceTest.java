//import org.example.SpringMain;
//import org.example.dao.CatRepository;
//import org.example.entity.Cat;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.http.HttpRequest;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.util.Assert;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
////import java.util.ArrayList;
////import java.util.Date;
//
//@SpringBootTest(classes = SpringMain.class)
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.properties")
////@ActiveProfiles(profiles = "test")
////@ContextConfiguration
//public class Cat2ServiceTest {
//
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    CatRepository catRepository;
//
//    @Test
//    public void findCatByIDTest() throws Exception {
//
//            mockMvc.perform(get("/api/cats/getCatByID")
//                    .param("id", "2")
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.name").value("Alex"))
//                    .andExpect(jsonPath("$.breed").value("Lop"));
//
//    }
//
//    @Test
//    public void insertCatTest() throws Exception {
//
//        MvcResult mvcResult = mockMvc.perform(post("/api/cats/insertCat")
//                .param("name", "akl")
//                .param("breed", "siam")
//                .param("color", "Black")
//                .param("date",
//                        new SimpleDateFormat("yyyy/MM/dd").format(new Date(1, 1, 1)))
//                .param("owner_id", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//        Assertions.assertEquals("akl",
//                catRepository.findById(Integer.valueOf(mvcResult.getResponse().getContentAsString()))
//                        .get().getName());
//    }
//
////    @Test
////    public void deleteCatTest(){
////        Cat cat = new Cat("akl", "siam", "Black", new Date(2020, 1, 1), 1);
////        catService.deleteCat(cat);
////        verify(catDAO).deleteCat(cat);
////    }
////
////    @Test
////    public void addFriendTest(){
////        Cat cat = new Cat("akl", "siam", "Black", new Date(2020, 1, 1), 1);
////        Cat cat2 = new Cat("Fox", "siam", "Yellow", new Date(2020, 1, 1), 1);
////        catService.addFriend(cat, cat2);
////        verify(catDAO).addFriend(cat, cat2);
////    }
//}
