package christmas.contant;

public enum Event {
    MIN_NUMBER_MENU(1),
    MAX_NUMBER_MENU(20),
    EVENT_APPLICABLE_AMOUNT(10_000),
    WEEKDAY_DISCOUNT_AMOUNT(2_023),
    WEEKEND_DISCOUNT_AMOUNT(2_023),
    SPECIAL_DISCOUNT_AMOUNT(1_000),
    GIFT_APPLICABLE_AMOUNT(120_000),
    D_DAY_DISCOUNT_START_AMOUNT(1_000),
    D_DAY_DISCOUNT_PLUS_AMOUNT(100);
    private int number;

    Event(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
