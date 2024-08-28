package service;

import domain.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceSnacksFiles implements IServiceSnacks {
    private final String FILE_NAME = "snacks.txt";
    //create snacks list
    private List<Snack> snacks = new ArrayList<>();

    public ServiceSnacksFiles() {
        var file = new File(FILE_NAME);
        var exists = false;
        try {
            exists = file.exists();
            if (exists) {
                this.snacks = getSnacksFile();
            } else {
                var out = new PrintWriter(new FileWriter(file));
                out.close();
                System.out.println("File created");
            }
        } catch (Exception e) {
            System.out.println("There was an error creating the file: " + e);
        }
        if (!exists)
            loadInitialSnacks();

    }

    private void loadInitialSnacks() {
        this.addSnack(new Snack("Chips", 5));
        this.addSnack(new Snack("Coca-Cola", 2));
        this.addSnack(new Snack("Sandwich", 10));
    }

    private List<Snack> getSnacksFile() {
        var snacks = new ArrayList<Snack>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (String line : lines) {
                String[] lineSnack = line.split(",");
                var idSnack = lineSnack[0];
                var nameSnack = lineSnack[1];
                var priceSnack = Double.parseDouble(lineSnack[2]);
                var snack = new Snack(nameSnack, priceSnack);
                snacks.add(snack);
            }
        } catch (Exception e) {
            System.out.println("There was an error reading the file: " + e);
            e.printStackTrace();
        }
        return snacks;
    }

    @Override
    public void addSnack(Snack snack) {
        //add a new snack to the list
        this.snacks.add(snack);
        //saving the snack to the file
        this.addSnackFile(snack);
    }

    private void addSnackFile(Snack snack) {
        boolean add = false;
        var file = new File(FILE_NAME);
        try {
            add = file.exists();
            var out = new PrintWriter(new FileWriter(file, add));
            out.println(snack.writeSnack());
            out.close();

        } catch (Exception e) {
            System.out.println("There was an error adding the snack: " + e);
        }
    }

    @Override
    public void showSnacks() {
        System.out.println("--- Snacks in the inventory ---");

        var inventorySnacks = "";
        for (var snack : this.snacks) {
            inventorySnacks += snack.toString() + "\n";
        }
        System.out.println(inventorySnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
