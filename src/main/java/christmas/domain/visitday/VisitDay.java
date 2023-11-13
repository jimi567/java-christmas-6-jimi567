package christmas.domain.visitday;

public interface VisitDay {
    void validate(int day);

    boolean isEventDay();

    boolean isWeekendDay();

    boolean isStarDay();

    boolean isBeforeDday();

    int calculateDaysUntilDday();
}
