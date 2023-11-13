package christmas.domain.customer;

import static christmas.contant.Error.NOT_VALIDATE_ORDER_ERROR;
import static christmas.contant.Error.ORDER_ONLY_DRINK_ERROR;
import static christmas.contant.MenuBoard.AT_LEAST_ORDER_QUANTITY;
import static christmas.contant.MenuBoard.MAX_TOTAL_ORDER_QUANTITY;
import static christmas.contant.MenuBoard.getSellingMenus;

import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.Menu;
import java.util.HashMap;

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

}
