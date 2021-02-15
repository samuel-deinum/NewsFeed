package deinumdevelopement.newsfeed;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static deinumdevelopement.newsfeed.MainActivity.LOG_TAG;

public class QueryUtils {

    private QueryUtils (){}

    public static List<Result> fetchResults(String requestURL){

        URL url = createURL(requestURL);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "Problem making http request",e);
        }

        List<Result> results = extractFeaturesFromJson(jsonResponse);
        return results;


    }


    private static URL createURL(String stringURL){

        URL url = null;

        try{
            url = new URL(stringURL);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error in creating URL", e);
        }

        return url;

    }


    private static String makeHttpRequest(URL url) throws IOException{

        String jsonResponse = " ";

        if(url==null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG, "Response Code: " + urlConnection.getResponseCode());
            }

        }catch(IOException e){
           Log.e(LOG_TAG, "Error in getting good json Response",e);
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException{

        StringBuilder output = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader((inputStreamReader));
            String line = reader.readLine();
            while(line!=null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();

    }


    public static List<Result> extractFeaturesFromJson(String resultJSON){

        if(TextUtils.isEmpty(resultJSON)){
            return null;
        }

        List<Result> results = new ArrayList<>();

        try{

            JSONObject baseJSON = new JSONObject(resultJSON);
            JSONObject response = baseJSON.getJSONObject("response");
            JSONArray resultsArray = response.getJSONArray("results");

            for(int i=0; i<resultsArray.length(); i++){

                JSONObject currentResult = resultsArray.getJSONObject(i);

                String sectionName = currentResult.getString("sectionName");
                String webTitle = currentResult.getString("webTitle");
                String date = currentResult.getString("webPublicationDate");
                String webUrl = currentResult.getString("webUrl");

                Result result = new Result(sectionName, webTitle, date, webUrl);
                results.add(result);

            }



        }catch (JSONException e){
            Log.e(LOG_TAG, "Problem Parsing the JSON");
        }

        return results;
    }



}
