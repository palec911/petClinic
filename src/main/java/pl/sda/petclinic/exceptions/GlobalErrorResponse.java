package pl.sda.petclinic.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public GlobalErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public GlobalErrorResponse() {
    }
}
