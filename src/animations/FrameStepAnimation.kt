package animations

/**
 * Created by erikskoglund on 2014-12-25.
 */
class FrameStepAnimation(val frames: Array<Int>, val interval: Float){

    var currentFrame = 0
    var current = 0f

    val frame: Int
        get() = frames.get(currentFrame)

    public fun reset(){
        currentFrame = 0
        current = 0f
    }

    public fun update(dt : Float){
        current += dt
        if(current > interval){
            current = 0f
            currentFrame++
            if(currentFrame > frames.size() - 1)
                reset()
        }
    }
}