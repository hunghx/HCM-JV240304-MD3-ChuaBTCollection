package run;

import business.AuthBusiness;
import business.IAuthDesign;
import entity.RoleName;
import entity.User;
import exception.UsernameAndPasswordException;
import util.IOFile;
import util.InputMethods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AuthenticationMenu {
    private static final IAuthDesign authDesign = new AuthBusiness();
    public static void main(String[] args) {
        while (true) {
            System.out.println("----------------------H-Store-------------------");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng kí");
            System.out.println("3. Thoát");
            System.out.println("----- Nhập lựa chọn -------");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    login();
                    // đăng nhập
                    break;
                case 2:
                    register();
                    // đăng kí
                    break;
                case 3:
                    // thoát
                    System.out.println("Tạm biệt");
                    break;
                default:
                    System.err.println("ko đúng lựa chọn");
            }
            if (choice==3){
                break;
            }
        }
    }
    private static void login(){
        System.out.println("=================Đăng nhập=================");
        System.out.println("Nhập username/ email :");
        String username = InputMethods.getString();
        System.out.println("Nhập password");
        String password = InputMethods.getString();
        try{
            User userLogin = authDesign.signIn(username,password);
            // luu thoong tin nguowif dungf vao file
            IOFile.writeUserLogin(userLogin);
            // xet quyen ngui dung
            if (userLogin.getRoleName().equals(RoleName.ADMIN)){
                menuAdmin();
            }else if (userLogin.getRoleName().equals(RoleName.USER)){
                // kiểm tra tài khoản có bị khóa ko
                if (userLogin.isBlocked()){
                    System.err.println("tai khoan cua bna da bi khoa, vui lòng liên hệ 0989376756");
                }else {
                    menuUser();
                }
            }
        }catch (UsernameAndPasswordException e){
            System.err.println(e.getMessage());
            // hỏi người ta có đăng nhập lại hay ko
        }
    }
    private static  void register(){
        System.out.println("------------------Register----------------");
        User user = new User();
        System.out.println("Nhập Tên ngươi dùng");
        user.setFullName(InputMethods.getString());
        System.out.println("Nhập email đăng nhập");
        user.setEmail(InputMethods.getString());
        System.out.println("Nhập password");
        user.setPassword(InputMethods.getString());
        System.out.println("Nhập số điện thoại ");
        user.setPhone(InputMethods.getString());
        System.out.println("Nhập điịa chỉ");
        user.setAddress(InputMethods.getString());
        System.out.println("Nhập ngày sinh (dd/MM/yyyy)");
        user.setBirthday(LocalDate.parse(InputMethods.getString(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        authDesign.signUp(user);
        System.out.println("Đăng kí thành công");
        // chuyển hug đến đăng nhập
        login();
    }

    private static void menuAdmin(){
        System.out.println("chào mừng bạn đến trang admin");
        InputMethods.pressAnyKey();
    }
    private static void menuUser(){
        System.out.println("chào mừng đê cửa haàng để mua sắm");
        InputMethods.pressAnyKey();
    }
}
