package xyz.yhsj.yhui.welcome;

import android.content.Intent;
import android.os.Bundle;
import xyz.yhsj.library.activity.AppIntro;
import xyz.yhsj.yhui.login.Login;
import xyz.yhsj.yhui.main.MainActivity;
import xyz.yhsj.yhui.welcome.slides.Slide_First;
import xyz.yhsj.yhui.welcome.slides.Slide_Fourth;
import xyz.yhsj.yhui.welcome.slides.Slide_Second;
import xyz.yhsj.yhui.welcome.slides.Slide_Third;

/**
 * Created by LOVE on 2015/6/10 010.
 */
public class Welcome_Guide extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new Slide_First(), getApplicationContext());
        addSlide(new Slide_Second(), getApplicationContext());
        addSlide(new Slide_Third(), getApplicationContext());
        addSlide(new Slide_Fourth(), getApplicationContext());
    }

    @Override
    public void onSkipPressed() {

        startActivity(new Intent(Welcome_Guide.this, MainActivity.class));
        finish();

    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(Welcome_Guide.this, Login.class));
        finish();
    }


}
