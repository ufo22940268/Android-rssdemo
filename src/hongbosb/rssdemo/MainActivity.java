package hongbosb.rssdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;

import java.util.*;

import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends ListActivity
{
    PullToRefreshListView mRefreshView;
    ArrayAdapter mAdapter;
    LinkedList<String> mListItems;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mRefreshView = (PullToRefreshListView)findViewById(R.id.refreshable_list);
        mRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetDataTask().execute();
            }
        });


        ListView actualListView = mRefreshView.getRefreshableView();

        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

        actualListView.setAdapter(mAdapter);

    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String params) {
            super.onPostExecute(params);

            mListItems.addFirst("later news...");
            mAdapter.notifyDataSetChanged();
            
            mRefreshView.onRefreshComplete();
        }
    }

    private String[] mStrings = { "hongbosb", "shijiesb", "xuyaosb", "llwsb", "fjsb", "cnsb", "blblsb",
        "acfsb", "chsb", "hkbsb", "afksb"};
}
