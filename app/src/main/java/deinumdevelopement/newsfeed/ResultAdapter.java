package deinumdevelopement.newsfeed;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sam on 04/04/2018.
 */

public class ResultAdapter extends ArrayAdapter<Result> {

    public ResultAdapter(Activity context, ArrayList<Result> results){
        super(context, 0, results);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Result currentResult = getItem(position);
        View listItemView = convertView;


        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }

        String sectionName = currentResult.getmSectionName();
        String title = currentResult.getmTitle();
        String date = currentResult.getmDate();

        TextView sectionName_TextView = listItemView.findViewById(R.id.sectionName_textView);
        sectionName_TextView.setText(sectionName);

        TextView title_TextView = listItemView.findViewById(R.id.title_textView);
        title_TextView.setText(title);

        TextView date_TextView = listItemView.findViewById(R.id.date_textView);
        date_TextView.setText(date);

        return listItemView;
    }//getView







}//ResultAdapter
