package controller;

import model.Item;
import model.Pack;
import model.Packlist;
import org.springframework.web.client.RestTemplate;
import view.Menu;


public class PackController {

    private static final String MENU_ADD_ITEM = "Add item";
    private static final String MENU_SAVE_QUIT = "Save and quit";
    private static final String[] CREATE_PACK_MENU = {MENU_ADD_ITEM,MENU_SAVE_QUIT};

    public static Menu menu = new Menu(System.in,System.out);
    RestTemplate restTemplate = new RestTemplate();

    public Packlist createPack(){
        System.out.println("Creating a new Pack");
        Pack pack = new Pack();

        //TODO create new pack_id with user_id

        while(true){
            String choice = (String)menu.getChoiceFromOptions(CREATE_PACK_MENU);
            if(MENU_ADD_ITEM.equals(choice)){
                pack.addItem(Item.mapItem());
            }else{
                //only other option is quit
                break;
            }
        }
        //build entity and ship template

        Packlist packlist = new Packlist();
        packlist.setPack(pack);
        packlist.setUser(App.currentUser);

        packlist = restTemplate.postForObject(Entity.BASE_URL + "/createpack",
                Entity.createJSONEntity(packlist),Packlist.class);

        return packlist;
    }

}
