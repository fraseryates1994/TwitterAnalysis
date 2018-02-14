
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
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        
        cb.setDebugEnabled(true)
                .setOAuthAppId(db.getKey("OAuthAppId").getKayValue())
                .setOAuthAppSecret(db.getKey("OAuthAppSecret").getKayValue())
                .setOAuthAccessToken(db.getKey("OAuthAccessToken").getKayValue())
                .setOAuthPermissions(db.getKey("OAuthPermissions").getKayValue());
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
