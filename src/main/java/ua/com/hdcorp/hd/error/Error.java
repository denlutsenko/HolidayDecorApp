package ua.com.hdcorp.hd.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error {

    private final int status;
    private final ErrorSource source;
    private final String title;
    private final String detail;
}