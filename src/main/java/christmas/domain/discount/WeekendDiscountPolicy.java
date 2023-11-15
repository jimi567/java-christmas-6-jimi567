package christmas.domain.discount;

import static christmas.contant.MenuBoard.MAIN;

import christmas.contant.Event;
import christmas.domain.customer.Customer;

public class WeekendDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Customer customer) {
        if (applicableEvent(customer)) {
            return customer.orderMenu().getQuantityOfMenuByCategory(MAIN) * Event.WEEKEND_DISCOUNT_AMOUNT.get();
        }
        return 0;
    }

    @Override
    public boolean applicableEvent(Customer customer) {
        return customer.orderMenu().isMoreMinimumOrderAmountForEvent() && customer.visitDate().isWeekendDay()
                && customer.orderMenu().getQuantityOfMenuByCategory(MAIN) > 0;
    }

    @Override
    public String toString() {
        return "주말 할인: ";
    }
}
