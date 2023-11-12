package christmas.contant;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6_000, "에피타이저"),
    TAPAS("타파스", 5_500, "에피타이저"),
    CAESAR_SALAD("시저샐러드", 8_000, "에피타이저"),
    T_BORN_STAKE("티본 스테이크", 55_000, "메인"),
    RIBS("바베큐립", 54_000, "메인"),
    SEAFOOD_PASTA("해산물파스타", 35_000, "메인"),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, "메인"),
    CHOCO_CAKE("초코케이크", 15_000, "디저트"),
    ICE_CREAM("아이스크림", 5_000, "디저트"),
    ZERO_COKE("제로콜라", 3_000, "음료"),
    RED_WINE("레드와인", 60_000, "음료"),
    CHAMPAGNE("샴페인", 25_000, "음료");

    private final String name;
    private final int price;
    private final String category;

    Menu(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
