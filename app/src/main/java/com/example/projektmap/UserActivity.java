package com.example.projektmap;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class UserActivity extends AppCompatActivity {

    private TextView tvUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tvUser = findViewById(R.id.tvUser);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvUser.setText(bundle.getString("user"));
        }
    }


    public void onRead(View v){
        // Čitaj iz baze
        try {
            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase baza = helper.getReadableDatabase();

            String[] string = {
                    DatabaseOptions.EMAIL
            };
            Cursor cursor = baza.query(
                    DatabaseOptions.USERS_TABLE, string,
                    null,
                    null,
                    null,
                    null,
                    null);

            // String builder
            StringBuilder builder = new StringBuilder();
            int ix_email = cursor.getColumnIndexOrThrow(DatabaseOptions.EMAIL);
            while(cursor.moveToNext()){
                builder.append(cursor.getString(ix_email));
                builder.append("\n");
            }
            // Upis u labelu
            TextView tv_korisnici = findViewById(R.id.tv_korisnici);
            tv_korisnici.setText(builder.toString());

        }catch (SQLiteException ex){
            ex.printStackTrace(); // Logcat
        }

    }
}









