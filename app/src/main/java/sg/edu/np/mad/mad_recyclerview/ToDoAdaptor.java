package sg.edu.np.mad.mad_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdaptor extends RecyclerView.Adapter<ToDoViewHolder> {
    ArrayList<String> data;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener o) {
        this.onItemClickListener = o;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public ToDoAdaptor(ArrayList<String> input){
        data = input;
    }
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbox,parent, false);
        return new ToDoViewHolder(item,onItemClickListener);
    }
    public void onBindViewHolder(ToDoViewHolder holder, int position){
        String s = data.get(position);
        holder.txt.setText(s);
    }
    public int getItemCount(){
        return data.size();
    }
}
