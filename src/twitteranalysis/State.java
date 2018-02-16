package twitteranalysis;

/**
 *
 * @author Fraser
 */
public class State {
    private String code;
    private String stateName;

    public State(String code, String stateName) {
        this.code = code;
        this.stateName = stateName;
    }

    public String getCode() {
        return code;
    }

    public String getStateName() {
        return stateName;
    }
   
}
