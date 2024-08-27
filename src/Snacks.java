import java.util.ArrayList;
import java.util.List;

public class Snacks {
    private static final List<Snack> snacks;

    //Static block initializer
    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Chips",5));
        snacks.add(new Snack("Coca-Cola",2));
        snacks.add(new Snack("Sandwich",10));
    }

    public static void addSnack(Snack snack){
        snacks.add(snack);
    }

    public static void showSnacks(){
        var inventorySnacks = "";
        for(var snack:snacks){
            inventorySnacks += snack.toString()+"\n";
        }
        System.out.println("......Snacks in the inventory......");
        System.out.println(inventorySnacks);
    }

    public static List<Snack> getSnacks(){
        return snacks;
    }
}
