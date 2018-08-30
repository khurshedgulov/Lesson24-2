package com.example.admin.lesson24_2;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    FrameLayout parent;
    int total;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        parent = findViewById(R.id.imageParent);



        // ValueAnimator служит для анимирования значений в заданном диапазоне
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        // addUpdateListener служит для привязки обработчика события обновления значения
        // рассчитанного для анимирования
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // В фукнции onAnimationUpdate анимированное значение можно получить через объект ValueAnimator
                // через функцию getAnimatedValue
                int padding = (int) animation.getAnimatedValue();
                textView.setPadding(padding, padding, 0, 0);
            }
        });

        // В переменной хранится общее количество ImageView вложенного внутрь родительского FrameLayout
        total = parent.getChildCount();

        // Этот ValueAnimator служит для анимирования значений от 0 до 1 для анимации плавного появления ImageView
        final ValueAnimator fade = ValueAnimator.ofFloat(0, 1);
        // Устанавливается обработчик события обновления рассчитанного значения
        fade.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Плавно появляется указанный дочерний ImageView
                parent.getChildAt(counter % total).setAlpha((float)animation.getAnimatedValue());
            }
        });

        // При клике на родительский фрейм запустить анимацию и увеличить счетчик на единицу
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fade.start();
                counter++;
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.start();

            }
        });

    }
}
