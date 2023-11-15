package christmas.contant;

import java.text.DecimalFormat;

public enum ViewMessage {
    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    VISIT_DAY_PROMPT_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU_PROMPT_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    RESULT_MESSAGE("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_MESSAGE("<주문 메뉴>"),
    BEFORE_DISCOUNT_TOTAL_ORDER_PAYMENT_MESSAGE("<할인 전 총주문 금액>"),
    GIFT_MESSAGE("<증정 메뉴>"),
    BENEFIT_MESSAGE("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT_MESSAGE("<총혜택 금액>"),
    AFTER_DISCOUNT_TOTAL_ORDER_PAYMENT_MESSAGE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE_MESSAGE("<12월 이벤트 배지>"),
    MONEY_FORMAT("%s원"),
    DISCOUNT_AMOUNT_FORMAT("-" + MONEY_FORMAT.get()),
    NOTHING_MESSAGE("없음"),
    MENU_FORMAT("%s %d개"),
    DECIMAL_FORMAT("###,###"),
    COMMA(","),
    DASH("-");

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    public static String decimalFormat(int money) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT.get());
        return decimalFormat.format(money);
    }
}
