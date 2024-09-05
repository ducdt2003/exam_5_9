import entities.User;
import views.LoginMenu;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.selectDisplayMenu(sc, users);
    }
}
