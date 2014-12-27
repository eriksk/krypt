package krypt

import java.util.Random


val random = Random(System.currentTimeMillis())

/**
 * Created by erikskoglund on 2014-12-27.
 */
public fun rand(): Float{
    return random.nextFloat()
}

public fun arrayOfZeroes(size: Int): Array<Int>{
    val ary = arrayListOf<Int>()
    size.times { ary.add(0) }
    return ary.copyToArray()
}

public fun lerp(x: Float, y: Float, t: Float): Float = x + (y - x) * t