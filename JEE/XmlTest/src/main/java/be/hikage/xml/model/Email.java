package be.hikage.xml.model;


public class Email {

    public enum EMAIL_TYPE {
        PERSONNELLE, PROFESSIONELLE
    }

    private EMAIL_TYPE type;
    private String email;

    public Email(EMAIL_TYPE type, String email) {
        this.type = type;
        this.email = email;
    }

    public EMAIL_TYPE getType() {
        return type;
    }

    public void setType(EMAIL_TYPE type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
