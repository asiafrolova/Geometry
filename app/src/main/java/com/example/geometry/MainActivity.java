package com.example.geometry;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.geometry.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Repository repository;
    MyAdapter adapter;
    Stroke[] strokes;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        strokes = makeStroke();
        repository=Repository.newInstance();

        adapter = new MyAdapter(this, strokes);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        binding.btnAgain.setOnClickListener(v -> {
            strokes = makeStroke();
            adapter = new MyAdapter(this, strokes);
            lv.setAdapter(adapter);
        });
        binding.btnCheck.setOnClickListener(v -> {
            repository.get(binding.editText1.getText().toString(),0);
            repository.get(binding.editText2.getText().toString(),1);
            repository.get(binding.editText3.getText().toString(),2);
            checkAnswers(strokes,repository.getRepository());
            adapter = new MyAdapter(this, strokes);
            lv.setAdapter(adapter);


        });
        binding.help.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Инструкция")
                    .setMessage("1. Числа с корнем пишутся без него \n " +
                            "Пример: cos 45 = 2/2 \n" +
                            "2.Если значения не существует напишите тире \n" +
                            "Пример: tg 180 = -")
                    .setIcon(R.drawable.help)
                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();
        });



    }

    Stroke[] makeStroke() {
        random = new Random();

        String[] functions = {"cos", "sin", "tg", "ctg"};
        String[] angels = {"2n", "3n/2", "n", "n/2", "n/3", "n/4", "n/6"};
        strokes = new Stroke[3];
        for (int i = 0; i < strokes.length; i++) {
            strokes[i] = new Stroke(
                    functions[random.nextInt(functions.length)]
                    , angels[random.nextInt(angels.length)]);

        }
        return strokes;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Инструкция")
                .setMessage("1. Числа с корнем пишутся без него \n " +
                        "Пример: cos 45 = 2/2 \n" +
                        "2.Если значения не существует напишите - \n" +
                        "Пример: tg 180 = -")
                .setIcon(R.drawable.help)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    Stroke[] checkAnswers(Stroke[] strokes, String[] answers) {


        for (int i = 0; i < strokes.length; i++) {
            String question = strokes[i].getFunction() + strokes[i].getAngle();
            switch (question) {
                case ("cos2n"):
                    if (repository.getRepository()[i].equals("1")){
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("cos3n/2"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("cosn"):
                    if (repository.getRepository()[i].equals("-1")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("cosn/2"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("cosn/3"):
                    if (repository.getRepository()[i].equals("1/2")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("cosn/4"):
                    if (repository.getRepository()[i].equals("2/2")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("cosn/6"):
                    if (repository.getRepository()[i].equals("3/2")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;

                case ("sin2n"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("sin3n/2"):
                    if (repository.getRepository()[i].equals("-1")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("sinn"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("sinn/2"):
                    if (repository.getRepository()[i].equals("1")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("sinn/3"):
                    if (repository.getRepository()[i].equals("3/2")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("sinn/4"):
                    if (repository.getRepository()[i].equals("2/2")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("sinn/6"):
                    if (repository.getRepository()[i].equals("3/2")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;

                case ("tg2n"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("tg3n/2"):
                    if (repository.getRepository()[i].equals("-")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("tgn"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("tgn/2"):
                    if (repository.getRepository()[i].equals("-")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("tgn/3"):
                    if (repository.getRepository()[i].equals("3")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("tgn/4"):
                    if (repository.getRepository()[i].equals("1")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("tgn/6"):
                    if (repository.getRepository()[i].equals("3/3")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;

                case ("ctg2n"):
                    if (repository.getRepository()[i].equals("-")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("ctg3n/2"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("ctgn"):
                    if (repository.getRepository()[i].equals("-")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("ctgn/2"):
                    if (repository.getRepository()[i].equals("0")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("ctgn/3"):
                    if (repository.getRepository()[i].equals("3/3")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("ctgn/4"):
                    if (repository.getRepository()[i].equals("1")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;
                case ("ctgn/6"):
                    if (repository.getRepository()[i].equals("3")) {
                        strokes[i].setAnswer(1);
                    } else {
                        strokes[i].setAnswer(0);
                    }
                    break;

            }

        }
        return strokes;
    }
}