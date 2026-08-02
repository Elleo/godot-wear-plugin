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

func _process(delta: float) -> void:
	if _wear_plugin:		
		if _wear_plugin.isAmbient():
			# Reduce framerate when in Ambient mode
			Engine.max_fps = 1
			# Hide most of the display (no more than 15% of pixels should be lit for good battery life)
			%AmbientCover.visible = true
		else:
			Engine.max_fps = 30
			%AmbientCover.visible =false

func _on_ambient_button_pressed() -> void:
	if not _wear_plugin:
		return
	
	_wear_plugin.installAmbientHandler()
	%AmbientButton.disabled = true
