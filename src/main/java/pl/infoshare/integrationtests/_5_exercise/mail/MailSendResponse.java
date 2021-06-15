package pl.infoshare.integrationtests._5_exercise.mail;

public class MailSendResponse {

    private final MailSendResult result;

    public MailSendResponse(MailSendResult result) {
        this.result = result;
    }

    public MailSendResult getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "MailSendResponse{" +
                "result=" + result +
                '}';
    }
}
