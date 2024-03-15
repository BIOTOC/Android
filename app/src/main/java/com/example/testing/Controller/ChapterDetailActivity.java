package com.example.testing.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testing.Model.Chapter;
import com.example.testing.R;

public class ChapterDetailActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("chapterId")) {
            int chapterId = intent.getIntExtra("chapterId", -1);
            String chapterDetail = intent.getStringExtra("chapterDetail");

            TextView chapterIdTextView = findViewById(R.id.chapter_id_text_view);
            TextView chapterDetailTextView = findViewById(R.id.chapter_detail_text_view);

            // Hiển thị thông tin chương
            chapterIdTextView.setText("Chapter " + chapterId);
            chapterDetailTextView.setText(chapterDetail);
        }

    }

    private Chapter getChapterDetail(int chapterId) {
        return new Chapter(chapterId,"Triệu Mặc Sênh lặng người nhìn cặp trai gái đứng trước quầy bán rau, một lần nữa chị cảm nhận sự lạ lùng của số phận. Bảy năm trước, chính họ khiến chị quyết định ra đi. Bây giờ họ lại cùng nhau đi mua sắm, vậy là cuối cùng họ vẫn cùng nhau! May mà hồi ấy chị bỏ ra đi, nếu không…Mặc Sênh không dám nghĩ thêm…\n" +
                "\n" +
                "Hà Dĩ Thâm, Hà Dĩ Văn, sao mình ngốc thế, tại sao cứ một mực cho rằng hai người ấy tên giống nhau thì nhất định là anh em?\n" +
                "\n" +
                "“Chúng tôi không phải là anh em, trước đây hai gia đình chúng tôi là hàng xóm của nhau, đều họ Hà cho nên cũng đặt tên cho các con giống nhau. Về sau, cha mẹ Dĩ Thâm đột ngột qua đời, cha mẹ tôi nhận nuôi Dĩ Thâm.”\n" +
                "\n" +
                "“Chị tưởng, chị mạnh hơn tình cảm hai mươi năm giữa tôi và Dĩ Thâm sao?”\n" +
                "\n" +
                "“Hôm nay tôi chính thức cho chị biết, tôi yêu Dĩ Thâm, nhưng tôi không muốn yêu thầm yêu vụng. Tôi và chị sẽ cạnh tranh công khai.”\n" +
                "\n" +
                "Năm19 tuổi, một ngày trước sinh nhật Mặc Sênh, cô bạn gái Hà Dĩ Văn vốn điềm đạm, bỗng nhiên thẳng thắn tuyên bố với chị. Một người dịu dàng, không bao giờ tranh giành với ai như Dĩ Văn mà quả quyết như vậy, chắc hẳn cô ấy phải yêu Dĩ Thâm nhiều lắm.\n" +
                "\n" +
                "Còn chị? Chị có gì để cạnh tranh với Dĩ Văn? Chính vào ngày Dĩ Văn tuyên chiến, chị đã thua, sau đó chị đã chạy trốn sang Mỹ suốt bảy năm trời.\n" +
                "\n" +
                "“Ôi, Hà Dĩ Thâm” – Nghĩ đến ánh mắt lạnh lùng, những lời nói tuyệt tình của anh ta, Mặc Sênh thấy nhoi nhói trong lòng. Tuy cảm giác rất mơ hồ, khó nhận ra, nhưng chắc chắn là có thật.\n" +
                "\n" +
                "Anh đi về phía chị, bàn tay Mặc Sênh nắm chặt tay đẩy xe hàng đến mức các đầu khớp ngón tay trắng bệch, như sắp long ra. Nhưng siêu thị lúc đó quá đông, chị lại đang đẩy cái xe chứa hàng nên hoàn toàn không thể dễ dàng quay người bỏ chạy. Tuy nhiên ngay lập tức chị nghĩ, vì sao mình phải lẩn tránh? Mình nên bình thản nói với họ một câu đại loại: “Ồ, đã lâu không gặp.”\n" +
                "\n" +
                "Rồi kiêu hãnh quay đi, để lại cho họ một hình ảnh đẹp về mình mới phải.\n" +
                "\n" +
                "Nhưng, có thể họ không nhận ra mình. Có thể lắm chứ, mình đã thay đổi nhiều, mái tóc dài buông xõa năm xưa giờ biến thành mái tóc ngắn chấm tai, làn da trắng nõn ngày nào giờ đã sạm đi nhiều bởi cái nắng bang California, lại còn quần bò, áo phông rộng thùng thình, giày thể thao, khác xưa nhiều quá!\n" +
                "\n" +
                "Họ bước từng bước, chầm chậm tiến lại gần nhau, rồi… lướt qua nhau.\n" +
                "\n" +
                "Đau đớn quá!\n" +
                "\n" +
                "Hình như có tiếng nói vẳng lại.\n" +
                "\n" +
                "“Có cần mua thêm sữa không?” – Đúng là tiếng Dĩ Văn rồi, vẫn nhỏ nhẹ như xưa.\n" +
                "\n" +
                "“…”\n" +
                "\n" +
                "Chị không nghe rõ câu trả lời. Nhớ quá, giọng nói trầm ấm như tiếng vĩ cầm của Dĩ Thâm luôn vẳng bên tai Mặc Sênh trong suốt bảy năm chị lưu lạc xứ người.\n" +
                "\n" +
                "Hẫng hụt, nhưng đồng thời cũng thấy nhẹ nhõm. Mặc Sênh ngẩng đầu nãy giờ vẫn cúi, quả quyết bước đi.\n" +
                "\n" +
                "“Rầm”, chiếc xe đẩy xô vào đống xà phòng hạ giá chất như núi trên lối đi. Thủ phạm là chị đứng ngây nhìn mấy trăm bánh xà phòng đổ tung toé, ngổn ngang .\n" +
                "\n" +
                "Tệ thật, liệu có thể vờ tỏ ra không biết mình là người gây ra chuyện?\n" +
                "\n" +
                "“Trời ơi, đây là lần thứ ba trong ngày rồi.” – Người quản lý siêu thị không biết từ đâu chạy đến nói như rên lên.\n" +
                "\n" +
                "Cho nên, cũng không nên trách người ta, sao lại chất hàng ngay giữa lối đi như vậy. Mặc Sênh lẩm bẩm, cố làm ra vẻ hối hận.\n" +
                "\n" +
                "Cảnh tượng đương nhiên thu hút sự chú ý của những người xung quanh, trong đó có Dĩ Văn. Dĩ Văn liếc nhìn về phía có nhiều tiếng ồn ào, bỗng giật mình – Cô ta! Có phải cô ta không? Dĩ Văn dường như không tin vào mắt mình… Đúng là cô ta! Trở về rồi ư?\n" +
                "\n" +
                "“Chuyện gì thế Dĩ Văn?” – Hà Dĩ Thâm, không hiểu, quay sang hỏi, bất giác nhìn theo ánh mắt của Dĩ Văn.\n" +
                "\n" +
                "Thân hình cao lớn của Dĩ Thâm bỗng khựng lại.\n" +
                "\n" +
                "Triệu Mặc Sênh!\n" +
                "\n" +
                "Người phụ nữ cúi đầu như một đứa trẻ mắc lỗi kia chẳng phải là Triệu Mặc Sênh? Vẻ mặt thiếu tự nhiên, mắt thấp thoáng nụ cười tinh quái không thể chối cãi. Từ xa khó nhìn rõ nét mặt cô ta, nhưng Dĩ Thâm biết. Anh vẫn biết, cô ta là vậy, sau khi khuấy đảo cho nước ao đục ngầu rồi bỏ đi một cách vô trách nhiệm, ương ngạnh, ích kỷ và đáng ghét.\n" +
                "\n" +
                "Chẵn bảy năm, cô ta vẫn còn nhớ đường về ư?\n" +
                "\n" +
                "Hà Dĩ Thâm gọi: “Dĩ Văn, về thôi!”\n" +
                "\n" +
                "Dĩ Văn kinh ngạc nhìn vẻ mặt bình thản của Dĩ Thâm: “Anh không định đến chào một câu ư? Hay là…”\n" +
                "\n" +
                "“Cô ấy từ lâu đã không còn tồn tại trong cuộc sống của anh.” – Giọng Dĩ Thâm dửng dưng, có vẻ như không có chuyện gì thật .\n");
    }
}