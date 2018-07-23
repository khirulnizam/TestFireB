package my.edu.kuis.fstm.testfireb;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//Firebase APIs import
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String FIREBASEURL = "https://test-fstm.firebaseio.com/";
    // Declaring Firebase object.
    Firebase firebase;

    private Button btnsave;
    private EditText trainingid, trainingname, contact, website;

    //String to store data from EditText
    private String strainingid, strainingname, scontact, swebsite;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase


        //edittext
        trainingid=(EditText)findViewById(R.id.trainingid);
        trainingname=(EditText)findViewById(R.id.trainingname);
        contact=(EditText)findViewById(R.id.contact);
        website=(EditText)findViewById(R.id.website);

        //button
        btnsave=(Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(this);

    }
    public void onClick(View v){

    }

}
