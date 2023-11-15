package christmas.domain.discount;

import static christmas.contant.Calender.CHRISTMAS_DATE;
import static christmas.contant.Event.D_DAY_DISCOUNT_PLUS_AMOUNT;
import static christmas.contant.Event.D_DAY_DISCOUNT_START_AMOUNT;

import christmas.domain.customer.Customer;

public class DdayDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Customer customer) {
        if (applicableEvent(customer)) {
            return D_DAY_DISCOUNT_START_AMOUNT.get()
                    + (CHRISTMAS_DATE - customer.visitDate().calculateDaysUntilDday() - 1)
                    * D_DAY_DISCOUNT_PLUS_AMOUNT.get();
        }
        return 0;
    }

    @Override
    public boolean applicableEvent(Customer customer) {
        return customer.orderMenu().isMoreMinimumOrderAmountForEvent() && customer.visitDate().isBeforeDday();
    }

    @Override
    public String toString() {
        return "크리스마스 디데이 할인: ";
    }
}
