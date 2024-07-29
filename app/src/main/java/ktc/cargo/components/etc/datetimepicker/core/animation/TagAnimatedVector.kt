package ktc.cargo.components.etc.datetimepicker.core.animation

import android.content.res.Resources
import android.util.AttributeSet
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ktc.cargo.driver.hmmdatetimepicker.datepicker.core.animation.AnimatedImageVector
import ktc.cargo.driver.hmmdatetimepicker.datepicker.core.animation.AnimatedVectorTarget
import ktc.cargo.driver.hmmdatetimepicker.datepicker.core.animation.attrs
import ktc.cargo.driver.hmmdatetimepicker.datepicker.core.animation.forEachChildOf
import ktc.cargo.driver.hmmdatetimepicker.datepicker.core.util.ExperimentalAnimationGraphicsApi
import org.xmlpull.v1.XmlPullParser

private const val TagAnimatedVector = "animated-vector"
private const val TagAnimatedVectorTarget = "target"

private fun parseAnimatedVectorTarget(
    res: Resources,
    theme: Resources.Theme?,
    attrs: AttributeSet
): AnimatedVectorTarget {
    return attrs.attrs(
        res, theme, AndroidVectorResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET
    ) { a ->
        AnimatedVectorTarget(
            a.getString(
                AndroidVectorResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET_NAME
            ) ?: "",
            loadAnimatorResource(
                theme,
                res,
                a.getResourceId(
                    AndroidVectorResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET_ANIMATION,
                    0
                )
            )
        )
    }
}

@ExperimentalAnimationGraphicsApi
internal fun XmlPullParser.parseAnimatedImageVector(
    res: Resources,
    theme: Resources.Theme?,
    attrs: AttributeSet
): AnimatedImageVector {
    return attrs.attrs(res, theme, AndroidVectorResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE) { a ->
        val drawableId = a.getResourceId(
            AndroidVectorResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_DRAWABLE,
            0
        )
        val targets = mutableListOf<AnimatedVectorTarget>()
        forEachChildOf(TagAnimatedVector) {
            if (eventType == XmlPullParser.START_TAG && name == TagAnimatedVectorTarget) {
                targets.add(parseAnimatedVectorTarget(res, theme, attrs))
            }
        }
        AnimatedImageVector(
            ImageVector.vectorResource(theme, res, drawableId),
            targets
        )
    }
}
