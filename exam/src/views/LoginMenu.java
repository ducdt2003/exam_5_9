package views;

import entities.User;
import services.UserServices;


import java.util.ArrayList;
import java.util.Scanner;

public class LoginMenu {
    public void displayMenu(Scanner sc){
        System.out.println("Hãy chọn 1 trong 2");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("Hãy lựa chọn chức năng");
    }

    public void selectDisplayMenu(Scanner sc, ArrayList<User> users){
        displayMenu(sc);
        int choice = Integer.parseInt(sc.nextLine());
        UserServices userRegisterServices = new UserServices();
        switch (choice){
            case 1:
                userRegisterServices.inputLogin(sc, users);
                break;
            case 2:
                User user = userRegisterServices.inputRegister(sc, users);
                userRegisterServices.displayRegisters(users);
                userRegisterServices.inputLogin(sc,users);
            default:
                System.out.println("Lỗi đăng nhập");
        }
    }
    public void inputpasswordMenu(Scanner sc){
        System.out.println("---Sai Mật Khẩu Rồi---");
        System.out.println("1. đăng nhập lại ");
        System.out.println("2. quên mật khẩu");
        System.out.println("0. thoát! sau nhớ nhập lại nak");
        System.out.println(" hãy chọn cái bạn muốn ");

    }

    public void selectPassWordMenu(Scanner sc, ArrayList<User> users){
        inputpasswordMenu(sc);
        int choice = Integer.parseInt(sc.nextLine());
        UserServices userRegisterServices = new UserServices();
        switch (choice){
            case 1:
                userRegisterServices.inputLogin(sc, users);
                break;
            case 2:
                userRegisterServices.forgotPassword(sc, users);
                break;
            case 0:
                System.out.println("Thoát chương trình!");
                System.exit(0);
            default:
                System.out.println("Lỗi đăng nhập");
        }
    }

    public void loginMenu(){
        System.out.println("---Bạn Muốn Làm Gì Tiếp Theo---");
        System.out.println("1 - Thay đổi username");
        System.out.println("2 - Thay đổi email");
        System.out.println("3 - Thay đổi mật khẩu");
        System.out.println("4 - Đăng xuất");
        System.out.println("0 - Thoát chương trình");
        System.out.print("Lựa chọn của bạn: ");
    }

    public void selectLoginMenu(Scanner sc, ArrayList<User> users){
        loginMenu();
        int choice = Integer.parseInt(sc.nextLine());
        UserServices userRegisterServices = new UserServices();
        switch (choice){
            case 1:
                userRegisterServices.changeUserName(sc, users);
                selectDisplayMenu(sc, users);
                break;
            case 2:
                userRegisterServices.changeEmail(sc, users);
                selectDisplayMenu(sc, users);
                break;
            case 3:
                userRegisterServices.changePassWord(sc, users);
                selectDisplayMenu(sc, users);
                break;
            case 4:
                System.out.println("---Đăng xuất thành công---");
                selectDisplayMenu(sc, users);
                break;
            case 0:
                System.out.println("---Thoát chương trình thành công!---");
                System.exit(0);
                break;
            default:
                System.out.println("Lỗi hệ thống");
        }
    }

}
