import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    // This Code will be run before each of the unit test case
    @BeforeEach
    public void setupRestaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    // Test if the method isRestaurantOpen() returns true for the simulated current time
    // in the range of the restaurant open times.
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant spyRestaurant = Mockito.spy(restaurant);

        //Mock getCurrentTime to return restaurant open times for unit testing.
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:00"));
        assertTrue(spyRestaurant.isRestaurantOpen());

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:01"));
        assertTrue(spyRestaurant.isRestaurantOpen());

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("21:59:59"));
        assertTrue(spyRestaurant.isRestaurantOpen());

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("14:00:00"));
        assertTrue(spyRestaurant.isRestaurantOpen());
        // spyRestaurant.isRestaurantOpen() returns true in this case
        //WRITE UNIT TEST CASE HERE
    }

    // Test if the method isRestaurantOpen() returns false for the simulated current time
    // in the range of restaurant closed times.
    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        //Mock getCurrentTime to return restaurant closed times for unit testing.
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:29:59"));
        assertFalse(spyRestaurant.isRestaurantOpen());

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:00"));
        assertFalse(spyRestaurant.isRestaurantOpen());

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:01"));
        assertFalse(spyRestaurant.isRestaurantOpen());

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("23:30:00"));
        assertFalse(spyRestaurant.isRestaurantOpen());
        // spyRestaurant.isRestaurantOpen() returns false in this case
        //WRITE UNIT TEST CASE HERE

    }

    @Test
    public void cumulative_sum_of_menu_items_with_no_menu_items_should_give_0() {
        List<String> listOfMenuItems = new ArrayList<String>();
        assertEquals(0, restaurant.cumulativeSumOfMenuItems(listOfMenuItems));
    }

    @Test
    public void cumulative_sum_of_menu_items_with_2_menu_items_with_rates_119_and_269_should_give_388() {
        List<String> listOfMenuItems = Arrays.asList( "Sweet corn soup", "Vegetable lasagne");
        assertEquals(388, restaurant.cumulativeSumOfMenuItems(listOfMenuItems));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}