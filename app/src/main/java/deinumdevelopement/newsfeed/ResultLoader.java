package deinumdevelopement.newsfeed;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class ResultLoader extends AsyncTaskLoader<List<Result>> {

    /** Tag for log messages */
    private static final String LOG_TAG = ResultLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link ResultLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public ResultLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Result> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Result> results = QueryUtils.fetchResults(mUrl);
/*
        results.add(new Result(mUrl, "title1", "date1", "websiteUrl1"));
        results.add(new Result("sectionName2", "title2", "date2", "websiteUrl2"));
        results.add(new Result("sectionName3", "title3", "date3", "websiteUrl3"));
        results.add(new Result("sectionName4", "title4", "date4", "websiteUrl4"));
        results.add(new Result("sectionName5", "title5", "date5", "websiteUrl5"));
*/

        return results;
    }
}


/*
public class ResultLoader extends AsyncTaskLoader<List<Result>> {

private String mUrl;

 /**
   * Constructs a new {@link ResultLoader}.
   *
   * @param context of the activity
   * @param url to load data from
   */
/*  public ResultLoader (Context context, String url) {
      super(context);
      mUrl = url;
  }


@Override
  protected void onStartLoading() {
      forceLoad();
  }

@Override
public List<Result> loadInBackground() {
    if (mUrl == null) {
        return null;
    }
    List<Result> results = new ArrayList();

    results.add(new Result("sectionName1", "title1", "date1", "websiteUrl1"));
    results.add(new Result("sectionName2", "title2", "date2", "websiteUrl2"));
    results.add(new Result("sectionName3", "title3", "date3", "websiteUrl3"));
    results.add(new Result("sectionName4", "title4", "date4", "websiteUrl4"));
    results.add(new Result("sectionName5", "title5", "date5", "websiteUrl5"));
    return results;
}



}//ResultLoader extends AsyncTaskLoader

*/