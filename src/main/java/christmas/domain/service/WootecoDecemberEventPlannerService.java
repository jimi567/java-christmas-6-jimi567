package christmas.domain.service;

import static christmas.contant.Event.EVENT_APPLICABLE_AMOUNT;
import static christmas.contant.EventBadge.getEventBadgeByTotalPayment;
import static christmas.contant.MenuBoard.CHAMPAGNE;
import static christmas.contant.ViewMessage.DECIMAL_FORMAT;
import static christmas.contant.ViewMessage.DISCOUNT_AMOUNT_FORMAT;
import static christmas.contant.ViewMessage.MENU_FORMAT;
import static christmas.contant.ViewMessage.NOTHING_MESSAGE;

import christmas.contant.EventBadge;
import christmas.contant.ViewMessage;
import christmas.domain.customer.Customer;
import christmas.domain.discount.DdayDiscountPolicy;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.GiftDiscountPolicy;
import christmas.domain.discount.SpecialDiscountPolicy;
import christmas.domain.discount.WeekdayDiscountPolicy;
import christmas.domain.discount.WeekendDiscountPolicy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WootecoDecemberEventPlannerService implements EventPlannerService {

    private final DiscountPolicy giftDiscountPolicy = new GiftDiscountPolicy();
    private final DiscountPolicy dDayDiscountPolicy = new DdayDiscountPolicy();
    private final DiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
    private final DiscountPolicy weekdayDiscountPolicy = new WeekdayDiscountPolicy();
    private final DiscountPolicy weekendDiscountPolicy = new WeekendDiscountPolicy();

    @Override
    public boolean isEventTarget(Customer customer) {
        return customer.getTotalPayment() >= EVENT_APPLICABLE_AMOUNT.get();
    }

    @Override
    public EventBadge getEventBadge(Customer customer) {
        return getEventBadgeByTotalPayment(customer.getTotalPayment());
    }

    @Override
    public int getTotalDiscountAmount(Customer customer) {
        return dDayDiscountPolicy.getDiscountAmount(customer) + giftDiscountPolicy.getDiscountAmount(customer)
                + specialDiscountPolicy.getDiscountAmount(customer) + weekdayDiscountPolicy.getDiscountAmount(customer)
                + weekendDiscountPolicy.getDiscountAmount(customer);
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
        if (giftDiscountPolicy.applicableEvent(customer)) {
            return String.format(MENU_FORMAT.get(), CHAMPAGNE.getMenu().name(), 1);
        }
        return ViewMessage.NOTHING_MESSAGE.get();
    }

    @Override
    public int getDiscountedPayment(Customer customer) {
        return customer.getTotalPayment() - getTotalDiscountAmount(customer) + giftDiscountPolicy.getDiscountAmount(
                customer);
    }

    //must 리팩토링
    private List<String> makeDiscountHistory(Customer customer) {
        List<String> history = new ArrayList<>();
        if (dDayDiscountPolicy.applicableEvent(customer)) {
            history.add(formatDiscountHistory(dDayDiscountPolicy, customer));
        }
        if (giftDiscountPolicy.applicableEvent(customer)) {
            history.add(formatDiscountHistory(giftDiscountPolicy, customer));
        }
        if (specialDiscountPolicy.applicableEvent(customer)) {
            history.add(formatDiscountHistory(specialDiscountPolicy, customer));
        }
        if (weekdayDiscountPolicy.applicableEvent(customer)) {
            history.add(formatDiscountHistory(weekdayDiscountPolicy, customer));
        }
        if (weekendDiscountPolicy.applicableEvent(customer)) {
            history.add(formatDiscountHistory(weekendDiscountPolicy, customer));
        }
        return history;
    }

    private String formatDiscountHistory(DiscountPolicy discountPolicy, Customer customer) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT.get());
        return discountPolicy + String.format(DISCOUNT_AMOUNT_FORMAT.get(),
                decimalFormat.format(discountPolicy.getDiscountAmount(customer)));
    }
}
