extends Node2D

var _plugin_name = "GodotWearPlugin"
var _wear_plugin

func _ready():
	if Engine.has_singleton(_plugin_name):
		_wear_plugin = Engine.get_singleton(_plugin_name)
	else:
		printerr("Couldn't find plugin " + _plugin_name)

func _on_detect_button_pressed():
	if _wear_plugin:
		if _wear_plugin.isWear():
			%Label.text = "I'm running on WearOS!"
		else:
			%Label.text = "I'm not running on WearOS!"
	else:
		$Label.text = "I'm not running on Android at all!"	


func _on_aod_button_toggled(toggled_on: bool) -> void:
	if not _wear_plugin:
		return
		
	if toggled_on:
		_wear_plugin.enabledAOD()
	else:
		_wear_plugin.disableAOD()
