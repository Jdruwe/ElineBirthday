package be.jeroendruwe.elinebirthday.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Question implements Parcelable {

    private String title;
    private List<Answer> answers;

    public Question(String title, List<Answer> answers) {
        this.title = title;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeTypedList(answers);
    }

    private Question(Parcel in) {
        this.title = in.readString();
        in.readTypedList(answers, Answer.CREATOR);
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
