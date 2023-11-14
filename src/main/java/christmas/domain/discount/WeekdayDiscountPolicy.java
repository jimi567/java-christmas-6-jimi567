package christmas.domain.discount;

import christmas.contant.Event;
import christmas.domain.customer.Customer;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    @Override
    public int getDiscountAmount(Customer customer) {
        if (applicableEvent(customer)) {
            return customer.orderMenu().getQuantityOfDessertMenu() * Event.WEEKDAY_DISCOUNT_AMOUNT.get();
        }
        return 0;
    }

    @Override
    public boolean applicableEvent(Customer customer) {
        return !customer.visitDay().isWeekendDay();
    }
}
