package kw.learn.androidbezier.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kw.learn.androidbezier.R;
import kw.learn.androidbezier.adapter.TimeLineRecyclerViewAdapter;
import kw.learn.androidbezier.data.TimeLineAllItem;

public class TimeLineFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_time_line, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.timeline);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new LoadingAllNote().execute("");
    }

    class LoadingAllNote extends AsyncTask<String, Void, String> {
        private TimeLineRecyclerViewAdapter timeLineRecyclerViewAdapter;
        @Override
        protected String doInBackground(String... strings) {
            if (getActivity()!=null) {

                List<TimeLineAllItem> lineAllItems = new ArrayList<>();
                for (int i = 0; i < 14; i++) {
                    TimeLineAllItem item = new TimeLineAllItem();
                    item.setTitle(true);
                    item.setMsg("title");
                    lineAllItems.add(item);

                    TimeLineAllItem item1 = new TimeLineAllItem();
                    item1.setTitle(false);
                    item1.setMsg("title");
                    lineAllItems.add(item1);

                }
                timeLineRecyclerViewAdapter = new TimeLineRecyclerViewAdapter(lineAllItems);

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            recyclerView.setAdapter(timeLineRecyclerViewAdapter);
        }
    }
}