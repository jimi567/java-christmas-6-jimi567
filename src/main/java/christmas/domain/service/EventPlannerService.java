package christmas.domain.service;

import christmas.contant.EventBadge;
import christmas.domain.customer.Customer;
import christmas.domain.menu.Menu;

public interface EventPlannerService {

    boolean isEventTarget(Customer customer);

    EventBadge getEventBadge(Customer customer);

    int getTotalDiscountAmount(Customer customer);

    String getBenefitHistory(Customer customer);

    Menu getGiftMenu(Customer customer);

    int getDiscountedPayment(Customer customer);
}
