package christmas.domain.service;

import static christmas.contant.Event.EVENT_APPLICABLE_AMOUNT;
import static christmas.contant.EventBadge.getEventBadgeByTotalDiscountAmount;
import static christmas.contant.MenuBoard.CHAMPAGNE;
import static christmas.contant.ViewMessage.DECIMAL_FORMAT;
import static christmas.contant.ViewMessage.DISCOUNT_AMOUNT_FORMAT;
import static christmas.contant.ViewMessage.MENU_FORMAT;
import static christmas.contant.ViewMessage.NOTHING_MESSAGE;

import christmas.contant.EventBadge;
import christmas.domain.customer.Customer;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.GiftDiscountPolicy;
import java.text.DecimalFormat;
import java.util.List;

public class WootecoDecemberEventPlannerService implements EventPlannerService {

    private final List<DiscountPolicy> discountPolicies;

    public WootecoDecemberEventPlannerService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    @Override
    public boolean isEventTarget(Customer customer) {
        return customer.getTotalPayment() >= EVENT_APPLICABLE_AMOUNT.get();
    }

    @Override
    public EventBadge getEventBadge(Customer customer) {
        return getEventBadgeByTotalDiscountAmount(getTotalDiscountAmount(customer));
    }

    @Override
    public int getTotalDiscountAmount(Customer customer) {
        return discountPolicies.stream().mapToInt(discountPolicy -> discountPolicy.getDiscountAmount(customer)).sum();
    }

    @Override
    public String getBenefitHistory(Customer customer) {
        List<String> history = makeDiscountHistory(customer);
        if (!makeDiscountHistory(customer).isEmpty()) {
            return String.join("\n", history);
        }
        return NOTHING_MESSAGE.get();
    }

    @Override
    public String getGiftMenuHistory(Customer customer) {
        DiscountPolicy giftDiscountPolicy = discountPolicies.stream()
                .filter(GiftDiscountPolicy.class::isInstance).findFirst().get();

        if (giftDiscountPolicy.applicableEvent(customer)) {
            return String.format(MENU_FORMAT.get(), CHAMPAGNE.getMenu().name(), 1);
        }
        return NOTHING_MESSAGE.get();
    }

    @Override
    public int getDiscountedPayment(Customer customer) {
        DiscountPolicy giftDiscountPolicy = discountPolicies.stream()
                .filter(GiftDiscountPolicy.class::isInstance).findFirst().get();

        return customer.getTotalPayment() - getTotalDiscountAmount(customer) + giftDiscountPolicy.getDiscountAmount(
                customer);
    }

    //must 리팩토링
    private List<String> makeDiscountHistory(Customer customer) {
        return discountPolicies.stream()
                .filter(discountPolicy -> discountPolicy.applicableEvent(customer))
                .map(discountPolicy -> formatDiscountHistory(discountPolicy, customer))
                .toList();
    }

    private String formatDiscountHistory(DiscountPolicy discountPolicy, Customer customer) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT.get());
        return discountPolicy + String.format(DISCOUNT_AMOUNT_FORMAT.get(),
                decimalFormat.format(discountPolicy.getDiscountAmount(customer)));
    }
}
