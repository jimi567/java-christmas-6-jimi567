package christmas.view;

import static christmas.contant.ViewMessage.AFTER_DISCOUNT_TOTAL_ORDER_PAYMENT_MESSAGE;
import static christmas.contant.ViewMessage.BEFORE_DISCOUNT_TOTAL_ORDER_PAYMENT_MESSAGE;
import static christmas.contant.ViewMessage.BENEFIT_MESSAGE;
import static christmas.contant.ViewMessage.EVENT_BADGE_MESSAGE;
import static christmas.contant.ViewMessage.GIFT_MESSAGE;
import static christmas.contant.ViewMessage.ORDER_MENU_MESSAGE;
import static christmas.contant.ViewMessage.ORDER_MENU_PROMPT_MESSAGE;
import static christmas.contant.ViewMessage.RESULT_MESSAGE;
import static christmas.contant.ViewMessage.START_MESSAGE;
import static christmas.contant.ViewMessage.TOTAL_BENEFIT_AMOUNT_MESSAGE;
import static christmas.contant.ViewMessage.VISIT_DAY_PROMPT_MESSAGE;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE.get());
    }

    public void printVisitDayPrompt() {
        System.out.println(VISIT_DAY_PROMPT_MESSAGE.get());
    }

    public void printOrderMenuPrompt() {
        System.out.println(ORDER_MENU_PROMPT_MESSAGE.get());
    }

    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE.get());
    }

    public void printOrderMenu(String orderMenuInformation) {
        System.out.println();
        System.out.println(ORDER_MENU_MESSAGE.get());
        System.out.println(orderMenuInformation);
    }

    public void printBeforeDiscountTotalPayment(String beforeDiscountTotalPaymentInformation) {
        System.out.println();
        System.out.println(BEFORE_DISCOUNT_TOTAL_ORDER_PAYMENT_MESSAGE.get());
        System.out.println(beforeDiscountTotalPaymentInformation);
    }

    public void printGiftMenu(String giftMenuInformation) {
        System.out.println();
        System.out.println(GIFT_MESSAGE.get());
        System.out.println(giftMenuInformation);
    }

    public void printBenefitHistory(String benefitHistoryInformation) {
        System.out.println();
        System.out.println(BENEFIT_MESSAGE.get());
        System.out.println(benefitHistoryInformation);
    }

    public void printTotalBenefitAmount(String totalBenefitAmountInformation) {
        System.out.println();
        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE.get());
        System.out.println(totalBenefitAmountInformation);
    }

    public void printAfterDiscountTotalPayment(String afterDiscountTotalPaymentInformation) {
        System.out.println();
        System.out.println(AFTER_DISCOUNT_TOTAL_ORDER_PAYMENT_MESSAGE.get());
        System.out.println(afterDiscountTotalPaymentInformation);
    }

    public void printEventBadge(String eventBadgeInformation) {
        System.out.println();
        System.out.println(EVENT_BADGE_MESSAGE.get());
        System.out.println(eventBadgeInformation);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
