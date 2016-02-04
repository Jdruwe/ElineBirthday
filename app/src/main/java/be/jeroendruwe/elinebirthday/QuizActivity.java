package be.jeroendruwe.elinebirthday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import be.jeroendruwe.elinebirthday.fragment.QuestionFragment;
import be.jeroendruwe.elinebirthday.pojo.Answer;
import be.jeroendruwe.elinebirthday.pojo.Question;

public class QuizActivity extends AppCompatActivity implements QuestionFragment.OnCorrectAnswerSelectedListener {

    private List<QuestionFragment> questionFragments;

    private final String STATE_QUESTION = "state_question";
    private int currentQuestion;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupQuestions();

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt(STATE_QUESTION);
            showQuestion(currentQuestion);
        } else {
            showQuestion(0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_QUESTION, currentQuestion);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setupQuestions() {
        questionFragments = new ArrayList<>();
        questionFragments.add(QuestionFragment.newInstance(new Question("Amai menne ...",
                new ArrayList<Answer>() {{
                    add(new Answer("Frak", false));
                    add(new Answer("Jas", true));
                    add(new Answer("Bernadette", false));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("Tijdens welke serie/film valt Eline in slaap?",
                new ArrayList<Answer>() {{
                    add(new Answer("Walking Dead", false));
                    add(new Answer("Star Wars", false));
                    add(new Answer("Brooklyn Nine-Nine", false));
                    add(new Answer("Allemaal", true));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("Naar welk land trekken Eline en Jeroen deze zomer op vakantie?",
                new ArrayList<Answer>() {{
                    add(new Answer("Syrië", false));
                    add(new Answer("Zweden", true));
                    add(new Answer("Irak ", false));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("Lachen gieren ...",
                new ArrayList<Answer>() {{
                    add(new Answer("Blazen", true));
                    add(new Answer("Brullen", false));
                }})));


        questionFragments.add(QuestionFragment.newInstance(new Question("Moeilijkste instrument?",
                new ArrayList<Answer>() {{
                    add(new Answer("Gitaar", true));
                    add(new Answer("Dwarsfluit", false));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("Wie behaalde de laatste plaats bij het spelen van Phase 10?",
                new ArrayList<Answer>() {{
                    add(new Answer("Jeroen", false));
                    add(new Answer("Danny", true));
                    add(new Answer("Eline", false));
                    add(new Answer("Kristiane", false));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("Heeft Jeroen \"The Broken Circle Breakdown\" al gezien?",
                new ArrayList<Answer>() {{
                    add(new Answer("Ja", true));
                    add(new Answer("Ja", true));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("Tijdens welke eeuw is de Ezaartmolen gebouwd?",
                new ArrayList<Answer>() {{
                    add(new Answer("20e v.Chr", false));
                    add(new Answer("18e", false));
                    add(new Answer("19e", true));
                    add(new Answer("20e", false));
                    add(new Answer("21e", false));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("In welke stad in Wallonië hebben Jeroen en Eline vorig jaar een weekendje gelogeerd?",
                new ArrayList<Answer>() {{
                    add(new Answer("Chimay", false));
                    add(new Answer("Spa", false));
                    add(new Answer("Bouillon ", false));
                    add(new Answer("Durbuy", true));
                    add(new Answer("Luik", false));
                }})));

        questionFragments.add(QuestionFragment.newInstance(new Question("In welke stad gaan Eline en Jeroen deze zomer naar ColdPlay kijken?",
                new ArrayList<Answer>() {{
                    add(new Answer("Amsterdam", true));
                    add(new Answer("Rotterdam", false));
                    add(new Answer("Den Haag ", false));
                    add(new Answer("Utrecht", false));
                    add(new Answer("Maastricht", false));
                }})));
    }

    private void showQuestion(int question) {
        updateTitle();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_quiz_fl_question, questionFragments.get(question));
        transaction.commit();
    }

    @Override
    public void onCorrectAnswerSelected() {
        currentQuestion++;
        if (currentQuestion != questionFragments.size()) {
            showQuestion(currentQuestion);
            updateTitle();
        } else {
            Intent intent = new Intent(getApplicationContext(), KeyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void updateTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Vraag " + (currentQuestion + 1) + "/" + questionFragments.size());
        }
    }
}
