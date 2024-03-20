package com.example.testing.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testing.Models.Chapter;
import com.example.testing.R;

public class ChapterDetailActivity extends AppCompatActivity {

    private int chapterId;
    private ScrollView scrollView;
    private Button viewCommentsButton;
    private TextView chapterIdTextView;
    private TextView chapterDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("chapterId")) {
            chapterId = intent.getIntExtra("chapterId", -1);
            String name = intent.getStringExtra("name");

            chapterIdTextView.setText(name + " - Chapter " + chapterId);

            Chapter chapter = getChapterDetail(chapterId);
            if (chapter != null) {
                chapterDetailTextView.setText(chapter.getChapterDetail());
            }
        }
    }

    private void bindingView() {
        chapterIdTextView = findViewById(R.id.chapter_id_text_view);
        chapterDetailTextView = findViewById(R.id.chapter_detail_text_view);
        scrollView = findViewById(R.id.scrollView);
        viewCommentsButton = findViewById(R.id.view_comments_button);
        viewCommentsButton.setVisibility(View.GONE);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (isScrollViewAtBottom(scrollView)) {
                viewCommentsButton.setVisibility(View.VISIBLE);
            } else {
                viewCommentsButton.setVisibility(View.GONE);
            }
        });
    }

    private void bindingAction() {
        viewCommentsButton.setOnClickListener(v -> {
            Intent oldintent = getIntent();
            String name = oldintent.getStringExtra("name");
            Intent intent = new Intent(ChapterDetailActivity.this, CommentActivity.class);
            intent.putExtra("chapterId", chapterId);
            String.valueOf(intent.putExtra("name", name));

            startActivity(intent);
        });

    }

    private boolean isScrollViewAtBottom(ScrollView scrollView) {
        if (scrollView.getChildAt(0) != null) {
            return (scrollView.getHeight() + scrollView.getScrollY()) >= (scrollView.getChildAt(0).getHeight());
        }
        return false;
    }

    private Chapter getChapterDetail(int chapterId) {
        return new Chapter(chapterId, "Đêm đã về khuya, bóng tối phủ kín khắp nơi, bao trùm lên cảnh vật. Thế nhưng trong núi lại chẳng hề yên tĩnh, tiếng mãnh thú rít gào rung động cả non sông, cây cối rung lên, lá bay tán loạn.\n" +
                "\n" +
                "Chốn rừng núi mênh mang là nơi hoạt động của vô số mãnh thú hồng hoang cùng những chủng tộc sót lại từ thời Thái Cổ. Tiếng kêu đáng sợ của muôn loài rống lên trong bóng tối, khiến mặt đất như muốn nứt toác ra.\n" +
                "\n" +
                "Từ trong dãy núi trông xa thấy có thấp thoáng một quầng sáng nhu hòa, tựa như một ngọn nến lập lòe dưới màn đêm đen vô tận, lẩn khuất giữa muôn trùng núi, ánh sáng dường như có thể vụt tắt bất cứ lúc nào.\n" +
                "\n" +
                "Đến gần hơn, có thể thấy rõ ở đó có một nửa thân cây khô khổng lồ, đường kính thân cây ước chừng hơn chục mét, toàn thân cháy đen, ngoại trừ một nửa thân cây này, chỉ còn lại một cành cây yếu ớt nhưng lại tỏa ra sức sống. Lá cây lung linh như được khắc từ lục ngọc, tán phát từng đốm sáng nhu hòa bao trùm lấy cả một thôn làng.\n" +
                "\n" +
                "Nói một cách chính xác thì đây là một thân cây sét đánh, rất nhiều năm về trước nó từng gặp phải một trận sét thông thiên, vòm cây um tùm cùng sức sống tràn trề của gốc liễu già đã bị sấm sét phá hủy. Nay chỉ còn lại một đoạn gốc cao chừng tám chín mét trồi lên mặt đất, đường kính rộng kinh người, cành liễu duy nhất sót lại kia trông như sợi xích thần bằng mây lục bích, hào quang ngập tràn bao trùm che chở cho cả thôn làng, khiến mảnh đất này trở nên mông lung như một vùng tiên thổ, trong chốn đại hoang cảm giác vô cùng thần bí.\n" +
                "\n" +
                "Nhà nhà trong thôn đều xây bằng đá. Đêm khuya thanh vắng, vẻ an lành yên ả của chốn này dường như tách biệt hẳn với bóng tối cùng tiếng mãnh thú gào thét bên ngoài.\n" +
                "\n" +
                "\"Hú uuuuu….\"\n" +
                "\n" +
                "Một trận cuồng phong thổi qua, có đám mây đen khổng lồ vắt ngang trời, che kín cả màn đêm, chắn nốt chút xíu ánh sao yếu ớt khiến dãy núi càng thêm tăm tối.\n" +
                "\n" +
                "Một tiếng chim kêu hung tợn từ trên cao vọng xuống, tiếng kêu sắc lẻm có sức xuyên thấu mạnh, không ngờ lại bắt nguồn từ đám mây đen kia. Nhìn kỹ, thì ra đó là một con chim khổng lồ to đến không tưởng, che lấp trăng sao, dài không biết bao nhiêu dặm.\n" +
                "\n" +
                "Đi qua Thạch Thôn, nó cúi đầu nhìn xuống, hai con mắt tựa như hai vầng trăng máu, hung khí ngút trời. Nó nhìn chằm chằm gốc liễu già trong chốc lát, cuối cùng bay về phía sâu nhất trong dãy núi.\n" +
                "\n" +
                "Yên ắng một lúc lâu mãi cho đến tận nửa đêm, mặt đất bắt đầu rung chuyển. Một bóng dáng mơ hồ từ phương xa bước tới, cao ngang đỉnh núi!\n" +
                "\n" +
                "Một hơi thở kì lạ lan tỏa, núi rừng lặng ngắt một cách chết chóc, hung cầm mãnh thú cúi rạp mình không dám phát ra một chút xíu âm thanh.\n" +
                "\n" +
                "Lại gần, đây là một sinh vật có hình người, dáng đứng thẳng, nó cao lớn vô cùng, sánh ngang đỉnh núi, toàn thân nó không có lông tóc gì mà dày đặc một lớp vảy vàng kim lấp lánh. Mặt nó phẳng lì, chỉ có một con mắt thẳng, mỗi lần chớp mở như có ánh sét vàng kim rạch qua, sắc bén ghê người. Toàn thân nó huyết khí mênh mông, tựa như một vị thần ma!\n" +
                "\n" +
                "Nó đi ngang qua chốn này, liếc nhìn gốc liễu, thoáng dừng chân rồi dường như đang vội vã lên đường, cuối cùng nhanh chóng bỏ đi. Vô vàn ngọn núi như đang rên rỉ dưới từng bước chân của nó, cả chốn núi rừng cũng bị uy thế ấy làm cho run rẩy.\n" +
                "\n" +
                "Bình minh lên, một con rết dài mười mét, thân to như thùng nước, tỏa ánh sáng bạc lấp lánh bò ngoằn ngoèo trong núi. Con rết như được đúc từ bạc trắng, mỗi một đốt đều sáng bóng dữ dằn, đập vào đá núi rầm rầm, tia lửa bắn tung tóe. Nhưng cuối cùng nó lại tránh qua Thạch Thôn mà không hề xâm nhập, nơi nó đi qua sương đen mịt mù, muông thú đều lẩn tránh.\n" +
                "\n" +
                "Một cành liễu mảnh mai tỏa ánh sáng bích hà óng ánh khẽ đung đưa trong gió….");
    }
}