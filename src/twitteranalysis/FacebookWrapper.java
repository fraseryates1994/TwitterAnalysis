
package twitteranalysis;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author win8.1
 */
public class FacebookWrapper {

    private Facebook facebook = null;
    
    public FacebookWrapper() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAppId("495582014171231")
                .setOAuthAppSecret("e0a77dfc2ffb4a6871f757beb6e0a16f")
                .setOAuthAccessToken("EAACEdEose0cBAMeZAeae13RBbKi4O9FdaqAphEnwo1ASGHiBUrdKOhbowwxjW4bRbCSx4nLdZBeERIKZA5eXzvp0bqzR6TuFcGjmRv363xwSQ2A4OE7fIaGhOqEhmzK4ak3PLXQfutY7VZCAKHh4mjKUtXFZBZBHqxghh1Hf06k4AgMNpVVMn7ZCWbCZC3ZBjqmCFvEke59E06wZDZD")
                .setOAuthPermissions("fraseryates1994@gmail.com");
        FacebookFactory ff = new FacebookFactory(cb.build());
        Facebook facebook = ff.getInstance();
    }

    public Facebook getFacebook() {
        return facebook;
    }
    
    
    
    public ResponseList<Post> getFeed(String pageName) {
        try {
            ResponseList<Post> feed = facebook.getFeed(pageName);
            for (Post post : feed) {
                System.out.println(post.getMessage());
            }    
            return feed;
        } catch (FacebookException ex) {
            Logger.getLogger(TwitterAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
