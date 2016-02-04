package be.jeroendruwe.elinebirthday.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import be.jeroendruwe.elinebirthday.R;
import be.jeroendruwe.elinebirthday.pojo.Answer;
import be.jeroendruwe.elinebirthday.pojo.Question;
import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;

public class QuestionFragment extends Fragment {

    private SmallBang mSmallBang;
    private static final String ARGUMENT_QUESTION = "question";
    private Button verifyAnswer;
    private RadioGroup radioGroup;
    private Question question;

    private OnCorrectAnswerSelectedListener fragmentCallback;

    public interface OnCorrectAnswerSelectedListener {
        void onCorrectAnswerSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity) {
            activity = (Activity) context;

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
            try {
                fragmentCallback = (OnCorrectAnswerSelectedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnCorrectAnswerSelectedListener");
            }
        }
    }

    public static QuestionFragment newInstance(Question question) {
        QuestionFragment myFragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_QUESTION, question);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        verifyAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(v);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                verifyAnswer.setEnabled(true);
            }
        });
    }

    private void verifyAnswer(final View view) {
        mSmallBang.bang(view, 100, new SmallBangListener() {
            @Override
            public void onAnimationStart() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int radioButtonID = radioGroup.getCheckedRadioButtonId();
                        View radioButton = radioGroup.findViewById(radioButtonID);
                        int index = radioGroup.indexOfChild(radioButton);

                        if (question.getAnswers().get(index).isCorrect()) {
                            //Correct
                            handleSuccess();
                        } else {
                            //Incorrect
                            handleFailure();
                        }
                    }
                }, 500);
            }

            @Override
            public void onAnimationEnd() {

            }
        });
    }

    private void handleFailure() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.failure));
        builder.setMessage(getString(R.string.wrong_answer));
        builder.setPositiveButton(getString(R.string.try_again), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void handleSuccess() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.success));
        builder.setMessage(getString(R.string.answered_correctly));
        builder.setPositiveButton(getString(R.string.next), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                fragmentCallback.onCorrectAnswerSelected();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, viewGroup, false);
        mSmallBang = SmallBang.attach2Window(getActivity());

        question = getArguments().getParcelable(ARGUMENT_QUESTION);

        TextView title = (TextView) view.findViewById(R.id.fragment_question_tv_title);
        title.setText(question.getTitle());

        verifyAnswer = (Button) view.findViewById(R.id.fragment_question_btn_verify_answer);

        //Create a RadioGroup for the answers
        RelativeLayout relativeLayoutContainer = (RelativeLayout) view.findViewById(R.id.fragment_question_rl_container);

        radioGroup = new RadioGroup(getContext());
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lp.addRule(RelativeLayout.BELOW, R.id.fragment_question_tv_title);
        radioGroup.setLayoutParams(lp);

        for (Answer answer : question.getAnswers()) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(answer.getAnswer());
            radioButton.setPadding(0, 30, 0, 30);
            radioGroup.addView(radioButton);
        }

        relativeLayoutContainer.addView(radioGroup);
        return view;
    }
}
