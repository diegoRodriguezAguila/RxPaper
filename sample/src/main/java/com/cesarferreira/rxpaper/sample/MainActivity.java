package com.cesarferreira.rxpaper.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cesarferreira.rxpaper.RxPaper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Context mContext = null;
    private TextView targetTextView;

    private Person defaultValue = null;
    private final String key = "PERSON_KEY";
    private String mCustomBook = "SOME_BOOK";

    Person person = new Person("Cesar", "ferreira");
    Person anotherPerson = new Person("Ivo", "ferreira");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        targetTextView = (TextView) findViewById(R.id.targetTextView);

    }


    private void log(String s) {
        Log.d("rxpaper", s);
        targetTextView.setText(s);
    }


    public void writeCustom(View view) {
        RxPaper.with(mContext, mCustomBook)
                .write(key, anotherPerson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean isSuccessfull) {
                        if (isSuccessfull) {
                            log("Custom write success!!!");

                        }
                    }

                });
    }

    public void write(View view) {
        RxPaper.with(mContext)
                .write(key, person)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean success) {
                        if (success) {
                            log("Write success!!!");
                        }
                    }

                });
    }

    public void readCustom(View view) {
        RxPaper.with(mContext, mCustomBook)
                .read(key, defaultValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        log(e.getMessage());

                    }

                    @Override
                    public void onNext(Person person) {
                        log(String.valueOf(person));
                    }
                });
    }

    public void read(View view) {
        RxPaper.with(mContext)
                .read(key, defaultValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        log(e.getMessage());
                    }

                    @Override
                    public void onNext(Person person) {
                        log(String.valueOf(person));
                    }
                });
    }

    public void destroy(View view) {
        RxPaper.with(mContext)
                .destroy()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean success) {
                        log("Data destroyed");
                    }
                });


    }

    public void destroyCustom(View view) {
        RxPaper.with(mContext, mCustomBook)
                .destroy()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean success) {
                        log("Custom data destroyed");
                    }
                });
    }
}
