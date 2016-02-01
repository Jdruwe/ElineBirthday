package be.jeroendruwe.elinebirthday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class KeyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.congratulations));
        }

        final ImageView backgroundImage = (ImageView) findViewById(R.id.content_key_iv_presents);
        Picasso.with(this).load(R.drawable.opened_gift).fit().centerCrop().into(backgroundImage);
    }

}
