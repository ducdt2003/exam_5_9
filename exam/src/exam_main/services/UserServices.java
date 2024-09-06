
package exam_main.services;
import exam_main.entities.User;
import exam_main.views.LoginMenu;

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
        User user = new User(userName, email, passWord);
        users.add(user);
        return user;
    }

    public void inputUserName(Scanner sc, ArrayList<User> users) {
        while (true) {
            userName = sc.nextLine();
            if (findUserByUsername(userName, users) != null) {
                System.out.println("Tài khoản đã tồn tại! Hãy chọn tên tài khoản khác.");
            } else {
                System.out.println("Tài khoản hợp lệ! Tiếp tục nhập email.");
                break;
            }
        }
    }

    public void inputEmail(Scanner sc, ArrayList<User> users) {
        Pattern pattern = Pattern.compile("^[a-z0-9]+@[a-z]+.[a-z]{2,}$");
        while (true) {
            email = sc.nextLine();
            if (pattern.matcher(email).find()) {
                if (findUserByEmail(email, users) != null) {
                    System.out.println("Email này đã được đăng ký từ trước rồi! Nhập email khác.");
                } else {
                    System.out.println("Email hợp lệ! Tiếp tục nhập mật khẩu.");
                    break;
                }
            } else {
                System.out.println("Lỗi khi đặt tên email.");
                System.out.print("Nhập lại email đúng quy tắc ---@gmail.com ");
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

    public void checkLogin(Scanner sc,User user, ArrayList<User> users) {
        System.out.println("Nhập tài khoản của bạn ");
        userName = sc.nextLine();
        System.out.println("Nhập mật khẩu của bạn ");
        passWord = sc.nextLine();
        boolean userFound = false;
        while (true) {
            for (User user1 : users) {
                if (user1.getUsername().equals(userName) && user1.getPassword().equals(passWord)) {
                    System.out.println("Chào mừng --> " + userName + " <-- đã đăng nhập thành công");
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.selectLoginMenu(sc, user,users);
                    userFound = true;
                    break;
                }
            }
            if (!userFound) {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng!");
                LoginMenu loginMenu = new LoginMenu();
                loginMenu.selectPassWordMenu(sc,user, users);
            } else {
                break;
            }
        }
    }

    public void forgotPassword(Scanner sc, ArrayList<User> users) {
        do {
            System.out.print("Nhập email đã liên kết với tài khoản: ");
            String email1 = sc.nextLine();
            User user = findUserByEmail(email1, users);
            if (user != null) {
                System.out.print("Email chính xác! Mời bạn nhập mật khẩu mới: ");
                passWord = sc.nextLine();
                user.setPassword(passWord);
                System.out.println("Mật khẩu đã được cập nhật. Vui lòng đăng nhập lại.");
                checkLogin(sc,user, users);
                break;
            } else {
                System.out.println("Email chưa được liên kết với tài khoản nào! Vui lòng thử lại.");
            }
        } while (true);
    }

    // Đổi username
    public void changeUserName(Scanner sc, User user, ArrayList<User> users) {
        System.out.println("Mời bạn nhập username mới: ");
        String newUserName = sc.nextLine();
        if (findUserByUsername(newUserName, users) != null) {
            System.out.println("Tài khoản đã tồn tại! Vui lòng chọn tên tài khoản khác.");
        } else {
            user.setUsername(newUserName);
            System.out.println("Tài khoản đã được đổi thành công!");
        }
    }

    // Đổi email
    public void changeEmail(Scanner sc, User user, ArrayList<User> users) {
        System.out.println("Mời bạn nhập email mới: ");
        String newEmail = sc.nextLine();

        // Kiểm tra email đã tồn tại hay chưa
        if (findUserByEmail(newEmail, users) != null) {
            System.out.println("Email này đã được đăng ký! Vui lòng chọn email khác.");
        } else {
            user.setEmail(newEmail);
            System.out.println("Email đã được đổi thành công!");
        }
    }

    // Đổi mật khẩu
    public void changePassWord(Scanner sc, User user) {
        System.out.println("Mời bạn nhập mật khẩu mới: ");
        String newPassWord = sc.nextLine();
        user.setPassword(newPassWord);
        System.out.println("Mật khẩu đã được đổi thành công!");
    }

    // tìm kiếm username trong danh sách người dùng
    private User findUserByUsername(String userName, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    // tìm kiếm email trong danh sách người dùng
    private User findUserByEmail(String email, ArrayList<User> users) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}

