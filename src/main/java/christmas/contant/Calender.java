package christmas.contant;

public enum Calender {
    EVENT_START_DAY(1),
    EVENT_END_DAY(31),
    D_DAY_EVENT_START_DAY(1),
    D_DAY_EVENT_END_DAY(25),
    STAR_DAY_FIRST(1),
    STAR_DAY_CYCLE(7),
    CHRISTMAS_DAY(25);

    private final int day;

    Calender(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
