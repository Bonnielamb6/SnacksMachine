package presentation;

import domain.Snack;
import service.IServiceSnacks;
import service.ServiceSnacksFiles;
import service.ServiceSnacksList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnacksMachine {
    public static void main(String[] args) {
        snacksMachine();
    }

    public static void snacksMachine() {
        var out = false;
        var console = new Scanner(System.in);
        //Create object to obtain IServiceSnacks
        IServiceSnacks serviceSnacks = new ServiceSnacksFiles();

        //Products list for snacks
        List<Snack> products = new ArrayList<>();
        System.out.println("***  ServiceSnacksList Machine  ***");
        serviceSnacks.showSnacks();//Current snacks
        while (!out) {
            try {
                var op = showMenu(console);
                out = executeOptions(op, console, products, serviceSnacks);
            } catch (Exception e) {
                System.out.println("There was an error: " + e);
            } finally {
                System.out.println();
            }
        }
    }

    private static int showMenu(Scanner console) {
        System.out.println("""
                Menu:
                1.Buy snack
                2.Show ticket
                3.Add new snack
                4.Snacks inventory
                5.Exit
                Choose an option:\s""");
        //Read and return the option selected
        return Integer.parseInt(console.nextLine());
    }

    private static boolean executeOptions(int op, Scanner console,
                                          List<Snack> products, IServiceSnacks serviceSnacks) {
        var out = false;

        switch (op) {
            case 1 -> buySnack(console, products, serviceSnacks);
            case 2 -> showTicket(products);
            case 3 -> addSnack(console, serviceSnacks);
            case 4 -> serviceSnacks.showSnacks();
            case 5 -> out = true;
            default -> System.out.println("That is not a valid option: " + op);
        }

        return out;
    }

    private static void buySnack(Scanner console,
                                 List<Snack> products, IServiceSnacks serviceSnacks) {
        System.out.println("Wich snack do you want to buy (id)?: ");
        var idSnack = Integer.parseInt(console.nextLine());
        //validate that snack exists in the list
        var snackFound = false;
        for (var snack : serviceSnacks.getSnacks()) {
            if (idSnack == snack.getIdSnack()) {
                //Add snack to products list
                products.add(snack);
                System.out.println("Snack added: " + snack);
                snackFound = true;
                break;
            }
        }
        if (!snackFound) {
            System.out.println("Snack not found (id): " + idSnack);
        }
    }

    private static void showTicket(List<Snack> products) {
        var ticket = "*** Ticket ***";
        var total = 0.0;
        for (var product : products) {
            ticket += "\n\t-" + product.getName() + " - $" + product.getPrice();
            total += product.getPrice();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void addSnack(Scanner console, IServiceSnacks serviceSnacks) {
        System.out.println("Snack name: ");
        var name = console.nextLine();
        System.out.println("Snack price: ");
        var price = Double.parseDouble(console.nextLine());
        serviceSnacks.addSnack(new Snack(name, price));
        System.out.println("Your snack has been added");
        serviceSnacks.showSnacks();
    }
}
