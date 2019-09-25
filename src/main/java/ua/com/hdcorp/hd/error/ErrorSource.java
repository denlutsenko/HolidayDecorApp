package ua.com.hdcorp.hd.error;

public class ErrorSource {

    private final String pointer;
    private final String parameter;

    public ErrorSource(final String pointer, final String parameter) {
        this.pointer = pointer;
        this.parameter = parameter;
    }

    public String getPointer() {
        return pointer;
    }

    public String getParameter() {
        return parameter;
    }
}
