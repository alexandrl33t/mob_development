package ru.mirea.maximov.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText firstValue;
    private EditText secondValue;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ImageButton res1, res2;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        Button btn = (Button) view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v) {
            //onClickNumber(v);
            TextView output = (TextView) v.findViewById(R.id.outputString);
            firstValue = (EditText) v.findViewById(R.id.firstValue);
            secondValue = (EditText) v.findViewById(R.id.secondValue);
            Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
            String operator = spinner.getSelectedItem().toString();

            int first = Integer.parseInt(firstValue.getText().toString());
            int second = Integer.parseInt(secondValue.getText().toString());
            System.out.println(first + "--------------------");
            int result = 0;
            switch (operator) {
                case ("+"):
                    result = first + second;
                case ("-"):
                    result = first - second;
                case ("*"):
                    result = first * second;
                case ("/"):
                    result = first / second;
            }
            output.setText(result);
        }
        });
        return view;
    }


}