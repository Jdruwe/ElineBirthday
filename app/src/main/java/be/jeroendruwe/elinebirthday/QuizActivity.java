package be.jeroendruwe.elinebirthday;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import be.jeroendruwe.elinebirthday.fragment.QuestionFragment;
import be.jeroendruwe.elinebirthday.pojo.Answer;
import be.jeroendruwe.elinebirthday.pojo.Question;

public class QuizActivity extends AppCompatActivity implements QuestionFragment.OnCorrectAnswerSelectedListener {

    private List<QuestionFragment> questionFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupQuestions();
        showQuestion(0);
    }

    private void setupQuestions() {
        questionFragments = new ArrayList<>();
        questionFragments.add(QuestionFragment.newInstance(new Question("Amai menne ...",
                new ArrayList<Answer>() {{
                    add(new Answer("Frak", false));
                    add(new Answer("Jas", true));
                    add(new Answer("Bernadette", false));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("Question 2",
                new ArrayList<Answer>() {{
                    add(new Answer("Answer 1", false));
                    add(new Answer("Answer 2", true));
                }})));
    }

    private void showQuestion(int question) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_quiz_fl_question, questionFragments.get(question));
        transaction.commit();
    }

    @Override
    public void onCorrectAnswerSelected() {
        showQuestion(1);
    }
}
