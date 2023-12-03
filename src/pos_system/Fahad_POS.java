package pos_system;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author 220110341
 */
public class Fahad_POS {
    public static final String fileName = "inventory1.txt";
    
    public static void main(String[] args) {
       
        try {
            GroceryStore store = new GroceryStore(fileName);
            
        
        System.out.println("=======[ Welcome to Fahad's Point Of Sale System ]=======");
        boolean goBack;
        while (true) {
            int choice = printMenu();
            switch (choice) {
                case 1:
                    goBack = store.processSale();
                    while(goBack == false){
                        goBack = store.processSale();
                    }
                    break;
                case 2:
                     goBack = store.updateInventory();
                    while (goBack == false){
                        goBack = store.updateInventory();
                    }
                    break;
                case 3:
                    
                   goBack = store.generateSaleReport();
                   while(goBack == false){
                       goBack = store.generateSaleReport();
                   }
                    break;
                case 0:
                    System.out.println("Thank you.. have a nice day :)!");
                    System.exit(0);
            }
        }
        
         } catch (FileNotFoundException ex) {
            System.out.println("Cannot find inventory file!");
            System.exit(0);
        }
    }

    public static int printMenu() {
        int numMenuOptions = 3;

        System.out.println("- To start Sales process choose 	(1)");
        System.out.println("- To update & track Inventory choose 	(2)");
        System.out.println("- To generate sales Reports choose 	(3)");
        System.out.println("- To Exit				(0)");
        System.out.print(">>> ");

        Scanner input = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > numMenuOptions) {
            try {
            choice = input.nextInt();
            input.nextLine();
            if ((choice < 0 || choice > numMenuOptions)) {
                System.out.println("\nPlease enter a valid option from 1 to " 
                        + numMenuOptions + " or 0 to Exit\n");
                break;
            }
             } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a correct option (Numbers only)\n");
                break;
            }
        }
        return choice;
    }
}
