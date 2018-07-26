// full code at
// https://github.com/khirulnizam/TestFireB/blob/master/app/src/main/java/my/edu/kuis/fstm/testfireb/Listing.java
package my.edu.kuis.fstm.testfireb;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listing extends AppCompatActivity {
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    //the trainings arraylist
    List<TrainingDetails> list = new ArrayList<>();

    //record display containers
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        //recyclerview container settings
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Listing.this));

        //setting for the progress dialog box and ON
        progressDialog = new ProgressDialog(Listing.this);
        progressDialog.setMessage("Loading Data from Firebase Database");
        progressDialog.show();

        //connect to the database trainings
        databaseReference = FirebaseDatabase.getInstance().getReference(MainActivity.Database_Path);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //for each record, do these
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //Log.i("list","Creating record");
                    TrainingDetails trainingdetails = dataSnapshot.getValue(TrainingDetails.class);
                    list.add(trainingdetails);
                }
                adapter = new RecyclerViewAdapter(Listing.this, list);
                recyclerView.setAdapter(adapter);
                //finish populating records and dismis progress dialog
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //if database has error(s)
                Log.i("DBError ",databaseError.toString());
                //Toast.makeText(getApplicationContext(),
                // databaseError.toString(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}//end class Listing










