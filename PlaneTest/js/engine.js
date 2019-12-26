var Contances = {
	
	
	BG_HEIGHT : 530,
	BG_WIDTH : 300,
	
	PLANE_HEIGHT : 70,
	PLANE_WIDTH : 60,
	
	ENEMY_HEIGHT : 24,
	ENEMY_WIDTH : 34,
	
	STEP : 4,
	BULLET_STEP : 8,	
	ENEMY_STEP : 2,
	
	ENEMY_APPEAR_FREQ : 400,
	
	W_KEYCODE : 87,
	A_KEYCODE : 65,
	S_KEYCODE : 83,
	D_KEYCODE : 68,
	
}

var time = 0;

var planeId = "myplane";

var goingUp    = false;
var goingDown  = false;
var goingLeft  = false;
var goingRight = false;

function $(id){
	return document.getElementById(id);
}


function getY(flyingObject){
	return flyingObject.style.bottom.replace('px','') -0;
}

function getX(flyingObject){
	return flyingObject.style.left.replace('px','') -0;
}


function setY(flyingObject,y){
	flyingObject.style.bottom = y + 'px';
}

function setX(flyingObject,x){
	flyingObject.style.left = x + 'px';
}

