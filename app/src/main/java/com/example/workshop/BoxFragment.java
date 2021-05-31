package com.example.workshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class BoxFragment extends Fragment implements View.OnTouchListener {

    private static final String TAG = "BoxFragment";
    private View view, view1, view2, view3, view4;
    public float x, y, dx, dy, dx1, dy1, dx2, dy2, dx3, dy3, dx4, dy4;
    int finalLocation;
    public static final String SHARED_PREFERENCES = "shared_preferences";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView =  inflater.inflate(R.layout.fragment_box, container, false);

        view = fragmentView.findViewById(R.id.view);
        view1 = fragmentView.findViewById(R.id.view1);
        view2 = fragmentView.findViewById(R.id.view2);
        view3 = fragmentView.findViewById(R.id.view3);
        view4 = fragmentView.findViewById(R.id.view4);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        dx = sharedPreferences.getFloat("point00", 0); // view.getX());
        dy = sharedPreferences.getFloat("point01", 0); // view.getY());
        Log.w(TAG, "onCreateView: dx = " + dx + "  view.getx = " + view.getX() );
        Log.w(TAG, "onCreateView: dy = " + dy + "  view.gety = " + view.getY() );
        dx1 = sharedPreferences.getFloat("point10", 0); // view1.getX());
        dy1 = sharedPreferences.getFloat("point11", 0); // view1.getY());
        dx2 = sharedPreferences.getFloat("point20", 0); // view2.getX());
        dy2 = sharedPreferences.getFloat("point21", 0); // view2.getY());
        dx3 = sharedPreferences.getFloat("point30", 0); // view3.getX());
        dy3 = sharedPreferences.getFloat("point31", 0); // view3.getY());
        dx4 = sharedPreferences.getFloat("point40", 0); // view4.getX());
        dy4 = sharedPreferences.getFloat("point41", 0); // view4.getY());

        view.setX(dx);
        view.setY(dy);
        view1.setX(dx1);
        view1.setY(dy1);
        view2.setX(dx2);
        view2.setY(dy2);
        view3.setX(dx3);
        view3.setY(dy3);
        view4.setX(dx4);
        view4.setY(dy4);

        view.setOnTouchListener(this);
        view1.setOnTouchListener(this);
        view2.setOnTouchListener(this);
        view3.setOnTouchListener(this);
        view4.setOnTouchListener(this);

        return fragmentView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        editor = sharedPreferences.edit();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN :
                x = v.getX() - event.getRawX();
                y = v.getY() - event.getRawY();
                Log.e(TAG, "ACTION_DOWN: " + x );
                Log.e(TAG, "ACTION_DOWN: " + y);
                finalLocation =  MotionEvent.ACTION_DOWN;

            case MotionEvent.ACTION_MOVE:
                v.setX(event.getRawX() + x);
                v.setY(event.getRawY() + y);
                finalLocation = MotionEvent.ACTION_MOVE;
                break;

            case MotionEvent.ACTION_UP:
                float centreX = v.getX() + v.getWidth()  / 2;
                float centreY = v.getY() + v.getHeight() / 2;


                if(v==view){
//                    Log.e(TAG, "onTouch: view dx = "  + x + " dy = " + y);
                    editor.putFloat("point00",x);
                    editor.putFloat("point01",  y);

                    Log.w(TAG, "ACTION_UP: x = " + x + " event.getrawx = " + event.getRawX() +
                            " event.getx = " + event.getX() + " getx = " + v.getX() + " centrex = " + centreX);
                    Log.w(TAG, "ACTION_UP: y = " + y + " event.getrawy = " + event.getRawY()
                            + " event.gety = " + event.getY() + " gety = " + v.getY() + " centrey = " + centreY);
                    editor.apply();
                } else if(v==view1){
//                    Log.e(TAG, "onTouch: view1 dx1 = "  + x + " dy1 = " + y);
                    editor.putFloat("point10", event.getRawX() + centreX);
                    editor.putFloat("point11", event.getRawY() + centreY);
                    editor.apply();
                } else if(v==view2){
//                    Log.e(TAG, "onTouch: view2 dx2 = "  + x + " dy2 = " + y);
                    editor.putFloat("point20", event.getRawX() + x);
                    editor.putFloat("point21", event.getRawY() + y);
                    editor.apply();
                }else if(v==view3) {
//                    Log.e(TAG, "onTouch: view3 dx3 = "  + x + " dy3 = " + y);
                    editor.putFloat("point30", event.getRawX() + x);
                    editor.putFloat("point31", event.getRawY() + y);
                    editor.apply();
                }else if(v==view4){
//                    Log.e(TAG, "onTouch: view4 dx4 = "  + x + " dy4 = " + y);
                    editor.putFloat("point40", event.getRawX() + x);
                    editor.putFloat("point41", event.getRawY() + y);
                    editor.apply();
                }

                break;

            default:
                return false;
        }
        return true;
    }
}