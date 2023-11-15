package christmas.domain.discount;

import static christmas.contant.MenuBoard.DESSERT;

import christmas.contant.Event;
import christmas.domain.customer.Customer;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    @Override
    public int getDiscountAmount(Customer customer) {
        if (applicableEvent(customer)) {
            return customer.orderMenu().getQuantityOfMenuByCategory(DESSERT) * Event.WEEKDAY_DISCOUNT_AMOUNT.get();
        }
        return 0;
    }

    @Override
    public boolean applicableEvent(Customer customer) {
        return customer.orderMenu().isMoreMinimumOrderAmountForEvent() && !customer.visitDate().isWeekendDay()
                && customer.orderMenu().getQuantityOfMenuByCategory(DESSERT) > 0;
    }

    @Override
    public String toString() {
        return "평일 할인: ";
    }
}
