package christmas.contant;

public enum Error {
    ERROR("[ERROR] "),
    RETRY_PROMPT(" 다시 입력해 주세요."),
    INVALID_DATE_ERROR(ERROR.get() + "유효하지 않은 날짜입니다." + RETRY_PROMPT.get()),
    INVALID_ORDER_ERROR(ERROR.get() + "유효하지 않은 주문입니다." + RETRY_PROMPT.get()),
    ORDER_ONLY_DRINK_ERROR(ERROR.get() + "음료만 주문할 수 없습니다." + RETRY_PROMPT.get()),
    PARSE_INT_ERROR(ERROR.get() + "입력값 범위를 초과헀습니다." + RETRY_PROMPT.get());

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    public void throwError() {
        throw new IllegalArgumentException(get());
    }
}
