package christmas.contant;

public enum Event {
    EVENT_APPLICABLE_AMOUNT(10_000),
    WEEKDAY_DISCOUNT_AMOUNT(2_023),
    WEEKEND_DISCOUNT_AMOUNT(2_023),
    SPECIAL_DISCOUNT_AMOUNT(1_000),
    GIFT_APPLICABLE_AMOUNT(120_000),
    D_DAY_DISCOUNT_START_AMOUNT(1_000),
    D_DAY_DISCOUNT_PLUS_AMOUNT(100);
    private final int number;

    Event(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }
}
