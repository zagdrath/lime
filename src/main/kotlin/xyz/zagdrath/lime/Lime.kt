/*
 * Copyright (C) 2022 Cody L. Wellman All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package xyz.zagdrath.lime

import xyz.zagdrath.lime.util.CameraMode
import xyz.zagdrath.lime.util.CameraStream
import xyz.zagdrath.lime.util.LEDMode
import xyz.zagdrath.lime.util.Pipeline
import xyz.zagdrath.lime.util.SnapshotMode
import xyz.zagdrath.lime.util.constants.*
import xyz.zagdrath.lime.util.limemath.*

class Lime(tableName: String) {
    private var limeTable: LimeTable
    private lateinit var defaultInstance: Lime

    init {
        limeTable = LimeTable(tableName)
    }

    constructor() : this("limelight") {
        limeTable = LimeTable()
    }

    fun getInstance(): Lime {
        defaultInstance = Lime()
        return defaultInstance
    }

    fun getInstance(tableName: String): Lime {
        return Lime(tableName)
    }

    fun getValidTarget(): Boolean {
        return (limeTable.validTarget.getDouble(0.0) > 0.5)
    }

    fun getTargetX(): Double {
        return limeTable.targetX.getDouble(0.0)
    }

    fun getTargetY(): Double {
        return limeTable.targetY.getDouble(0.0)
    }

    fun getTargetArea(): Double {
        return clampValue((limeTable.targetArea.getDouble(0.0) / 100.0), 0.0, 1.0)
    }

    fun getTargetSkew(): Double {
        return limeTable.targetSkew.getDouble(0.0)
    }

    fun getLatency(): Double {
        return (limeTable.latency.getDouble(0.0) + CAPTURE_LATENCY)
    }

    fun getShortSideLength(): Double {
        return limeTable.shortSideLength.getDouble(0.0)
    }

    fun getLongSideLength(): Double {
        return limeTable.longSideLength.getDouble(0.0)
    }

    fun getHorizSideLength(): Double {
        return limeTable.horizSideLength.getDouble(0.0)
    }

    fun getVertSideLength(): Double {
        return limeTable.vertSideLength.getDouble(0.0)
    }

    fun getTargetXCorners(): DoubleArray {
        return limeTable.xCorners.getDoubleArray(DoubleArray(0))
    }

    fun getTargetYCorners(): DoubleArray {
        return limeTable.yCorners.getDoubleArray(DoubleArray(0))
    }

    fun setLEDMode(ledMode: LEDMode) {
        limeTable.ledMode.setNumber(ledMode.getValue())
    }

    fun setCameraMode(cameraMode: CameraMode) {
        limeTable.cameraMode.setNumber(cameraMode.getValue())
    }

    fun setSnapshotMode(snapshotMode: SnapshotMode) {
        limeTable.snapshotMode.setNumber(snapshotMode.getValue())
    }

    fun setCameraStream(cameraStream: CameraStream) {
        limeTable.cameraStream.setNumber(cameraStream.getValue())
    }

    fun setPipeline(pipeline: Pipeline) {
        if (!pipeline.equals(Pipeline.INVALID_PIPELINE)) {
            limeTable.pipeline.setNumber(pipeline.getValue())
        }
    }

    fun getPipeline(): Pipeline {
        var pipelineValue: Double = limeTable.getPipeline.getDouble(0.0)
        var pipelineID: Int = (pipelineValue + 0.5).toInt()
        when (pipelineID) {
            0 -> return Pipeline.PIPELINE_0
            1 -> return Pipeline.PIPELINE_1
            2 -> return Pipeline.PIPELINE_2
            3 -> return Pipeline.PIPELINE_3
            4 -> return Pipeline.PIPELINE_4
            5 -> return Pipeline.PIPELINE_5
            6 -> return Pipeline.PIPELINE_6
            7 -> return Pipeline.PIPELINE_7
            8 -> return Pipeline.PIPELINE_8
            9 -> return Pipeline.PIPELINE_9
            else -> {
                return Pipeline.INVALID_PIPELINE
            }
        }
    }
}
