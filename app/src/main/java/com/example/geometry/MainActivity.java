package com.example.geometry;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.geometry.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Repository repository;
    private MyAdapter adapter;
    List<Stroke> strokes = new ArrayList<>();
    Random random;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        repository=Repository.newInstance();
        strokes = makeStroke();

        adapter = new MyAdapter();
        adapter.setStrokeList(strokes);
        binding.listView.setAdapter(adapter);

        binding.btnAgain.setOnClickListener(v -> {
            repository.setNewFunctions(true);
            strokes = makeStroke();


            adapter = new MyAdapter();
            adapter.setStrokeList(strokes);
            binding.listView.setAdapter(adapter);

        });
        binding.btnCheck.setOnClickListener(v -> {
            repository.setNewFunctions(false);

            checkAnswers(strokes,repository.getRepository());
            adapter = new MyAdapter();
            adapter.setStrokeList(strokes);
            binding.listView.setAdapter(adapter);


        });
        binding.help.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Инструкция")
                    .setMessage("1. Числа с корнем пишутся без него \n " +
                            "Пример: cos 45 = 2/2 \n" +
                            "2.Если значения не существует напишите тире \n" +
                            "Пример: tg 180 = - \n"+
                            "3. В случае удаления отступа (пробела) ответ будет засчитан как неверный")
                    .setIcon(R.drawable.help)
                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();
        });
        sharedPreferences = getSharedPreferences("MODE",MODE_PRIVATE);
        repository.setNight(sharedPreferences.getBoolean("night",false));

            if(repository.isNight()){
                binding.darkOrLight.setChecked(true);
                getDelegate().setLocalNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);;

            }


        binding.darkOrLight.setOnClickListener(v -> {
            if(repository.isNight()){
                getDelegate().setLocalNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("night",false);

            }
            else{
                getDelegate().setLocalNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("night",true);

            }
            editor.commit();
        });




    }

    List<Stroke> makeStroke() {


        strokes.clear();
        random = new Random();

        String[] functions = {"cos", "sin", "tg", "ctg"};
        String[] angels = {"2n", "3n/2", "n", "n/2", "n/3", "n/4", "n/6"};
        for (int i = 0; i < 3; i++) {
            repository.get(" ",i);
            strokes.add(new Stroke(functions[random.nextInt(functions.length)]
                    , angels[random.nextInt(angels.length)]));


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


    List<Stroke> checkAnswers(List<Stroke> strokes, String[] answers) {


        for (int i = 0; i < strokes.size(); i++) {
            String question = strokes.get(i).getFunction() + strokes.get(i).getAngle();
            switch (question) {
                case ("cos2n"):
                    if (repository.getRepository()[i].equals(" 1")){
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"1");
                    }
                    break;
                case ("cos3n/2"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("cosn"):
                    if (repository.getRepository()[i].equals(" -1")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"-1");
                    }
                    break;
                case ("cosn/2"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("cosn/3"):
                    if (repository.getRepository()[i].equals(" 1/2")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"1/2");
                    }
                    break;
                case ("cosn/4"):
                    if (repository.getRepository()[i].equals(" 2/2")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"2/2");
                    }
                    break;
                case ("cosn/6"):
                    if (repository.getRepository()[i].equals(" 3/2")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"3/2");

                    }
                    break;

                case ("sin2n"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("sin3n/2"):
                    if (repository.getRepository()[i].equals(" -1")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"-1");
                    }
                    break;
                case ("sinn"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("sinn/2"):
                    if (repository.getRepository()[i].equals(" 1")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"1");
                    }
                    break;
                case ("sinn/3"):
                    if (repository.getRepository()[i].equals(" 3/2")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"3/2");
                    }
                    break;
                case ("sinn/4"):
                    if (repository.getRepository()[i].equals(" 2/2")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"2/2");
                    }
                    break;
                case ("sinn/6"):
                    if (repository.getRepository()[i].equals(" 1/2")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"1/2");
                    }
                    break;

                case ("tg2n"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("tg3n/2"):
                    if (repository.getRepository()[i].equals(" -")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"-");
                    }
                    break;
                case ("tgn"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("tgn/2"):
                    if (repository.getRepository()[i].equals(" -")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"-");
                    }
                    break;
                case ("tgn/3"):
                    if (repository.getRepository()[i].equals(" 3")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"3");
                    }
                    break;
                case ("tgn/4"):
                    if (repository.getRepository()[i].equals(" 1")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"1");
                    }
                    break;
                case ("tgn/6"):
                    if (repository.getRepository()[i].equals(" 3/3")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"3/3");
                    }
                    break;

                case ("ctg2n"):
                    if (repository.getRepository()[i].equals(" -")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"-");
                    }
                    break;
                case ("ctg3n/2"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("ctgn"):
                    if (repository.getRepository()[i].equals(" -")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"-");
                    }
                    break;
                case ("ctgn/2"):
                    if (repository.getRepository()[i].equals(" 0")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"0");
                    }
                    break;
                case ("ctgn/3"):
                    if (repository.getRepository()[i].equals(" 3/3")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"3/3");
                    }
                    break;
                case ("ctgn/4"):
                    if (repository.getRepository()[i].equals(" 1")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"1");
                    }
                    break;
                case ("ctgn/6"):
                    if (repository.getRepository()[i].equals(" 3")) {
                        strokes.get(i).setAnswer(1);
                    } else {
                        strokes.get(i).setAnswer(0);
                        repository.setCorrectAnswers(i,"3");
                    }
                    break;

            }

        }
        return strokes;
    }
}
