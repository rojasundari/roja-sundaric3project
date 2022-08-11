import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        // Checks if the user checking time falls in restaurant open times range
        // and returns a boolean
        if (getCurrentTime().isAfter(this.openingTime) && getCurrentTime().isBefore(this.closingTime))
            return true;
        else if (getCurrentTime().equals(this.openingTime))
            return true;
        else if (getCurrentTime().isBefore(this.openingTime) || getCurrentTime().isAfter(this.closingTime))
            return false;
        else // this will take care of the condition  (getCurrentTime().equals(this.closingTime))
            return false;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        // Returns the list of items
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    // Calculate the cumulative sum of the value of all the menu items selected by the user
    // Returns the cumulative order value, given the name of the items in a List format.
    public int cumulativeSumOfMenuItems(List<String> listOfMenuItems) {
        int cumulativeSum = 0;
        for(String menuItemName : listOfMenuItems) {
            cumulativeSum += findItemByName(menuItemName).getPrice();
        }
        return cumulativeSum;
    }
}
