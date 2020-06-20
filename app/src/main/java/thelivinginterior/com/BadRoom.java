package thelivinginterior.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class BadRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_room);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(BadRoom.this,MainActivity.class);
        startActivity(i);

        super.onBackPressed();
    }
}