package com.example.deep.androidtutorial.ui.addbook;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.deep.androidtutorial.HomePage;
import com.example.deep.androidtutorial.R;
import com.example.deep.androidtutorial.database.BookDao;
import com.example.deep.androidtutorial.model.Book;

import java.util.Calendar;

public class AddBookFragment extends Fragment {
    private EditText tname, tprice, tauthor;
    private TextView tdate;
    private Spinner tcategory;
    private BookDao bookDao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_addbook, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomePage homePage = (HomePage) getActivity();
        bookDao = homePage.getBookDao();
        tname = view.findViewById(R.id.addbookname);
        tprice = view.findViewById(R.id.addbookprice);
        tauthor = view.findViewById(R.id.addbookauthor);
        tdate = view.findViewById(R.id.addbookdate);
        tcategory = view.findViewById(R.id.addbookcategory);
        ImageView dateicon = view.findViewById(R.id.addbookicon);
        Button addBook = view.findViewById(R.id.addbookadd);
        Button resetBook = view.findViewById(R.id.addbookreset);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()) {
                    //do code here
                    Book book = new Book();
                    book.setName(name);
                    book.setPrice(Float.parseFloat(price));
                    book.setAuthor(author);
                    book.setDate(date);
                    book.setCategory(category);

                    long t = bookDao.addBook(book);

                    if (t > 0) {
                        Toast.makeText(getActivity(), "Successfully Added", Toast.LENGTH_SHORT).show();
                        reset();
                    } else {
                        Toast.makeText(getActivity(), "Added Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        resetBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        dateicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate();
            }
        });

    }

    private void showDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                tdate.setText(y + "-" + (m + 1) + "-" + d);
            }
        }, year, month, day);
        dp.show();
    }

    private void reset() {
        tname.setText("");
        tprice.setText("");
        tauthor.setText("");
        tdate.setText("Select Date");
        tcategory.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getActivity().getResources().getStringArray(R.array.category)));
    }

    private String name, price, author, category, date;

    private boolean valid() {
        name = tname.getText().toString();
        price = tprice.getText().toString();
        author = tauthor.getText().toString();
        date = tdate.getText().toString();
        category = tcategory.getSelectedItem().toString();
        if (TextUtils.isEmpty(name)) {
            tname.setError("Enter Name");
            tname.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(price)) {
            tprice.setError("Enter Price");
            tprice.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(author)) {
            tauthor.setError("Enter Author");
            tauthor.requestFocus();
            return false;
        } else if (date.equalsIgnoreCase("select date")) {
            tdate.setError("Select Date");
            tdate.requestFocus();
            return false;
        } else if (category.equalsIgnoreCase("select category")) {
            tcategory.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}