package controller;

import model.User;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import view.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserController {

    //purpose of this class is to create / get Users
    //no credentials are required, authentication with JWT is a fucking nightmare

    private static RestTemplate template = new RestTemplate();

    //called with login option from menu, returns list of users, selection will be saved in user

    public static List<User> getUsersFromDb(){
        String sql = "select * from users";
        List<User> userList = Arrays.asList(template.getForObject(Entity.BASE_URL + "/getAllUsers",User[].class));
        return userList;
    }

    public static User selectUser(List<User> userList){
        User current = new User();
        System.out.println("Select user from list: ");
        for(User user : userList){
            System.out.println(user.getUser_id() + ": " + user.getUsername());
        }
        Scanner in = new Scanner(System.in);
        while(!in.hasNextInt()){
            System.out.println("must be a number:");
            in.next();
        }
        int userId = in.nextInt();
        boolean valid = false;
        while(!valid){
            for(User user : userList){
                if(user.getUser_id() == userId){
                    valid = true;
                    current.setUser_id(user.getUser_id());
                    current.setUsername(user.getUsername());
                }
            }
            //TODO this is still wonky with verification
            System.out.println("must be valid user: ");
            while(!in.hasNextInt()){
                in.next();
            }
            userId = in.nextInt();
        }
        return current;
    }


    //called with register, enter new user and push to db

    public static User registerNewUser(){
        //create new user in db and return to current user in app
        Scanner in = new Scanner(System.in);
        System.out.println("Enter new user name: ");
        String username = in.nextLine();
        User user = new User();
        user.setUsername(username);
        user = template.exchange(Entity.BASE_URL + "/createUser", HttpMethod.POST,
                Entity.createJSONEntity(user),User.class).getBody();
        //make sure user comes back with a user_id
        return user;
    }

}
