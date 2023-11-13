package christmas.contant;

public enum Error {
    ERROR("[ERROR] "),
    RE_TRY_PROMPT(" 다시 입력해 주세요."),
    NOT_VALIDATE_DAY_ERROR(ERROR.get() + "유효하지 않은 날짜입니다." + RE_TRY_PROMPT.get()),
    NOT_VALIDATE_ORDER_ERROR(ERROR.get() + "유효하지 않은 주문입니다." + RE_TRY_PROMPT.get()),
    ORDER_ONLY_DRINK_ERROR(ERROR.get() + "음료만 주문할 수 없습니다." + RE_TRY_PROMPT.get());

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
