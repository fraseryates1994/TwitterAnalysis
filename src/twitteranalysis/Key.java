
package twitteranalysis;

/**
 *
 * @author Fraser
 */
public class Key {
    private int id;
    private String platform;
    private String keyName;
    private String kayValue;

    public Key(int id, String platform, String keyName, String kayValue) {
        this.id = id;
        this.platform = platform;
        this.keyName = keyName;
        this.kayValue = kayValue;
    }

    public int getId() {
        return id;
    }

    public String getPlatform() {
        return platform;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getKayValue() {
        return kayValue;
    }
    
    
}
