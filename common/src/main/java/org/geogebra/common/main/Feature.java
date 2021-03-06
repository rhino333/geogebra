package org.geogebra.common.main;

public enum Feature {
	ALL_LANGUAGES,

	LOCALSTORAGE_FILES,

	TUBE_BETA,

	EXERCISES,

	TOOL_EDITOR,

	KOREAN_KEYBOARD,

	POLYGON_TRIANGULATION,

	DATA_COLLECTION,

	IMPLICIT_SURFACES,

	CONTOUR_PLOT_COMMAND,

	LOG_AXES,

	HIT_PARAMETRIC_SURFACE,

	PARAMETRIC_SURFACE_IS_REGION,

	EXAM_TABLET,

	TOOLBAR_ON_SMALL_SCREENS,

	ACRA,

	ANALYTICS,

	SAVE_SETTINGS_TO_FILE,

	HANDWRITING,

	AV_DEFINITION_AND_VALUE,

	CONVEX_HULL_3D,

	/** MOB-637 */
	DIFFERENT_AXIS_RATIO_3D,

	/** GGB-334, TRAC-3401 */
	ADJUST_WIDGETS,

	/** GGB-944 */
	EXPORT_ANIMATED_PDF,

	/** GGB-776 */
	ABSOLUTE_TEXTS,

	/** MOB-601 */
	MOBILE_LOCAL_SAVE,


	/** MOB-788 */
	MOBILE_USE_FBO_FOR_3D_IMAGE_EXPORT,

	/** GGB-1236 */
	DRAGGING_NON_MOVEABLE_OBJECT_SPIN_THE_VIEW,

	/** GGB-1288 */
	ADJUST_VIEWS,


	/** GGB-1263 */
	AUTOSCROLLING_SPREADSHEET,

	/** GGB-1252 */
	KEYBOARD_BEHAVIOUR,

	/** MOW */
	WHITEBOARD_APP,

	MOBILE_CACHE_FEATURED,


	/**
	 * GGB-1374
	 */
	READ_OBJECT_NAME_AT_SELECTING,

	/**
	 * GGB-1398 + GGB-1529
	 */
	SHOW_ONE_KEYBOARD_BUTTON_IN_FRAME,

	/** MOW-97 */
	ERASER, ROUNDED_POLYGON,

	/** MOW-175 */
	MOW_CONTEXT_MENU,

	/** GGB- 1501 */
	DIALOGS_OVERLAP_KEYBOARD,

	/** MOV-169 */
	DYNAMIC_STYLEBAR,

	/** MOW-29 */
	MOW_TOOLBAR,

	MOW_PEN_IS_LOCUS,

	MOW_PEN_EVENTS,

	/** MOW-105 */
	MOW_PEN_SMOOTHING,

	/** GGB-1617 */
	AUTOMATIC_DERIVATIVES,

	/** SolveQuartic in CAS GGB-1635 */
	SOLVE_QUARTIC,

	/** MOW-166 */
	MOW_AXES_STYLE_SUBMENU,

	/** MOW-55 */
	MOW_BOUNDING_BOXES,


	/** AND-193 */
	AND_TRACE_IN_PROPERTIES,

	/** MOW-239 */
	MOW_IMPROVE_CONTEXT_MENU,

	/** MOW-251, MOW-197 */
	MOW_CLEAR_VIEW_STYLEBAR,

	/** MOW-197 */
	MOW_COLORPOPUP_IMPROVEMENTS,

	/** AND-278 */
	AND_GEOMETRY_IN_MATH_APPS_MENU,

	/** MOW-88 */
	MOW_DIRECT_FORMULA_CONVERSION,

	/** GGB-1572 */
	FIXED_OBJECTS_EDITABLE,

	/** GGB-1671 */
	AV_CONTEXT_MENU,

	MOBILE_AV_EDITOR,

	/** GGB-1662 */
	AV_PLUS,

	/** AND-247 */
	AND_KILL_TOOLBAR,

	/** GGB-1697 */
	AV_ITEM_DESIGN,

	/** Dynamic stylebar added for locked geos too */
	LOCKED_GEO_HAVE_DYNAMIC_STYLEBAR,

