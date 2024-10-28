package birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter;

import android.view.MotionEvent;

public class ZoomIconEvent implements StickerIconEvent {
    @Override
    public void onActionDown(TextStickerView stickerView, MotionEvent event) {

    }

    @Override
    public void onActionMove(TextStickerView stickerView, MotionEvent event) {
        stickerView.zoomAndRotateCurrentSticker(event);
    }

    @Override
    public void onActionUp(TextStickerView stickerView, MotionEvent event) {
        if (stickerView.getOnStickerOperationListener() != null) {
            stickerView.getOnStickerOperationListener()
                    .onStickerZoomFinished(stickerView.getCurrentSticker());
        }
    }
}