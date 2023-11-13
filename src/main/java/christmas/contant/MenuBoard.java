package christmas.contant;

import christmas.domain.menu.AppetizerMenu;
import christmas.domain.menu.DessertMenu;
import christmas.domain.menu.DrinkMenu;
import christmas.domain.menu.MainMenu;
import christmas.domain.menu.Menu;
import java.util.Arrays;
import java.util.List;

public enum MenuBoard {

    MUSHROOM_SOUP(new AppetizerMenu("양송이수프"), 6_000),
    TAPAS(new AppetizerMenu("타파스"), 5_500),
    CAESAR_SALAD(new AppetizerMenu("시저샐러드"), 8_000),
    T_BORN_STAKE(new MainMenu("티본스테이크"), 55_000),
    RIBS(new MainMenu("바비큐립"), 54_000),
    SEAFOOD_PASTA(new MainMenu("해산물파스타"), 35_000),
    CHRISTMAS_PASTA(new MainMenu("크리스마스파스타"), 25_000),
    CHOCO_CAKE(new DessertMenu("초코케이크"), 15_000),
    ICE_CREAM(new DessertMenu("아이스크림"), 5_000),
    ZERO_COKE(new DrinkMenu("제로콜라"), 3_000),
    RED_WINE(new DrinkMenu("레드와인"), 60_000),
    CHAMPAGNE(new DrinkMenu("샴페인"), 25_000);

    public static final int AT_LEAST_ORDER_QUANTITY = 1;
    public static final int MAX_TOTAL_ORDER_QUANTITY = 20;

    private final Menu menu;
    private final int price;

    MenuBoard(Menu menu, int price) {
        this.menu = menu;
        this.price = price;
    }

    public Menu get() {
        return menu;
    }

    public static List<Menu> getSellingMenus() {
        return Arrays.stream(MenuBoard.values()).map(MenuBoard::get).toList();
    }
}
