package donggeo.appdonggeorefactoring;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class SearchByValue extends AppCompatActivity {

    public Context context;

    String[] exchangeRate = {
            "유럽연합 EUR",  "독익 EUR", "프랑스 EUR", "이탈리아 EUR", "스페인 EUR", "포르투갈 EUR", "그리스 EUR", "네덜란드 EUR", "오스트리아 EUR", "벨기에 EUR", "아일랜드 EUR", "슬로바키아 EUR", "리투아니아 EUR", "핀란드 EUR", //유럽 코드 1
            "영국 GBP",  "스위스 CHF", "스웨덴 SEK", "체코 CZK", "덴마크 DKK", "노르웨이 NOK", "러시아 RUB", "폴란드 PLN", //유럽 코드 1
            "일본 JPY", "중국 CNY", "홍콩 HKD", "대만 TWD",  "몽골 MNT", "카자흐스탄 KZT", "인도 INR","파키스탄 PKR", // 동아시아 코드 2
            "태국 THB", "싱가포르 SGD", "말레이시아 MYR", "인도네시아 IDR",  "브루나이 BND", "베트남 VND",// 동남 아시아 코드 3
            "오만 OMR",  "터키 TRY", "이스라엘 ILS", "사우디아라비아 SAR", "쿠웨이트 KWD", "바레 BHD",  "아랍에미리트 AED", "요르단 JOD",  "카타르 QAR", //중동 코드 4
            "호주 AUD", "뉴질랜드 NZD", //오세아니아 코드 5
            "캐나다 CAD", "미국 USD", //북아메리카 코드 6
            "칠레 CLP", "브라질 BRL",  // 남아메리카 코드 7
            "이집트 EGP", "남아공 ZAR", // 아프리카 코드 8
    };
    String[] school_item = { "가톨릭대학교", "감리교신학대학교", "건국대학교", "경희대학교", "고려대학교", "광운대학교", "국민대학교",
            "덕성여자대학교", "동국대학교", "동덕여자대학교", "명지대학교", "삼육대학교", "상명대학교", "서강대학교",
            "서경대학교", "서울과학기술대학교", "서울교육대학교", "서울기독대학교", "서울대학교", "서울시립대학교",
            "서울여자대학교", "서울한영대학교", "성공회대학교", "성균관대학교", "성신여자대학교", "세종대학교", "숙명여자대학교",
            "숭실대학교", "연세대학교", "이화여자대학교", "장로회신학대학교", "중앙대학교", "총신대학교", "추계예술대학교",
            "한국성서대학교", "한국외국어대학교", "한국체육대학교", "한성대학교", "한양대학교", "홍익대학교"};

    EditText exchangeInput;
    EditText schoolInput;
    InputMethodManager im;

    Number minCost;
    Number maxCost;

    public SearchByValue(Context context) {
        this.context = context;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    Intent intent1 = new Intent(context, MainActivity.class);
                    context.startActivity(intent1);
                case R.id.navigation_search:
                    Intent intent2 = new Intent(context, SearchByValue.class);
                    context.startActivity(intent2);
                /*case R.id.navigation_write:
                    Intent intent3 = new Intent(context, WritePostActivity.class);
                    context.startActivity(intent3);
                case R.id.navigation_mypage:
                    Intent intent4 = new Intent(context, MypageActivity.class);
                    context.startActivity(intent4);*/
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_value);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


/*        exchangeInput = (EditText)findViewById(R.id.search_exchangeInput);
        schoolInput =  (EditText)findViewById(R.id.search_schoolInput);

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.search_rangeSeekbar3);

        // get min and max text view
        final TextView tvMin = (TextView)findViewById(R.id.textMin);
        final TextView tvMax = (TextView)findViewById(R.id.textMax);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                minCost = minValue;
                tvMax.setText(String.valueOf(maxValue));
                maxCost = maxValue;
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        AutoCompleteTextView exchangeView = (AutoCompleteTextView) findViewById(R.id.search_exchangeInput);

        exchangeView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, exchangeRate));

        MultiAutoCompleteTextView schoolView =
                (MultiAutoCompleteTextView) findViewById(R.id.search_schoolInput);
        schoolView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        schoolView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, school_item));

        im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);*/
    }
}
