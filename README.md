# Godot WearOS Plugin

This plugin provides some helper utilities for creating games for WearOS devices.

WearOS is just a modified version of Android, as such a game exported using the standard Godot Android template will work on all WearOS devices. This plugin helps you to manage elements specific to watches.

## Features

### WearOS Detection

The `isWear()` function will report if your game is currently running on a watch or not. This makes it easier to create games for multiple different form factors.

### Always-On-Display [NOT COMPLETE]

The plugin implements an AOD handler, which will report to your game when AOD mode is entered. This allows you to reduce your battery consumption. It is recommended that when the AOD mode is activated your game should have no more than 15% of the display's pixels lit.

## Misc

This plugin is based on the [Godot Android Plugin Template](https://github.com/m4gr3d/Godot-Android-Plugin-Template)
