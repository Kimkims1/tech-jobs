package codes39.techjobsprovider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import codes39.techjobsprovider.adapters.JobAdapter;
import codes39.techjobsprovider.models.JobModel;

public class MyJobsFragment extends Fragment {

    private RecyclerView jobRecyclerView;
    private FirebaseFirestore firestore;
    private JobAdapter adapter;

    public MyJobsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestore = FirebaseFirestore.getInstance();

        Query query = firestore.collection("Jobs");

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(3)
                .build();

        //Firestore Recycler Options
        FirestorePagingOptions<JobModel> options = new FirestorePagingOptions.Builder<JobModel>()
                .setLifecycleOwner(this)
                .setQuery(query, config, JobModel.class)
                .build();

        adapter = new JobAdapter(options, getContext());


        jobRecyclerView.setHasFixedSize(true);
        jobRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        jobRecyclerView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jobRecyclerView = view.findViewById(R.id.jobRecyclerView);

    }
}