package animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

// 아이디와 비밀번호가 틀렸을 경우 화면이 흔들리는 기능.
public class Shake {
    private TranslateTransition tt;

    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }
    public void playAnimation() {
        tt.playFromStart();
    }
}
