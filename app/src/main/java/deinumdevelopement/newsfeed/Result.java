package deinumdevelopement.newsfeed;

/**
 * Created by sam on 04/04/2018.
 */

public class Result {

    private String mSectionName;
    private String mTitle;
    private String mDate;
    private String mWebsiteUrl;



    public Result(String sectionName, String title, String date, String websiteUrl){

        mSectionName = sectionName;
        mTitle = title;
        mDate = date;
        mWebsiteUrl = websiteUrl;
    }

    public String getmSectionName(){
        return mSectionName;
    }
    public String getmTitle(){
        return mTitle;
    }
    public String getmDate(){
        return mDate;
    }
    public String getmWebsiteUrl(){
        return mWebsiteUrl;
    }
}
