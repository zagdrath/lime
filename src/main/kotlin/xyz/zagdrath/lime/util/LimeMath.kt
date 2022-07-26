/*
 * Copyright (C) 2022 Cody L. Wellman All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package xyz.zagdrath.lime.util.limemath

fun clampValue(value: Int, min: Int, max: Int): Int {
    return Math.max(min, Math.min(value, max))
}

fun clampValue(value: Double, min: Double, max: Double): Double {
    return Math.max(min, Math.min(value, max))
}

fun getTargetDistance(
        cameraAngle: Double,
        cameraHeight: Double,
        targetAngle: Double,
        targetHeight: Double
): Double {
    return (targetHeight - cameraHeight) / Math.tan(Math.toRadians(cameraAngle + targetAngle))
}
