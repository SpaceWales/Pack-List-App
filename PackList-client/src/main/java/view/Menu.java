package view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;

    public Menu(InputStream input, OutputStream output){
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public void openingArt(){
        String art = "           (                 ,&&&.\n" +
                "            )                .,.&&\n" +
                "           (  (              \\=__/\n" +
                "               )             ,'-'.\n" +
                "         (    (  ,,      _.__|/ /|\n" +
                "          ) /\\ -((------((_|___/ |\n" +
                "        (  // | (`'      ((  `'--|\n" +
                "      _ -.;_/ \\\\--._      \\\\ \\-._/.\n" +
                "     (_;-// | \\ \\-'.\\    <_,\\_\\`--'|\n" +
                "     ( `.__ _  ___,')      <_,-'__,'\n" +
                "  `'(_ )_)(_)_)'";
        System.out.println(art);
    }

    public Object getChoiceFromOptions(Object[] options){
        Object choice = null;
        while(choice == null){
            displayMenu(options);
            choice = getChoiceInput(options);
        }
        out.println();
        return choice;
    }

    public void displayMenu(Object[] options){
        out.println();
        for(int i = 0;i<options.length;i++){
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + ">>> ");
        out.flush();
    }

    public Object getChoiceInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput.trim());
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }


}
