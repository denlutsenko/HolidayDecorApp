package ua.com.hdcorp.hd.error;

public class Error {
    private final int status;
    private final String title;
    private final String detail;

    public Error(final int status, final String title, final String detail) {
        this.status = status;
        this.title = title;
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
