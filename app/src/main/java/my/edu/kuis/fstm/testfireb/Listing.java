package my.edu.kuis.fstm.testfireb;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listing extends AppCompatActivity {
    // Declaring String variable ( In which we are storing firebase server URL ).
        public static final String FIREBASEURL = "https://test-fstm.firebaseio.com/";
        public static final String Database_Path = "trainings";
        private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
        // Declaring Firebase object.
        Firebase firebase;
        DatabaseReference databaseReference;
        ProgressDialog progressDialog;
        List<TrainingDetails> list = new ArrayList<>();

        RecyclerView recyclerView;

        RecyclerView.Adapter adapter ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listing);

            recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(Listing.this));

            progressDialog = new ProgressDialog(Listing.this);

            progressDialog.setMessage("Loading Data from Firebase Database");

            progressDialog.show();
            //Firebase
            Firebase.setAndroidContext(Listing.this);
            firebase = new Firebase(FIREBASEURL);

            databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        TrainingDetails studentDetails = dataSnapshot.getValue(TrainingDetails.class);

                        list.add(studentDetails);
                    }

                    adapter = new RecyclerViewAdapter(Listing.this, list);

                    recyclerView.setAdapter(adapter);

                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    progressDialog.dismiss();

                }
            });

        }
    }