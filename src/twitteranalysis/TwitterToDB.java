package twitteranalysis;

/**
 *
 * @author Fraser
 */
public class TwitterToDB {

    private int id;
    private String ogUserName;
    private String ogStatus;
    private String comment;
    private int followersCount;
    private int favouriteCount;
    private int friendCount;
    private int location;
    private int isVerified;
    private int hasSwear;
    private int hasPositiveWord;
    private int hasNegativeWord;
    private int hasPositiveEmoji;
    private int hasNegativeEmoji;

    public TwitterToDB() {
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public void setOgUserName(String ogUserName) {
        this.ogUserName = ogUserName;
    }
    
    public void setOgStatus(String status) {
        this.ogStatus = status;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFollowersCount(int followersCount) {
        if ((followersCount >= 0) && (followersCount <= 10)) {
            this.followersCount = 0;
        } else if ((followersCount > 10) && (followersCount <= 100)) {
            this.followersCount = 1;
        } else if ((followersCount > 100) && (followersCount <= 300)) {
            this.followersCount = 2;
        } else if (followersCount > 300) {
            this.followersCount = 3;
        }
    }

    public void setFavouriteCount(int favouriteCount) {
        if (favouriteCount == 0) {
            this.favouriteCount = 0;
        } else {
            this.favouriteCount = 1;
        }
    }

    public void setFriendCount(int friendCount) {
        if ((friendCount >= 0) && (friendCount <= 10)) {
            this.friendCount = 0;
        } else if ((friendCount > 10) && (friendCount <= 100)) {
            this.friendCount = 1;
        } else if ((friendCount > 100) && (friendCount <= 300)) {
            this.friendCount = 2;
        } else if (friendCount > 300) {
            this.friendCount = 3;
        }
    }

    public void setLocation(Country location) {
        if (location.getContinentCode().equals("AF")) {
            this.location = 0;
        } else if (location.getContinentCode().equals("AS")) {
            this.location = 1;
        } else if (location.getContinentCode().equals("OC")) {
            this.location = 2;
        } else if (location.getContinentCode().equals("EU")) {
            this.location = 3;
        } else if (location.getContinentCode().equals("NA")) {
            this.location = 4;
        } else if (location.getContinentCode().equals("SA")) {
            this.location = 5;
        }
    }

    public void setIsVerified(boolean verified) {
        if (verified) {
            this.isVerified = 1;
        } else {
            this.isVerified = 0;
        }
    }

    public void setHasSwear(boolean swear) {
        if (swear) {
            this.hasSwear = 1;
        } else {
            this.hasSwear = 0;
        }
    }

    public void setHasPositiveWord(boolean posWord) {
        if (posWord) {
            this.hasPositiveWord = 1;
        } else {
            this.hasPositiveWord = 0;
        }
    }

    public void setHasNegativeWord(boolean negWord) {
        if (negWord) {
            this.hasNegativeWord = 1;
        } else {
            this.hasNegativeWord = 0;
        }
    }

    public void setHasPositiveEmoji(boolean posEmoji) {
        if (posEmoji) {
            this.hasPositiveEmoji = 1;
        } else {
            this.hasPositiveEmoji = 0;
        }
    }

    public void setHasnegativeEmoji(boolean negEmoji) {
        if (negEmoji) {
            this.hasNegativeEmoji = 1;
        } else {
            this.hasNegativeEmoji = 0;
        }
    }

    public int getId() {
        return id;
    }
    
    public String getOgUserName() {
        return ogUserName;
    }

    public String getOgStatus() {
        return ogStatus;
    }

    public String getComment() {
        return comment;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public int getLocation() {
        return location;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public int getHasSwear() {
        return hasSwear;
    }

    public int getHasPositiveWord() {
        return hasPositiveWord;
    }

    public int getHasNegativeWord() {
        return hasNegativeWord;
    }

    public int getHasPositiveEmoji() {
        return hasPositiveEmoji;
    }

    public int getHasNegativeEmoji() {
        return hasNegativeEmoji;
    }
    
    
    
}
