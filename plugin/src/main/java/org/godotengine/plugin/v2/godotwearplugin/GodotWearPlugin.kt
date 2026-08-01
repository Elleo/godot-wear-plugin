package org.godotengine.plugin.v2.godotwearplugin

import android.util.Log
import android.widget.Toast
import android.content.Context
import android.content.pm.PackageManager
import androidx.wear.ambient.AmbientLifecycleObserver
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.UsedByGodot

class GodotWearPlugin(godot: Godot): GodotPlugin(godot) {

    override fun getPluginName() = BuildConfig.GODOT_PLUGIN_NAME

    @UsedByGodot
    fun isWear(): Boolean {
        return getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_WATCH);
    }

    @UsedByGodot
    fun enableAOD() {

    }

    @UsedByGodot
    fun disableAOD() {

    }

    @UsedByGodot
    fun AODActive(): Boolean {
        return false;
    }
}
