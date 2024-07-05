package run;

import entity.RoleName;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import util.IOFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//      String pass = "123456";
//      String hhPass1 = BCrypt.hashpw(pass,BCrypt.gensalt(5));
//      String hhPass2 = BCrypt.hashpw(pass,BCrypt.gensalt(5));
//        System.out.println(hhPass1);
//        System.out.println(hhPass2);
//
//        // so khớp
//        System.out.println(BCrypt.checkpw("123456",hhPass2));
        List<User> users = new ArrayList<>();
        User user = new User(1,"Nguyen văn a","a@gmail.com",BCrypt.hashpw("123456",BCrypt.gensalt(5)),"09756547656", LocalDate.of(1999,5,5),"HCM", RoleName.USER,false);
        User admin = new User(2,"admin","admin@gmail.com",BCrypt.hashpw("admin123",BCrypt.gensalt(5)),null,null,null, RoleName.ADMIN,false);
        users.add(user);
        users.add(admin);
        IOFile.writeToFile(IOFile.USER_PATH,users);

    }
}
