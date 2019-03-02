package deinumdevelopement.newsfeed;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sam on 19/04/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }//onCreate


    public static class NewsFeedPreferenceFragement extends PreferenceFragment{

    }//Preference Fragment



}//SettingsActivity
