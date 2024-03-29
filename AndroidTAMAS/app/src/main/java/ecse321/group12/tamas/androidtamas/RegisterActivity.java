package ecse321.group12.tamas.androidtamas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class RegisterActivity extends AppCompatActivity {

    private ResourceManager rm;
    private String fileName;
    String error = null;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fileName = getFilesDir().getAbsolutePath() + "/tamas_data.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        refreshData();
    }
    private void refreshData()
    {
        EditText et = (EditText) findViewById(R.id.register_edittext_applicant_identification);
        if (et!=null)
        {
            et.setText("");
        }
        et = (EditText) findViewById(R.id.register_edittext_applicant_cgpa);
        if (et!=null)
        {
            et.setText("");
        }
        et = (EditText) findViewById(R.id.register_edittext_applicant_skills);
        if (et!=null)
        {
            et.setText("");
        }
        et = (EditText) findViewById(R.id.register_edittext_applicant_name);
        if (et!=null)
        {
            et.setText("");
        }
    }
    public void addApplicant(View v)
    {
        TamasController tc = new TamasController(rm);

        TextView tv = (TextView) findViewById(R.id.register_edittext_applicant_name);
        String name = tv.getText().toString();

        tv = (TextView) findViewById(R.id.register_edittext_applicant_cgpa);
        String cgpa =tv.getText().toString();

        tv = (TextView) findViewById(R.id.register_edittext_applicant_identification);
        String id = tv.getText().toString();

        tv = (TextView) findViewById(R.id.register_edittext_applicant_skills);
        String skills = tv.getText().toString();

        RadioButton isGraduate =(RadioButton) findViewById(R.id.register_radiobutton_graduate_student);
        Boolean studentType = isGraduate.isChecked();
        try
        {
            tc.registerApplicant(name,id,cgpa,skills,studentType);
            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
            moveTo(LoginActivity.class);
        }
        catch (InvalidInputException e)
        {
            error=e.getMessage();
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
            refreshData();
        }
    }
    private void moveTo(Class target)
    {
        Intent i = new Intent(getApplicationContext(), target);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed()
    {
        moveTo(LoginActivity.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}