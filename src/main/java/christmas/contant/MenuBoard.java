package christmas.contant;

import christmas.domain.menu.Menu;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum MenuBoard {
    MUSHROOM_SOUP(new Menu("양송이수프"), 6_000, "애피타이저"),
    TAPAS(new Menu("타파스"), 5_500, "애피타이저"),
    CAESAR_SALAD(new Menu("시저샐러드"), 8_000, "애피타이저"),
    T_BORN_STAKE(new Menu("티본스테이크"), 55_000, "메인"),
    RIBS(new Menu("바비큐립"), 54_000, "메인"),
    SEAFOOD_PASTA(new Menu("해산물파스타"), 35_000, "메인"),
    CHRISTMAS_PASTA(new Menu("크리스마스파스타"), 25_000, "메인"),
    CHOCO_CAKE(new Menu("초코케이크"), 15_000, "디저트"),
    ICE_CREAM(new Menu("아이스크림"), 5_000, "디저트"),
    ZERO_COKE(new Menu("제로콜라"), 3_000, "음료"),
    RED_WINE(new Menu("레드와인"), 60_000, "음료"),
    CHAMPAGNE(new Menu("샴페인"), 25_000, "음료");

    public static final int AT_LEAST_ORDER_QUANTITY = 1;
    public static final int MAX_TOTAL_ORDER_QUANTITY = 20;
    public static final String APPETIZER = "애피타이저";
    public static final String MAIN = "메인";
    public static final String DESSERT = "디저트";
    public static final String DRINK = "음료";

    private final Menu menu;
    private final int price;
    private final String category;

    MenuBoard(Menu menu, int price, String category) {
        this.menu = menu;
        this.price = price;
        this.category = category;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public static int getPriceByMenu(Menu menu) {
        return Arrays.stream(values()).filter(menuBoard -> menuBoard.getMenu().equals(menu)).findFirst().get()
                .getPrice();
    }

    public static String getCategoryByMenu(Menu menu) {
        return Arrays.stream(values()).filter(menuBoard -> menuBoard.getMenu().equals(menu)).findFirst().get()
                .getCategory();
    }

    public static Set<Menu> getSellingMenus() {
        return Arrays.stream(MenuBoard.values()).map(MenuBoard::getMenu).collect(Collectors.toSet());
    }
}
