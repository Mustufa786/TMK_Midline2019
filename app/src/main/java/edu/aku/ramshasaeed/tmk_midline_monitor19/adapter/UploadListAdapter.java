package edu.aku.ramshasaeed.tmk_midline_monitor19.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.aku.ramshasaeed.tmk_midline_monitor19.R;
import edu.aku.ramshasaeed.tmk_midline_monitor19.contracts.SyncModel;
import edu.aku.ramshasaeed.tmk_midline_monitor19.databinding.UploadListAdapterBinding;

public class UploadListAdapter extends RecyclerView.Adapter<UploadListAdapter.UploadListViewHolder>{
    List<SyncModel> uploadlist;
    UploadListViewHolder holder;
    private int checkStatus(int i) {
        switch (i) {
            case 1:
                return Color.RED;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.GRAY;
            default:
                return Color.BLACK;
        }
    }
    public void updatesyncList(List<SyncModel> uploadlist) {
        this.uploadlist = uploadlist;
        notifyDataSetChanged();
    }
    public UploadListAdapter(List<SyncModel> uploadlist) {
        this.uploadlist = uploadlist;
    }

    @NonNull
    @Override
    public UploadListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upload_list_adapter, parent, false);
        return new UploadListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadListViewHolder holder, int position) {
        this.holder = holder;
        this.holder.bindUser(this.uploadlist.get(position));
    }

    @Override
    public int getItemCount() {
        return uploadlist.size() > 0 ? uploadlist.size() : 0;
    }
    public class UploadListViewHolder extends RecyclerView.ViewHolder {
        UploadListAdapterBinding binding;
        public UploadListViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
        public void bindUser(SyncModel model) {
            binding.ustatusColor.setBackgroundColor(checkStatus(model.getstatusID()));
            binding.utvTableName.setText(model.gettableName());
            binding.utvStatus.setText(model.getstatus());
            binding.utvMsg.setText(model.getmessage());
            if(model.getstatusID() == 1 || model.getstatusID() == 3||model.getstatusID() == 4){
                binding.upb.setVisibility(View.GONE);
            }else{
                binding.upb.setVisibility(View.VISIBLE);
            }
        }
    }
}
