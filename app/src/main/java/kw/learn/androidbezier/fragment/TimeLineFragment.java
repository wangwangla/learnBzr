package kw.learn.androidbezier.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import kw.learn.androidbezier.R;
import kw.learn.androidbezier.adapter.TimeLineRecyclerViewAdapter;
import kw.learn.androidbezier.bean.DateBean;
import kw.learn.androidbezier.bean.KeyBean;
import kw.learn.androidbezier.bean.NoteBean;
import kw.learn.androidbezier.data.TimeLineAllItem;
import kw.learn.androidbezier.sqlite.NoteSeriUtils;
import kw.learn.androidbezier.utils.NoteUtils;

public class TimeLineFragment extends Fragment {
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
                List<NoteBean> noteBeans = NoteSeriUtils.readNote();
                Map<KeyBean,ArrayList<NoteBean>> hashMap = new TreeMap(new Comparator<KeyBean>() {
                    @Override
                    public int compare(KeyBean o2, KeyBean o1) {
                        if (o1.getYear() > o2.getYear()) {
                            return 1;
                        }else if (o1.getYear() < o2.getYear()){
                            return -1;
                        }else {
                            if (o1.getMonth() > o2.getMonth()) {
                                return 1;
                            }else if (o1.getMonth() < o2.getMonth()){
                                return -1;
                            }else {
                                return 0;
                            }
                        }
                    }

                    @Override
                    public boolean equals(Object obj) {
                        return false;
                    }
                });

                for (NoteBean noteBean : noteBeans) {
                    long time = noteBean.getTime();
                    DateBean dateBean = NoteUtils.longToDateBean(time);
                    KeyBean bean = new KeyBean();
                    bean.setYear(dateBean.getYear());
                    bean.setMonth(dateBean.getMonth());
                    ArrayList<NoteBean> noteBeansTemp = null;
                    if(hashMap.containsKey(bean)){
                        noteBeansTemp = hashMap.get(bean);
                    }else {
                        noteBeansTemp = new ArrayList<>();
                    }
                    noteBeansTemp.add(0,noteBean);
                    hashMap.put(bean,noteBeansTemp);
                }
                for (KeyBean key : hashMap.keySet()) {
                    ArrayList<NoteBean> noteBeans1 = hashMap.get(key);
                    if (noteBeans1.size()>0) {
                        NoteBean noteBean = noteBeans1.get(0);
                        TimeLineAllItem item = new TimeLineAllItem();
                        item.setTitle(true);
                        item.setMsg(noteBean);
                        lineAllItems.add(item);
                    }else {
                        continue;
                    }
                    for (NoteBean noteBean : noteBeans1) {
                        TimeLineAllItem item = new TimeLineAllItem();
                        item.setTitle(false);
                        item.setMsg(noteBean);
                        lineAllItems.add(item);
                    }
                }
                timeLineRecyclerViewAdapter = new TimeLineRecyclerViewAdapter(lineAllItems,TimeLineFragment.this);

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