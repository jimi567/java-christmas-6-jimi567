package christmas.domain.service;

import christmas.contant.EventBadge;
import christmas.domain.customer.Customer;

public interface EventPlannerService {


    EventBadge getEventBadge(Customer customer);

    int getTotalDiscountAmount(Customer customer);

    String getBenefitHistory(Customer customer);

    String getGiftMenuHistory(Customer customer);

    int getDiscountedPayment(Customer customer);
}
