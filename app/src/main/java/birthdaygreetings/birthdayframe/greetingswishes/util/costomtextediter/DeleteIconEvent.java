package birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter;

import android.view.MotionEvent;

public class DeleteIconEvent implements StickerIconEvent {
    @Override
    public void onActionDown(TextStickerView stickerView, MotionEvent event) {

    }

    @Override
    public void onActionMove(TextStickerView stickerView, MotionEvent event) {

    }

    @Override
    public void onActionUp(TextStickerView stickerView, MotionEvent event) {
        stickerView.removeCurrentSticker();
    }
}