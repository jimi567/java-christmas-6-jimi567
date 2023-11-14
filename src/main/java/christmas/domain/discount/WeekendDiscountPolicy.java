package christmas.domain.discount;

import christmas.contant.Event;
import christmas.domain.customer.Customer;

public class WeekendDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Customer customer) {
        if (applicableEvent(customer)) {
            return customer.orderMenu().getQuantityOfMainMenu() * Event.WEEKEND_DISCOUNT_AMOUNT.get();
        }
        return 0;
    }

    @Override
    public boolean applicableEvent(Customer customer) {
        return customer.visitDate().isWeekendDay() && customer.orderMenu().getQuantityOfMainMenu() > 0;
    }

    @Override
    public String toString() {
        return "주말 할인: ";
    }
}
