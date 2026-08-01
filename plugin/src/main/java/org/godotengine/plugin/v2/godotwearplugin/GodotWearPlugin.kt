package org.godotengine.plugin.v2.godotwearplugin

import android.util.Log
import android.widget.Toast
import android.content.Context
import android.content.pm.PackageManager
import android.app.Activity
import androidx.wear.ambient.AmbientLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.UsedByGodot

class GodotWearPlugin(godot: Godot): GodotPlugin(godot) {

    private var ambientActive: Boolean = false

    private val ambientCallback = object : AmbientLifecycleObserver.AmbientLifecycleCallback {

        override fun onEnterAmbient(ambientDetails: AmbientLifecycleObserver.AmbientDetails) {
            ambientActive = true
        }

        override fun onUpdateAmbient() {
        }

        override fun onExitAmbient() {
            ambientActive = false
        }
    }

    private val ambientObserver: AmbientLifecycleObserver by lazy {
        AmbientLifecycleObserver(godot.getActivity() as Activity, ambientCallback)
    }

    override fun getPluginName() = BuildConfig.GODOT_PLUGIN_NAME

    @UsedByGodot
    fun isWear(): Boolean {
        return getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_WATCH)
    }

    @UsedByGodot
    fun enableAOD() {
        (getContext() as LifecycleOwner).lifecycle.addObserver(ambientObserver)
    }

    @UsedByGodot
    fun disableAOD() {
        (getContext() as LifecycleOwner).lifecycle.removeObserver(ambientObserver)
    }

    @UsedByGodot
    fun AODActive(): Boolean {
        return ambientActive;
    }
}
