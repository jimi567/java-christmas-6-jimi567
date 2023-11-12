package christmas.contant;

public enum Error {
    ERROR("[ERROR]"),
    RE_TRY_PROMPT(" 다시 입력해 주세요.");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    public void throwException() {
        throw new IllegalArgumentException(get());
    }
}
