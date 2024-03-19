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
        return new Chapter(chapterId, "- Cha, trời mưa xuống hái thuốc là một thời cơ tốt, chí ít không cần cướp đến bể đầu chảy máu cùng người khác.\n" +
                "\n" +
                "Người đàn ông trung niên này tên là Trầm Thiên Hổ, là phụ thân của Trầm Tường, là một võ giả cường đại danh chấn một phương, cũng là người có hy vọng kế thừa Trầm gia tộc trưởng đời kế tiếp nhất, tuy con trai hắn không có linh mạch, nhưng hắn vẫn cổ vũ Trầm Tường, vẫn thỉnh thoảng cho hắn một ít đan dược trân quý, chỉ bất quá vẫn là không làm nên chuyện gì.\n" +
                "\n" +
                "- Cầm.\n" +
                "\n" +
                "Trầm Thiên Hổ bất đắc dĩ nở nụ cười, vứt cho Trầm Tường một cái hộp nhỏ.\n" +
                "\n" +
                "Trầm Tường tiếp nhận cái hộp, cũng không thèm nhìn tới đồ vật bên trong, hắn biết bên trong là đan dược, cười cợt nói:\n" +
                "\n" +
                "- Đa tạ cha, như vậy ta cũng không cần đi trộm những con gà này của Mã lão đầu để bổ dưỡng thân thể.\n" +
                "\n" +
                "Điều này làm cho Mã quản gia kia mặt đầy cay đắng, hắn không nghĩ tới mình dĩ nhiên bị nhìn chằm chằm.\n" +
                "\n" +
                "Nhìn bóng lưng Trầm Tường biến mất, Trầm Thiên Hổ chỉ có thể thở dài, tuy hắn ở Trầm gia có địa vị rất cao, nhưng Trầm gia trưởng lão quản lý đối với những đan dược trân quý hi hữu này rất nghiêm khắc, hắn chỉ có thể lấy của bản thân cho Trầm Tường, nhưng cái này cũng không có tác dụng gì, bởi vì đan dược quá ít.\n" +
                "\n" +
                "Làm cha, cái nào không hy vọng nhi tử thành Long? Chỉ bất quá Trầm Thiên Hổ cũng không có cách nào, hắn chỉ có thể làm hết sức, tranh thủ đan dược cho Trầm Tường.\n" +
                "\n" +
                "...\n" +
                "\n" +
                "Tiên Ma Nhai, đó là một địa phương phi thường hoang vu, giờ khắc này trên vách núi cheo leo có một thiếu niên ở trần leo lên.\n" +
                "\n" +
                "\n" +
                "Lúc này rơi xuống mưa rào tầm tã, nhưng Trầm Tường leo núi như vậy, đây là một chuyện vô cùng nguy hiểm, phải biết vách núi bên dưới Tiên Ma Nhai này là sâu không thấy đáy, hơn nữa phía dưới quanh năm tràn ngập một loại khí tức mang theo tử vong, vì lẽ đó rất nhiều người đều không muốn tới gần địa phương này.\n" +
                "\n" +
                "Nhưng Trầm Tường tới nơi này hái thuốc, còn leo lên trên vách đá, chậm rãi đi xuống phía dưới, nếu để cho người khác biết, nhất định sẽ cười hắn là một kẻ không muốn sống, ai cũng biết loại địa phương chim không thèm ị này, tử khí dày đặc là chắc chắn sẽ không có linh dược tốt gì.\n" +
                "\n" +
                "Trầm Tường không những không ngốc, mà còn rất thông minh, hắn biết Tiên Ma Nhai này tồn tại rất nhiều năm, đặc biệt là những tử khí này phía dưới, càng là không ai biết tồn tại bao nhiêu năm.\n" +
                "\n" +
                "Ở bên trong nhận thức của người thường, địa phương không hề có sinh khí là không có linh dược, mà Trầm Tường lại không cho là như vậy, đạo lý vật cực tất phản hắn cũng biết, hắn khẳng định chắc chắn trên vách đá này nhất định có một loại linh dược trân quý trong truyền thuyết.\n" +
                "\n" +
                "Địa Ngục Linh Chi, loại linh dược này nghe tới rất đáng sợ, nhưng là một loại linh dược có hiệu quả tẩy kinh phạt tủy, bình thường sinh trưởng ở cổ chiến trường, bãi tha ma,… những địa phương có tử khí dày đặt, là một loại kỳ dược.\n" +
                "\n" +
                "Ngày mưa có thể làm cho một ít hắc khí trầm xuống, như vậy Trầm Tường liền có thể thấy rõ một ít vách đá sâu, hắn liền đi đến địa phương sâu phía dưới, như vậy hắn liền có thể tìm tới Địa Ngục Linh Chi kia.\n");
    }
}