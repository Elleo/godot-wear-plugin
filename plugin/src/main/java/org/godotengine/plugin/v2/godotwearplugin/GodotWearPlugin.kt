package org.godotengine.plugin.v2.godotwearplugin

import android.util.Log
import android.widget.Toast
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.app.Activity
import androidx.wear.ambient.AmbientLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.godotengine.godot.Godot
import org.godotengine.godot.GodotActivity
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.UsedByGodot

class GodotWearPlugin(godot: Godot): GodotPlugin(godot) {

    private var ambientActive: Boolean = false

    private val ambientCallback = object : AmbientLifecycleObserver.AmbientLifecycleCallback {

        override fun onEnterAmbient(ambientDetails: AmbientLifecycleObserver.AmbientDetails) {
            Log.i("godot", "Enter Ambient")
            ambientActive = true
        }

        override fun onUpdateAmbient() {
            Log.i("godot", "Update Ambient")
        }

        override fun onExitAmbient() {
            Log.i("godot", "Exit Ambient")
            ambientActive = false
        }
    }

    private val ambientObserver: AmbientLifecycleObserver by lazy {
        AmbientLifecycleObserver(godot.getActivity()!!, ambientCallback)
    }

    override fun getPluginName() = BuildConfig.GODOT_PLUGIN_NAME

    @UsedByGodot
    fun isWear(): Boolean {
        Log.i("godot", "isWear()")
        return getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_WATCH)
    }

    @UsedByGodot
    fun installAmbientHandler() {
        var activity: Activity? = godot.getActivity()
        if (activity == null) {
            Log.e("godot", "Can't get Activity")
            return
        }
        try {
            activity.runOnUiThread(Runnable {
                (activity as GodotActivity).lifecycle.addObserver(ambientObserver)
            })
            Log.i("godot", "Ambient mode handler installed")
        } catch (e: Exception) {
            Log.e("godot", e.toString())
        }
    }

    @UsedByGodot
    fun isAmbient(): Boolean {
        return ambientActive;
    }
}
