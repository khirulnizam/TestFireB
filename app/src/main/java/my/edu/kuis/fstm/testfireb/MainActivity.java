// full code at
// https://github.com/khirulnizam/TestFireB/blob/master/app/src/main/java/my/edu/kuis/fstm/testfireb/MainActivity.java
package my.edu.kuis.fstm.testfireb;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Firebase APIs import
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnsave,btnview;
    private EditText trainingid, trainingname, contact, website;

    //String to store data from EditText
    private String strainingid, strainingname, scontact, swebsite;

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String FIREBASEURL = "https://test-fstm.firebaseio.com/";
    public static final String Database_Path = "trainings";
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    // Declaring Firebase object.
    Firebase firebase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        Firebase.setAndroidContext(MainActivity.this);
        firebase = new Firebase(FIREBASEURL);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        //edittexts
        trainingid=(EditText)findViewById(R.id.trainingid);
        trainingname=(EditText)findViewById(R.id.trainingname);
        contact=(EditText)findViewById(R.id.contact);
        website=(EditText)findViewById(R.id.website);

        //button
        btnsave=(Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(this);
        btnview=(Button)findViewById(R.id.btnview);
        btnview.setOnClickListener(this);

    }//end onCreate

    public void GetDataFromEditText(){

        strainingid = trainingid.getText().toString();
        strainingname = trainingname.getText().toString();
        scontact = contact.getText().toString();
        swebsite = website.getText().toString();
    }//end GetDataFromEditText

    public void onClick(View v){
        if (v.getId()==R.id.btnsave) {//user press save record button
            TrainingDetails trainingdetails = new TrainingDetails();
            GetDataFromEditText();

            // Adding name into class function object.
            //trainingdetails.setTrainingid(strainingid);
            trainingdetails.setTrainingname(strainingname);
            trainingdetails.setWebsite(swebsite);
            trainingdetails.setContact(scontact);

            // Adding the both name and number values using TrainingDetails class object using ID.
            databaseReference.child(strainingid).setValue(trainingdetails);

            // Showing Toast message after successfully data submit.
            //**********common dialog box
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Saving record...");
            builder1.setMessage("Record for "+strainingname+" saved");
            builder1.setCancelable(false);
            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create().show();
            contact.setText("");
            website.setText("http://");
            trainingname.setText("");
            trainingid.setText("");
        }
        else if (v.getId()==R.id.btnview){
            Intent i=new Intent(this,Listing.class);
            startActivity(i);
        }
    }//end ooClick

}//end main class