	/** GGB-1686 */
	TICK_NUMBERS_AT_EDGE,

	/** GGB-1756 */
	STORE_IMAGES_ON_APPS_PICKER,

	/** GGB-1732 */
	AV_PLAY_ONLY,

	/** GGB-1810 */
	AV_TEXT_ITEM,

	/** GGB-1773 */
	UNBUNDLING,

	EXPORT_SCAD,

	INPUT_BAR_SOLVE,


	/** AND-343 */
	AND_SNACKBAR,
	
	/** MOB-1227 */
	MOB_SELECT_TOOL,

	/** GGB-1868 */
	SELECT_TOOL,

	/** AND-174 */
	AND_KEEP_SIGNED_IN_WHEN_NO_CONNECTION,

	/** MOB-1241 */
	MOB_INPUT_BAR_SOLVE,

	/** /GGB-1895 Div[], Mod[], Division[] of polynomials */
	NON_CAS_POLYNOMIAL_DIVISION,

	/** GGB-1876 */
	DOUBLE_ROUND_BRACKETS,

	/** GGB-1708 */
	INPUT_BAR_ADD_SLIDER,

	/** GGB-1880 */
	AXES_NUMBERS_WHITE_BACKGROUND,

	/** GGB-1881 */
	MINOR_GRIDLINES,

	AND_FOCUS_ON_BIND,

	/** AND-423 */
	AND_SPEED_UP_AV,

	/** GGB-1916 */
	DEFAULT_OBJECT_STYLES,
	
	/** GGB-2008 */
	OBJECT_DEFAULTS_AND_COLOR,

	/** AND-422 */
	AND_COLLECT_ADAPTER_NOTIFICATIONS,

	ZOOM_PANEL,

	MOB_TOOLSET_LEVELS, SHOW_STEPS,

	/** GGB-1907 */
	DYNAMIC_STYLEBAR_SELECTION_TOOL,

	/** GGB-1910 */
	LABEL_SETTING_ON_STYLEBAR,

	/** GGB-1958 */
	ONLY_ONE_ZERO,

	/** GGB-1960 */
	DELETE_BUTTON_BEHAVIOR_FIX,

	/** GGB-1966 */
	FUNCTIONS_DYNAMIC_STYLEBAR_POSITION,

	/** AND-361*/
	AND_TRANSPARENT_STATUSBAR,

	CENTER_STANDARD_VIEW,

	AND_MOVE_FAB,

	LABEL_NAME_CAPTION, SURFACE_2D,

	/** GGB-1970*/
	ARROW_OUTPUT_PREFIX,

	/** AND-479*/
	AND_EXAM_MODE,

	/** GGB-1935 */
	LATEX_ON_KEYBOARD,

	/** GGB-1948 */
	MINOR_GRIDLINES_FIXES,

	/** GGB-1917*/
	OBJECT_HIGHLIGHT,

	AND_AV_ITEM_MENU,

	/** GGB-1982 */
	OPENING_DYNAMIC_STYLEBAR_ON_FIXED_GEOS,
	/** GGB-1982 */
	CLOSE_LABEL_DIALOG_AT_ESC,
	
	/** GGB-1983 */
	DYNAMIC_STYLEBAR_POSITION_MULTISELECT,
	/** GGB-1983 */
	DYNAMIC_STYLEBAR_MULTISELECTION_BUGS,

	/** GGB-1985*/
	FLOATING_SETTINGS,

	/** GGB-1989*/
	GLOBAL_SETTINGS,

	AND_COMPACT_AV_OUTPUT,

	/** GGB-1975 */
	HIGHLIGT_IMPROVEMENTS,

	/** GGB-2005 */
	TOOLTIP_DESIGN,

	/** GGB-1986 */
	DIALOG_DESIGN,

	INITIAL_PORTRAIT,

	/** MOW-261 */
	MOW_COLOR_FILLING_LINE,

	/** GGB-2015 */
	GEO_AV_DESCRIPTION,

	/**
	 * AND-364
	 */
	MOB_EV_SETTINGS_POPUP,

	/** GGB-2039 */
	FLOATING_MOVE_BUTTON_ANIM,

	/** AND-465 */
	MOB_DYNAMIC_SYLEBAR
}
