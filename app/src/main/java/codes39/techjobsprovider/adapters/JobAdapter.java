package codes39.techjobsprovider.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;

import codes39.techjobsprovider.R;
import codes39.techjobsprovider.models.JobModel;

public
class JobAdapter extends FirestorePagingAdapter<JobModel,JobAdapter.jobViewHolder> {

    public JobAdapter(@NonNull FirestorePagingOptions<JobModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull jobViewHolder holder, int position, @NonNull JobModel model) {

    }

    @NonNull
    @Override
    public jobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class jobViewHolder extends RecyclerView.ViewHolder {

        private TextView jobTitle;
        private TextView jobDescription;
        private TextView jobDuration;
        private TextView payMethod;
        private Button btnApply;
        private TextView jobBudget;

        public jobViewHolder(@NonNull View itemView) {
            super(itemView);

            jobBudget = itemView.findViewById(R.id.budget);
            jobDescription = itemView.findViewById(R.id.jobDescription);
            jobDuration = itemView.findViewById(R.id.jobDuration);
            payMethod = itemView.findViewById(R.id.payMethod);
            jobTitle = itemView.findViewById(R.id.jobTitle);
            btnApply = itemView.findViewById(R.id.btnApply);
        }
    }
}
