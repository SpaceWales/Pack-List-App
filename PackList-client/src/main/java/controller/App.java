package controller;

import model.User;
import view.Menu;

import java.util.List;

public class App {

    private static final String MENU_LOGIN = "Login";
    private static final String MENU_REGISTER = "Register";
    private static final String MENU_EXIT = "Exit";
    private static final String MENU_PACKLIST = "PackList";
    private static final String MENU_TRIP = "Trips";
    private static final String PACKLIST_CREATE = "Create PackList";
    private static final String PACKLIST_VIEW = "View PackList";
    private static final String PACKLIST_EDIT = "Edit PackList";
    private static final String PACKLIST_DELETE = "Delete PackList";
    private static final String TRIP_CREATE = "Create Trip";
    private static final String TRIP_VIEW = "View Trip";
    private static final String TRIP_EDIT = "Edit Trip";
    private static final String TRIP_DELETE = "Delete Trip";
    private static final String[] LOGIN_MENU_OPTIONS = {MENU_LOGIN,MENU_REGISTER,MENU_EXIT};
    private static final String[] MAIN_MENU_OPTIONS = {MENU_PACKLIST,MENU_TRIP,MENU_EXIT};
    private static final String[] PACKLIST_MENU_OPTIONS = {PACKLIST_CREATE,PACKLIST_VIEW,PACKLIST_EDIT,PACKLIST_DELETE,MENU_EXIT};
    private static final String[] TRIP_MENU_OPTIONS = {TRIP_CREATE,TRIP_VIEW,TRIP_EDIT,TRIP_DELETE,MENU_EXIT};

    public static Menu menu = new Menu(System.in,System.out);
    public static PackController packController = new PackController();
    public static User currentUser;

    public static void main(String[] args) {
        run();
    }

    public App(){
        //constructor for authentication
    }

    public static void run(){
        menu.openingArt();
        registerAndLogin();
        mainMenu();

    }

    private static void registerAndLogin(){
        //while unauthenticated present login options
        //TODO set up authentication, just bypass for now
        //while will be changed to !authenticated
        while(true) {
            String choice = (String) menu.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
            if (MENU_LOGIN.equals(choice)) {
                //TODO figure out verification
                List<User> userList = UserController.getUsersFromDb();
                currentUser = UserController.selectUser(userList);
                System.out.println(currentUser.getUsername() + " logged in");
                break;
            } else if (MENU_REGISTER.equals(choice)) {
                //TODO figure out verification
                currentUser = UserController.registerNewUser();
                System.out.println(currentUser.getUser_id() + " " +
                        currentUser.getUsername() + " registered");
                break;
            }else {
                exitProgram();
            }
        }
    }

    private static void mainMenu(){
        while(true){
            String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if(MENU_PACKLIST.equals(choice)){
                //change to packlist submenu
                packListMenu();
            }else if(MENU_TRIP.equals(choice)){
                //change to trip submenu
                tripMenu();
            }else {
                exitProgram();
            }
        }
    }

    private static void packListMenu(){
        while(true){
            String choice = (String)menu.getChoiceFromOptions(PACKLIST_MENU_OPTIONS);
            if(PACKLIST_CREATE.equals(choice)){
                //todo
                packController.createPack();
            }else if(PACKLIST_VIEW.equals(choice)){
                //todo
            }else if(PACKLIST_EDIT.equals(choice)){
                //todo
            }else if(PACKLIST_DELETE.equals(choice)){
                //todo
            }else{
                exitProgram();
            }
        }
    }

    private static void tripMenu(){
        while(true){
            String choice = (String)menu.getChoiceFromOptions(TRIP_MENU_OPTIONS);
            if(TRIP_CREATE.equals(choice)){
                //todo
            }else if(TRIP_VIEW.equals(choice)){
                //todo
            }else if(TRIP_EDIT.equals(choice)){
                //todo
            }else if(TRIP_DELETE.equals(choice)){
                //todo
            }else{
                exitProgram();
            }
        }
    }

    private static void exitProgram(){
        System.exit(0);
    }
}
