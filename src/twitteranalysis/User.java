
package twitteranalysis;

/**
 *
 * @author Fraser
 */
public class User {
    
    private String id;
    private String firstName;
    private String lastName;
    private String twitterName;

    public User(String id, String firstName, String lastName, String twitterName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.twitterName = twitterName;
    }
    
    public User() {
        
    }

    public String getId() {
        return id;
    }
    
        
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", twitterName=" + twitterName + '}';
    }
}
