package birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter;

import android.view.MotionEvent;


public interface StickerIconEvent {
    void onActionDown(TextStickerView stickerView, MotionEvent event);

    void onActionMove(TextStickerView stickerView, MotionEvent event);

    void onActionUp(TextStickerView stickerView, MotionEvent event);
}
