
package twitteranalysis;

/**
 *
 * @author Fraser
 */
public class Country {
    private String code;
    private String country;
    private String continentCode;

    public Country(String code, String country, String continentCode) {
        this.code = code;
        this.country = country;
        this.continentCode = continentCode;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public String getContinentCode() {
        return continentCode;
    } 

    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", country=" + country + ", continentCode=" + continentCode + '}';
    }
}
