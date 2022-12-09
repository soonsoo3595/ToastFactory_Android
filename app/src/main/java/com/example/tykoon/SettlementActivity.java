package com.example.tykoon;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettlementActivity extends AppCompatActivity {

    ImageButton btnBack,btnContinue;
    TextView tvDay, tvScore, tvRating, tvGuest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settlement_view);

        btnBack = (ImageButton) findViewById(R.id.settlement_back);
        btnContinue = (ImageButton)findViewById(R.id.settlement_continue);
        tvDay = (TextView) findViewById(R.id.settlement_day);
        tvScore = (TextView) findViewById(R.id.settlement_score);
        tvRating = (TextView) findViewById(R.id.settlement_rating);
        tvGuest = (TextView) findViewById(R.id.settlement_guest);

        tvDay.setText(Short.toString(GameInstance.getInstance().getStage()));
        tvScore.setText(Integer.toString(GameInstance.getInstance().getScore()));
        tvRating.setText(Float.toString(GameInstance.getInstance().getRating()));
        tvGuest.setText(Integer.toString(GameInstance.getInstance().getVisited_guest()));

        // 홈 화면으로 돌아가기
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameInstance.getInstance().init();

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                int status = intent.getIntExtra("status",1);

                switch(status)
                {
                    // 일시정지해서 이 액티비티를 연 경우 계속하기는 원래 액티비티로 돌아가자
                    // 스테이지 클리어해서 이 액티비티가 열어진 경우 계속하기를 누르면 다음 스테이지로 진행
                    // status값이 0이면, 일시정지상태일때 누른거고 1이면 스테이지가 종료돼서 정산 화면이 나온거임
                    case 0:
                        MainActivity.PauseToResume = true;
                        finish();
                        break;
                    case 1:
                        short cur_stage = GameInstance.getInstance().getStage();
                        // 마지막 스테이지인 경우 랭킹 부분으로
                        if(cur_stage == 3)
                        {
                            // 랭킹 부분 여기다 하시면 될거에요
                        }
                        // 마지막 스테이지가 아닌 경우 다음 스테이지로 넘어감
                        GameInstance.getInstance().setStage((short) (cur_stage + 1));
                        GameInstance.getInstance().setVisited_guest(0);

                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
