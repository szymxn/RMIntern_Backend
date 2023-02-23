package at.spengergasse.rmbackend.validation;


@SuppressWarnings("serial")
public class EmilExistException extends Throwable{
    public EmilExistException(final String message){
        super(message);
    }
}
