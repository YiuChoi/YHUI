package xyz.yhsj.yhui.welcome;

import android.content.Intent;
import android.os.Bundle;
import xyz.yhsj.library.activity.AppIntro;
import xyz.yhsj.yhui.MainActivity;
import xyz.yhsj.yhui.welcome.slides.FirstSlide;
import xyz.yhsj.yhui.welcome.slides.FourthSlide;
import xyz.yhsj.yhui.welcome.slides.SecondSlide;
import xyz.yhsj.yhui.welcome.slides.ThirdSlide;

/**
 * Created by LOVE on 2015/6/10 010.
 */
public class Welcome_Guide extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new SecondSlide(), getApplicationContext());
        addSlide(new ThirdSlide(), getApplicationContext());
        addSlide(new FourthSlide(), getApplicationContext());
    }

    @Override
    public void onSkipPressed() {

        startActivity(new Intent(Welcome_Guide.this, MainActivity.class));
        finish();

    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(Welcome_Guide.this, MainActivity.class));
        finish();
    }


}
