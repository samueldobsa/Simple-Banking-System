import java.io.IOException;

// update the class
class BadRequestException extends IOException {
    String message;

    public BadRequestException(String message){
        super(message);
    }

}