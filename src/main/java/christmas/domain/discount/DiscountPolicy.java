package christmas.domain.discount;

import christmas.domain.customer.Customer;

public interface DiscountPolicy {

    int getDiscountAmount(Customer customer);

    boolean applicableEvent(Customer customer);
}
