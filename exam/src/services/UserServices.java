
package services;

import entities.User;
import views.LoginMenu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserServices {
    private String userName;
    private String passWord;
    private String email;

   public User inputRegister(Scanner sc, ArrayList<User> users) {
       System.out.println("---Tạo Tài Khoản---");
       System.out.print("Nhập userName: ");
       inputUserName(sc, users);
       System.out.print("Nhập email: ");
       inputEmail(sc, users);
       System.out.print("Nhập passWord: ");
       inputPassWord(sc);
       User user = new User(userName, email,passWord);
       users.add(user);
       return user;
   }

    public void inputUserName(Scanner sc, ArrayList<User> users) {
        while (true) {
            userName = sc.nextLine();
            boolean usernameExists = false;
            for (User user : users) {
                if (user.getUsername().equals(userName)) {
                    usernameExists = true;
                    break;
                }
            }
            if (usernameExists) {
                System.out.println("Tài khoản đã tồn tại! Hãy chọn tên tài khoản khác.");
            } else {
                System.out.println("Tài khoản hợp lệ! Tiếp tục nhập email.");
                break;
            }
        }
    }


    public void inputEmail(Scanner sc, ArrayList<User> users) {
        Pattern pattern = Pattern.compile("^[a-z0-9]+@[a-z]+\\.[a-z]{2,}$");
        while (true) {
            email = sc.nextLine();
            boolean emailExists = false;
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    emailExists = true;
                    break;
                }
            }

            if (pattern.matcher(email).matches()) {
                if (emailExists) {
                    System.out.println("Email này đã được đăng ký từ trước rồi! Nhập email khác.");
                } else {
                    System.out.println("Email hợp lệ! Tiếp tục nhập mật khẩu.");
                    break;
                }
            } else {
                System.out.println("Lỗi khi đặt tên email.");
                System.out.print("Nhập lại email đúng quy tắc (ví dụ: example@gmail.com): ");
            }
        }
    }


    public void inputPassWord(Scanner sc) {
        Pattern pattern = Pattern.compile("^(.*[A-Z])([a-z0-9]){7,15}$");
        do {
            passWord = sc.nextLine();
            if (pattern.matcher(passWord).find()) {
                System.out.println("Đăng ký hoàn tất");
                break;
            } else {
                System.out.print("Mật khẩu phải từ 7 đến 15 ký tự và chữ cái đầu tiên viết hoa");
                System.out.println("\nNhập Lại: ");
            }
        } while (true);
    }


   public void displayRegisters(ArrayList<User> users) {
       System.out.println("Danh sách tài khoản đã đăng ký:");
       for (User user : users) {
           System.out.println("Username: " + user.getUsername() + ", Email: " + user.getEmail() + ", Password: " + user.getPassword());
       }
   }

    public void inputLogin(Scanner sc, ArrayList<User> users) {
        System.out.println("Nhập tài khoản của bạn ");
        userName = sc.nextLine();
        System.out.println("Nhập mật khẩu của bạn ");
        passWord = sc.nextLine();
        checkLogin(sc, users);
    }

    public void checkLogin(Scanner sc, ArrayList<User> users) {
        boolean userFound = false; // Biến để theo dõi liệu tài khoản có tồn tại không
        while (true) {
            for (User user : users) {
                if (user.getUsername().equals(userName) && user.getPassword().equals(passWord)) {
                    System.out.println("Chào mừng --> " + userName + " <-- đã đăng nhập thành công");
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.selectLoginMenu(sc, users);
                    userFound = true;
                    break;
                }
            }
            if (!userFound) {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng!");
                LoginMenu loginMenu = new LoginMenu();
                loginMenu.selectPassWordMenu(sc, users);
            } else {
                break;
            }
        }
    }
    public void forgotPassword(Scanner sc, ArrayList<User> users) {
        do {
            System.out.print("Nhập email đã liên kết với tài khoản: ");
            String email1 = sc.nextLine();
            for (User user: users) {
                if (user.getEmail().equals(email1)){
                    System.out.print("Email chính xác! Mời bạn nhập mật khẩu mới: ");
                    passWord = sc.nextLine();
                    user.setPassword(passWord);
                    System.out.println("Mật khẩu đã được cập nhật. Vui lòng đăng nhập lại.");
                    inputLogin(sc, users);
                    checkLogin(sc, users);
                    break;
                } else {
                    System.out.println("Email chưa được liên kết với tài khoản nào! Vui lòng thử lại.");
                }
            }

        } while (true);
    }

    public void changeUserName(Scanner sc, ArrayList<User> users) {
        do {
            System.out.print("Nhập Tài khoản cũ: ");
            String oldUserName = sc.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(oldUserName)) {
                    System.out.print("Nhập tài khoản mới: ");
                    String newUserName = sc.nextLine();
                    user.setUsername(newUserName);
                    System.out.println("Tài Khoản đã được đổi thành công");
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.selectDisplayMenu(sc, users);
                    break;
                } else {
                    System.out.println("Tài khoản này chưa tồn tại từ trước! Hãy nhập đúng tài khoản ban đầu");
                }
            }
        } while (true);
    }

    public void changeEmail(Scanner sc, ArrayList<User> users) {
        do {
            System.out.print("Nhập Email cũ: ");
            String oldEmail = sc.nextLine();
            for (User user : users) {
                if (user.getEmail().equals(oldEmail)) {
                    System.out.print("Nhập Email mới: ");
                    String newEmail = sc.nextLine();
                    user.setEmail(newEmail);
                    System.out.println("Email đã được đổi thành công");
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.selectDisplayMenu(sc, users);
                    break;
                } else {
                    System.out.println("Email này chưa tồn tại từ trước! Hãy nhập đúng tài khoản ban đầu");
                }
            }
        } while (true);
    }

    public void changePassWord(Scanner sc, ArrayList<User> users) {
        do {
            System.out.print("Nhập mật khẩu cũ: ");
            String oldPassWord = sc.nextLine();
            for (User user : users) {
                if (user.getPassword().equals(oldPassWord)) {
                    System.out.print("Nhập Mật khẩu mới: ");
                    String newPassWord= sc.nextLine();
                    user.setPassword(newPassWord);
                    System.out.println("Mật Khẩu đã được đổi thành công");
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.selectDisplayMenu(sc, users);
                    break;
                } else {
                    System.out.println("Mật Khẩu này chưa tồn tại từ trước! Hãy nhập đúng tài khoản ban đầu");
                }
            }
        } while (true);
    }
}
