package service;

import domain.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServiceSnacksList implements IServiceSnacks {
    private static final List<Snack> snacks;

    //Static block initializer
    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("Chips", 5));
        snacks.add(new Snack("Coca-Cola", 2));
        snacks.add(new Snack("Sandwich", 10));
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public void showSnacks() {
        var inventorySnacks = "";
        for (var snack : snacks) {
            inventorySnacks += snack.toString() + "\n";
        }
        System.out.println("......ServiceSnacksList in the inventory......");
        System.out.println(inventorySnacks);
    }

    public List<Snack> getSnacks() {
        return snacks;
    }
}
