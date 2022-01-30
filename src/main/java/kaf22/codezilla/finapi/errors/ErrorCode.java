package kaf22.codezilla.finapi.errors;

public enum ErrorCode {

    INCORRECT_LOGIN_DETAILS(1, "Incorrect login details"),
    INCORRECT_REGISTRATION_DATA(2, "Incorrect registration data"),
    INCORRECT_ID(3, "Incorrect id"),
    AT_LEAST_ONE_PARAMETER(4, "In the body of the method, you must pass at least one field for changes"),
    NO_SUCH_OBJECT(5, "No such object"),
    FILE_DOES_NOT_EXIST(6, "File does not exist"),
    NOT_ALL_REQUIRED_FIELDS_WERE_FILLED(7, "Not all required fields were filled"),
    INCORRECT_DATA_OR_QUERY_PARAMETERS(8, "Incorrect data or query parameters");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
