package ru.pyply.games.mobilehorrorquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Character player; // персонаж
    Story story; // история (сюжет)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // создаем нового персонажа и историю
        player = new Character("Вася");
        story = new Story();
        // в первый раз выводим на форму весь необходимый текст и элементы
        // управления
        updateStatus();
    }

    // метод для перехода на нужную ветку развития
    private void go(int i) {
        story.go(i + 1);
        updateStatus();
        // если история закончилась, выводим на экран поздравление
        if (story.isEnd()) {

            String text;
            if (player.getHealth() <= 0)
                text = "Вы умерли!";
            else if (player.getKnowledge() <= 100) {
                text = player.name + ", Вы живы, но расследование не завершенно, а убийства не закончились.";
            } else {
                text = player.name + ", Вы нашли все улики и раскрыли дело! Маньяком был уборщик этого дома." +
                        "\n Его оскорбляли подростки, из-за чего он сильно обиделся и начал их убивать." +
                        " Вам дали премию за свои заслуги!";
            }
            ((TextView) findViewById(R.id.title)).
                setText("Конец!");
            ((TextView) findViewById(R.id.desc)).
                    setText(text);
            ((LinearLayout) findViewById(R.id.layout)).removeAllViews();

            Toast.makeText(this, "Игра закончена!", Toast.LENGTH_LONG).show();
        }
    }

    // в этом методе размещаем всю информацию, специфичную для текущей
    // ситуации на форме приложения, а также размещаем кнопки, которые
    // позволят пользователю выбрать дальнейший ход событий
    @SuppressLint("SetTextI18n")
    private void updateStatus() {
        // не забываем обновить репутацию в соответствии с новым
        // состоянием дел
        if (story.current_situation.isFirstCheck) {
            story.current_situation.isFirstCheck = false;
            player.addHealth(story.current_situation.dh);
            player.addKnowledge(story.current_situation.dk);
        }

        // выводим статус на форму
        ((TextView) findViewById(R.id.status)).
                setText(String.format("ХП: %s\nЗнания: %s%%",
                        player.getHealth(), player.getKnowledge()));

        // аналогично для заголовка и описания ситуации
        ((TextView) findViewById(R.id.title)).
                setText(story.current_situation.subject);
        ((TextView) findViewById(R.id.desc)).
                setText(story.current_situation.text);
        ((LinearLayout) findViewById(R.id.layout)).removeAllViews();

        // размещаем кнопку для каждого варианта, который пользователь
        // может выбрать
        for (int i = 0; i < story.current_situation.direction.length; i++) {
            Button b = new Button(this);
            b.setText(Integer.toString(i + 1));
            final int buttonId = i;
            // Внимание! в анонимных классах
            // можно использовать только те переменные метода,
            // которые объявлены как final.
            // Создаем объект анонимного класса и устанавливаем его
            // обработчиком нажатия на кнопку
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    go(buttonId);
                    // поскольку анонимный класс имеет полный
                    // доступ к методам и переменным родительского,
                    // то просто вызываем нужный нам метод.
                }
            });
            // добавляем готовую кнопку на разметку
            ((LinearLayout) findViewById(R.id.layout)).addView(b);
        }
    }

}