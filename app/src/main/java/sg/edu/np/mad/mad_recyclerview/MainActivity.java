package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String TAG="To-Do List";
    ArrayList<String>data = new ArrayList<>();
    Button addButton;
    EditText aEdit;
    ToDoAdaptor tAdaptor;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.button);
        aEdit = findViewById(R.id.editText);
        rv = findViewById(R.id.recyclerView);
        data.add("Buy Milk");
        data.add("Send Postage");
        data.add("Buy Android Development Book");

        //adaptor
        tAdaptor = new ToDoAdaptor(data);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(tAdaptor);
        tAdaptor.setOnItemClickListener(new ToDoAdaptor.OnItemClickListener(){
            @Override
            public void ItemClick(int position){
                Log.v(TAG,"To-Do Option clicked.");
                removeToDo(position);
            }
        });
    }
    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     *
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);
        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }

    public void addToDo (View v){
        data.add(aEdit.getText().toString());
        tAdaptor.notifyDataSetChanged();
        showNewEntry(rv,data);
        Log.v(TAG,"Added new to-do option.");
        aEdit.getText().clear();
    }
    private void removeToDo(final int position){
        String todo = data.get(position);
        //alert dialog
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Delete");
        b.setMessage("Are you sure you want to delete "+todo + " ?");
        b.setCancelable(false);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.remove(position);
                Log.v(TAG,"To-Do option removed.");
                tAdaptor.notifyDataSetChanged();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"User chose No.");
            }
        });
        AlertDialog alert = b.create();
        alert.show();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
}
