package sg.edu.np.mad.mad_recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ToDoViewHolder extends RecyclerView.ViewHolder {
    TextView txt;
    public ToDoViewHolder(View itemView, final ToDoAdaptor.OnItemClickListener onItemClickListener){
        super(itemView);
        txt = itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if(onItemClickListener != null){
                    int position = getAdapterPosition();
                    onItemClickListener.ItemClick(position);
                }
            }
        });
    }
}
