package be.jeroendruwe.elinebirthday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;

public class StartActivity extends AppCompatActivity {

    private SmallBang mSmallBang;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mSmallBang = SmallBang.attach2Window(this);

        final ImageView backgroundImage = (ImageView) findViewById(R.id.content_start_imv_background);
        setBackground(backgroundImage, R.drawable.eline);

        next = (Button) findViewById(R.id.content_start_btn_next);

        setShowCase(next);
        setListeners();
    }

    private void setListeners() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(v);
            }
        });
    }

    private void setShowCase(View view) {
        new MaterialShowcaseView.Builder(this)
                .setTarget(view)
                .setDismissOnTouch(true)
                .setMaskColour(R.color.colorPrimaryDark)
                .setContentText(getString(R.string.intro))
                .show();
    }

    private void setBackground(ImageView imageView, int resourceId) {
        Picasso.with(this).load(resourceId).fit().centerCrop().into(imageView);
    }

    private void addNumber(final View view) {
        mSmallBang.bang(view, 100, new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
}
