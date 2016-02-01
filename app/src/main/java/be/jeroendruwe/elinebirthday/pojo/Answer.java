package be.jeroendruwe.elinebirthday.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    private String answer;
    private boolean isCorrect;

    public Answer(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.answer);
        dest.writeByte(isCorrect ? (byte) 1 : (byte) 0);
    }

    private Answer(Parcel in) {
        this.answer = in.readString();
        this.isCorrect = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
