package christmas.domain.customer;

import static christmas.contant.Error.NOT_VALIDATE_ORDER_ERROR;
import static christmas.contant.Error.ORDER_ONLY_DRINK_ERROR;
import static christmas.contant.MenuBoard.AT_LEAST_ORDER_QUANTITY;
import static christmas.contant.MenuBoard.MAX_TOTAL_ORDER_QUANTITY;
import static christmas.contant.MenuBoard.getPriceByMenu;
import static christmas.contant.MenuBoard.getSellingMenus;
import static christmas.contant.ViewMessage.MENU_FORMAT;

import christmas.domain.menu.AppetizerMenu;
import christmas.domain.menu.DessertMenu;
import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.MainMenu;
import christmas.domain.menu.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderMenu {

    private final HashMap<Menu, Integer> orderMenu;

    public OrderMenu(HashMap<Menu, Integer> orderMenu) {
        validate(orderMenu);
        this.orderMenu = orderMenu;
    }

    private void validate(HashMap<Menu, Integer> orderMenu) {
        if (!isExistMenuBoard(orderMenu)) {
            NOT_VALIDATE_ORDER_ERROR.throwError();
        } else if (!isOrderingAtLeastOrderCount(orderMenu)) {
            NOT_VALIDATE_ORDER_ERROR.throwError();
        } else if (!isWithinMaxToTalOrderCount(orderMenu)) {
            NOT_VALIDATE_ORDER_ERROR.throwError();
        } else if (isOnlyOrderDrink(orderMenu)) {
            ORDER_ONLY_DRINK_ERROR.throwError();
        }
    }

    private boolean isExistMenuBoard(HashMap<Menu, Integer> orderMenu) {
        return getSellingMenus().containsAll(orderMenu.keySet());
    }

    private boolean isOrderingAtLeastOrderCount(HashMap<Menu, Integer> orderMenu) {
        return orderMenu.values().stream().allMatch(count -> count >= AT_LEAST_ORDER_QUANTITY);
    }

    private boolean isWithinMaxToTalOrderCount(HashMap<Menu, Integer> orderMenu) {
        return orderMenu.values().stream().mapToInt(Integer::intValue).sum() <= MAX_TOTAL_ORDER_QUANTITY;
    }

    private boolean isOnlyOrderDrink(HashMap<Menu, Integer> orderMenu) {
        return orderMenu.keySet().stream().allMatch(menu -> menu instanceof DrinkMenu);
    }

    public int getTotalAmount() {
        return orderMenu.entrySet()
                .stream()
                .mapToInt(entry -> getPriceByMenu(entry.getKey()) * entry.getValue())
                .sum();
    }

    public int getCountDessertMenu() {
        return orderMenu.keySet().stream()
                .filter(menu -> menu instanceof DessertMenu)
                .mapToInt(orderMenu::get)
                .sum();
    }

    public int getCountAppetizerMenu() {
        return orderMenu.keySet().stream()
                .filter(menu -> menu instanceof AppetizerMenu)
                .mapToInt(orderMenu::get)
                .sum();
    }

    public int getCountMainMenu() {
        return orderMenu.keySet().stream()
                .filter(menu -> menu instanceof MainMenu)
                .mapToInt(orderMenu::get)
                .sum();
    }

    public int getCountDrinkMenu() {
        return orderMenu.keySet().stream()
                .filter(menu -> menu instanceof DrinkMenu)
                .mapToInt(orderMenu::get)
                .sum();
    }

    @Override
    public String toString() {
        List<String> orderMenuInformation = new ArrayList<>();
        orderMenu.forEach(
                (menu, count) -> orderMenuInformation.add(String.format(MENU_FORMAT.get(), menu.getName(), count)));
        return String.join("\n", orderMenuInformation);
    }

}
