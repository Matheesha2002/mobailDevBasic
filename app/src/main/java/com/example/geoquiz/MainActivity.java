//package com.example.geoquiz;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainActivity extends AppCompatActivity {
//
//    private TextView mQuestionTextView;
//    private Button mTrueButton;
//    private Button mFalseButton;
//    private Button mNextButton;
//
//    private Button mPreviousButton;
//
//    private Button btnEnglish;
//    private Button btnSinhala;
//    private Button btnTamil;
//
//
//
//
//    private Question[] mQuestionBank = new Question[] {
//            new Question(R.string.question1, true),
//            new Question(R.string.question2, false),
//            new Question(R.string.question3, true),
//            new Question(R.string.question4, false),
//            new Question(R.string.question5, false)
//    };
//
//    private int mCurrentIndex = 0;
//    private int mCorrectAnswers = 0;
//    private int mTotalQuestions = mQuestionBank.length;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnEnglish = findViewById(R.id.btnEnglish);
//        btnSinhala = findViewById(R.id.btnSinhala);
//        btnTamil = findViewById(R.id.btnTamil);
//
//        mQuestionTextView = findViewById(R.id.question_text_view);
//        mTrueButton = findViewById(R.id.true_button);
//        mFalseButton = findViewById(R.id.false_button);
//        mNextButton = findViewById(R.id.next_button);
//        mPreviousButton = findViewById(R.id.previous_button);
//
//        mPreviousButton.setOnClickListener(v -> {
//            mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
//            updateQuestion();
//        });
//
//        updateQuestion();
//
//        mTrueButton.setOnClickListener(v -> checkAnswer(true));
//        mFalseButton.setOnClickListener(v -> checkAnswer(false));
//
//        mNextButton.setOnClickListener(v -> {
//            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//            updateQuestion();
//        });
//    }
//
//    private void updateQuestion() {
//        int question = mQuestionBank[mCurrentIndex].getTextResId();
//        mQuestionTextView.setText(question);
//    }
//
//    private void checkAnswer(boolean userPressedTrue) {
//        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
//
//        int messageResId;
//
//        if (userPressedTrue == answerIsTrue) {
//            messageResId = R.string.correct_toast;
//            mCorrectAnswers++;
//        } else {
//            messageResId = R.string.incorrect_toast;
//        }
//
//        double successRate = ((double)mCorrectAnswers / mTotalQuestions) * 100;
//
//        Toast.makeText(this,
//                getString(messageResId) +
//                        "\nSuccess Rate: " + successRate + "%",
//                Toast.LENGTH_SHORT).show();
//    }
//}
package com.example.geoquiz;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;

    private Button btnEnglish;
    private Button btnSinhala;
    private Button btnTamil;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
            new Question(R.string.question4, false),
            new Question(R.string.question5, false)
    };

    private int mCurrentIndex = 0;
    private int mCorrectAnswers = 0;
    private int mTotalQuestions = mQuestionBank.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Language Buttons
        btnEnglish = findViewById(R.id.btnEnglish);
        btnSinhala = findViewById(R.id.btnSinhala);
        btnTamil = findViewById(R.id.btnTamil);

        // Quiz Views
        mQuestionTextView = findViewById(R.id.question_text_view);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPreviousButton = findViewById(R.id.previous_button);

        // Initial Question
        updateQuestion();

        // TRUE button
        mTrueButton.setOnClickListener(v -> checkAnswer(true));

        // FALSE button
        mFalseButton.setOnClickListener(v -> checkAnswer(false));

        // NEXT button
        mNextButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });

        // PREVIOUS button
        mPreviousButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
            updateQuestion();
        });

        // Language Switching
        btnEnglish.setOnClickListener(v -> setLocale("en"));
        btnSinhala.setOnClickListener(v -> setLocale("si"));
        btnTamil.setOnClickListener(v -> setLocale("ta"));
    }

    // Update Question Text
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    // Check Answer
    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            mCorrectAnswers++;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        double successRate = ((double) mCorrectAnswers / mTotalQuestions) * 100;

        Toast.makeText(this,
                getString(messageResId) +
                        "\nSuccess Rate: " + String.format("%.0f", successRate) + "%",
                Toast.LENGTH_SHORT).show();
    }

    // Language Change Method
    private void setLocale(String langCode) {

        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        recreate(); // refresh activity
    }
}