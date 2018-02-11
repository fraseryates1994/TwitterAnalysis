package twitteranalysis.Genderize.client;

import twitteranalysis.Genderize.model.NameGender;
import java.util.List;
import java.util.Locale;

public interface Genderize {

    NameGender getGender(String name);

    List<NameGender> getGenders(String... names);

    NameGender getGender(String name, Locale locale);

    List<NameGender> getGenders(String[] names, Locale locale);

}
