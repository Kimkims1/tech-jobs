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

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import codes39.techjobsprovider.adapters.JobAdapter;
import codes39.techjobsprovider.models.JobModel;

public class MyJobsFragment extends Fragment {

    private RecyclerView jobRecyclerView;
    private JobAdapter adapter;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firestore.collection("Jobs");

    public MyJobsFragment() {
        // Required empty public constructor
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
        firestore = FirebaseFirestore.getInstance();

        loadData();
    }

    private void loadData() {

        Query query = reference.orderBy("jobBudget", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<JobModel> options = new FirestoreRecyclerOptions.Builder<JobModel>()
                .setQuery(query, JobModel.class)
                .build();

        adapter = new JobAdapter(options);

        jobRecyclerView.setHasFixedSize(true);
        jobRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        jobRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}