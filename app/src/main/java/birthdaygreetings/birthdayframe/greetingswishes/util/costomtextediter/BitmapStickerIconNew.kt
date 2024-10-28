package birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.MotionEvent

/**
 * @author wupanjie
 */
class BitmapStickerIconNew(drawable: Drawable?, @Gravity gravity: Int) : birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.DrawableSticker(drawable),
    birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.StickerIconEvent {

    @Retention(AnnotationRetention.SOURCE)
    annotation class Gravity

    var iconRadius = DEFAULT_ICON_RADIUS
    var iconExtraRadius = DEFAULT_ICON_EXTRA_RADIUS
    var x = 0f
    var y = 0f

    @get:Gravity
    @Gravity
    var position = LEFT_TOP
    var iconEvent: birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.StickerIconEvent? = null

    init {
        position = gravity
    }

    fun draw(canvas: Canvas, paint: Paint?) {
        canvas.save()
        canvas.drawCircle(x, y, iconRadius, paint!!)
        canvas.restore()
        super.draw(canvas)
    }

    override fun onActionDown(stickerView: birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.TextStickerView, event: MotionEvent) {
        if (iconEvent != null) {
            iconEvent!!.onActionDown(stickerView, event)
        }
    }

    override fun onActionMove(stickerView: birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.TextStickerView, event: MotionEvent) {
        if (iconEvent != null) {
            iconEvent!!.onActionMove(stickerView, event)
        }
    }

    override fun onActionUp(stickerView: birthdaygreetings.birthdayframe.greetingswishes.util.costomtextediter.TextStickerView, event: MotionEvent) {
        if (iconEvent != null) {
            iconEvent!!.onActionUp(stickerView, event)
        }
    }

    companion object {
        const val DEFAULT_ICON_RADIUS = 30f
        const val DEFAULT_ICON_EXTRA_RADIUS = 10f
        const val LEFT_TOP = 0
        const val RIGHT_TOP = 1
        const val LEFT_BOTTOM = 2
        const val RIGHT_BOTTOM = 3
    }
}