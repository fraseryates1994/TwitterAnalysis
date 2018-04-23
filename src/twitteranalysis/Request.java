package twitteranalysis;

/**
 *
 * @author Fraser
 */
public class Request {
    private String name;
    private String email;
    private String request;

    public Request(String name, String email, String request) {
        this.name = name;
        this.email = email;
        this.request = request;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRequest() {
        return request;
    }


}
