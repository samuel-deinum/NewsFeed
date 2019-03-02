package deinumdevelopement.newsfeed;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.start;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Result>> {

    private ResultAdapter mAdapter;
    private String REQUEST_URL = "http://content.guardianapis.com/search?q=debates&api-key=test";
    private static int LOADER_CONSTANT = 1;
    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_CONSTANT, null, this);


        mAdapter = new ResultAdapter(this, new ArrayList<Result>());
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){

                Result currentResult = mAdapter.getItem(position);
                Uri resultUri = Uri.parse(currentResult.getmWebsiteUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, resultUri);
                startActivity(websiteIntent);
            }
        });//setonitemclicklister
    }//OnCreate

    //_____________LOADER CALLBACKS________________________________________________________________
    @Override
    public Loader<List<Result>> onCreateLoader(int i, Bundle bundle) {

        // Create a new loader for the given URL
        return new ResultLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Result>> loader, List<Result> results) {

/*        //For the Progress Bar, Get rid of it when it's done.
        View loadingIndicator = findViewById(R.id.loading_inticator);
        loadingIndicator.setVisibility(View.GONE);

        //For my Empty View
        if(!isConnected){
            mEmptyStateTextView.setText("No Internet Connection");
        }else {
            mEmptyStateTextView.setText("No Earthquakes Found");
        }
*/
        // Clear the adapter of previous earthquake data
        mAdapter.clear();


        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (results != null && !results.isEmpty()) {
            mAdapter.addAll(results);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Result>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}//MainActivity
