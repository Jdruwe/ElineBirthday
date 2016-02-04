package be.jeroendruwe.elinebirthday;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tyrantgit.explosionfield.ExplosionField;

public class KeyActivity extends AppCompatActivity {

    private ExplosionField explosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        explosionField = ExplosionField.attach2Window(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.congratulations));
        }

        final ImageView lock = (ImageView) findViewById(R.id.content_key_iv_lock);
        Picasso.with(this).load(R.drawable.lock).fit().centerCrop().into(lock);

        final TextView lockKey = (TextView) findViewById(R.id.content_key_tv_lock_key);

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(v);
                v.setVisibility(View.GONE);

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lockKey.setVisibility(View.VISIBLE);
                    }
                }, 500);
            }
        });
    }
}
