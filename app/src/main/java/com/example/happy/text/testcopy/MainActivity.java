package com.example.happy.text.testcopy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happy.text.testcopy.database.AdDatabaseManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button saveBtn, readBtn;
    EditText nameEdit, priceEdit, authorEdit, nameEdit1, priceEdit1, authorEdit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = (EditText) this.findViewById(R.id.nameEdit);
        priceEdit = (EditText) this.findViewById(R.id.priceEdit);
        authorEdit = (EditText) this.findViewById(R.id.authorEdit);
        saveBtn = (Button) this.findViewById(R.id.save_btn);

        nameEdit1 = (EditText) this.findViewById(R.id.nameEdit1);
        priceEdit1 = (EditText) this.findViewById(R.id.priceEdit1);
        authorEdit1 = (EditText) this.findViewById(R.id.authorEdit1);
        readBtn = (Button) this.findViewById(R.id.read_btn1);

        saveBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        //初始化 数据库
        AdDatabaseManager.getInstance().initDatabase();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.read_btn1) {
            //读取数据库
            String name = nameEdit1.getText().toString();
            if (StringUtil.isEmpty(name)) {
                Toast.makeText(MainActivity.this, "要查找的书名为空,请输入书名后再查找!", Toast.LENGTH_SHORT).show();
                return;
            }
            Book book = AdDatabaseManager.getInstance().getData(nameEdit1.getText().toString());
            if (book != null) {
                priceEdit1.setText(book.getBookPrice());
                authorEdit1.setText(book.getBookAuthor());
                Toast.makeText(MainActivity.this, "读取数据库成功 !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "读取数据库成功! 为查找到相关数据", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.save_btn) {
            //保存数据库
            String name = nameEdit.getText().toString();
            String price = priceEdit.getText().toString();
            String author = authorEdit.getText().toString();
            Book book = new Book();
            book.setBookName(name);
            book.setBookAuthor(author);
            book.setBookPrice(price);
            AdDatabaseManager.getInstance().insertData(book);
        }
    }
}
