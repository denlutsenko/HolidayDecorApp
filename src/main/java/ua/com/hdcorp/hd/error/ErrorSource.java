package ua.com.hdcorp.hd.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorSource {

    private final String pointer;
    private final String parameter;
}