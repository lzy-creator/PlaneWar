			function reload(){
				location.reload();
			}									
			function keydownEvent(e){
				var ev = e || event;
				var keyCode = ev.keyCode;
				
				//向上飞
				if(keyCode == Contances.W_KEYCODE){					
					goingUp    = true;									
				}
				//向下飞
				if(keyCode == Contances.S_KEYCODE){					
					goingDown  = true;					
				}
				//向左飞
				if(keyCode == Contances.A_KEYCODE){					
					goingLeft  = true;				
				}
				//向右飞
				if(keyCode == Contances.D_KEYCODE){					
					goingRight = true;					
				}	
			}
			
			function keyupEvent(e){
				var ev = e || event;
				var keyCode = ev.keyCode;
				
				if(keyCode == Contances.W_KEYCODE){
					goingUp    = false;
				}
				
				if(keyCode == Contances.S_KEYCODE){
					goingDown  = false;
				}
				
				if(keyCode == Contances.A_KEYCODE){
					goingLeft  = false;
				}
				
				if(keyCode == Contances.D_KEYCODE){
					goingRight = false;
				}
			}
			
			//设置游戏的过程
			var gameInterval = setInterval(function(){
				
				if(goingUp){
					//1.获取原来的bottom(将坐标转换为数字)
					var y = getY($(planeId)) + Contances.STEP;
					//边界碰撞检测
					if(y > Contances.BG_HEIGHT - Contances.PLANE_HEIGHT){
						return;
					}
					//3.把新的坐标值重新赋给plane
					setY($(planeId),y);	
				}
				
				if(goingDown){
					
					var y = getY($(planeId)) - Contances.STEP;
					
					if(y < 10){
						return;
					}
					
					setY($(planeId),y);
				}
				
				if(goingLeft){				
					
					var x = getX($(planeId)) - Contances.STEP;
					
					if(x<0){
						return;
					}
					
					setX($(planeId),x);
				}
				
				if(goingRight){
					
					var x = getX($(planeId)) + Contances.STEP;
					
					if(x>Contances.BG_WIDTH - Contances.PLANE_WIDTH){
						return ;
					}
					
					setX($(planeId),x);
				}
				
				//扫描全局子弹，让子弹飞
				var bullets = document.getElementsByClassName('bullet');
				
				if(bullets){
					for (var i = 0;i<bullets.length;i++) {
						
						var y = getY(bullets[i]) + Contances.BULLET_STEP
						
						if(y>Contances.BG_HEIGHT - 10){
							$('container').removeChild(bullets[i]);
							continue;
						}
						
						setY(bullets[i],y);
						
						//扫描所有子弹是否和敌机发生碰撞。
						
						//遍历所有敌机
						var enemies = document.getElementsByClassName("enemy");
						for (var j = 0;j < enemies.length;j++) {
							
							var x1 = getX(enemies[j]);
							var y1 = getY(enemies[j]);
							
							var x2 = getX(bullets[i]);
							var y2 = y;
							
							if(Math.abs( x1 - x2 ) < Contances.ENEMY_WIDTH && Math.abs( y1 - y2 ) < Contances.ENEMY_HEIGHT){
								
								enemies[j].src = "img/smallboom.gif";
								
								
								var currentScore = $('score').innerHTML -0;
								currentScore += 10;
								
								Contances.ENEMY_STEP = 2 + Math.floor(currentScore/200);
								
								$('score').innerHTML = currentScore;
								
							
							}
							
							var x3 = getX($(planeId));
							var y3 = getY($(planeId));
							if(Math.abs( x1 - x3 ) < Contances.ENEMY_WIDTH && Math.abs( y1 - y3 ) < Contances.ENEMY_HEIGHT){
								var score = $('score').innerHTML;	 
								$(planeId).src = "img/myplaneboom.gif";
								$('container').innerHTML = "<p style=\"color:red;text-align: center;\">you lose!</p>";
								clearInterval(gameInterval);
								clearInterval(bulletTimer);
								clearInterval(enemymoveTimer);
								clearInterval(enemyboomTimer);
								clearInterval(enemyreTimer);
								clearInterval(planeboomTimer);
								
								$('container').innerHTML += "<p style=\"color:green;text-align: center;\">  您本次的分数为"+score+"分   </p>";
								
								$('container').innerHTML += "<button style=\"margin:0 auto\" onclick=\"reload()\">再试一次</button>";
							}
						}
					}
				}
				
				time += 0.1;
				
				
				
			},20)
			
			
			//设置定时器，动态绘制子弹。
			var bulletTimer = setInterval(function(){
				
				var bulletX = getX($(planeId)) + Contances.PLANE_WIDTH/2 - 2;
				var bulletY = getY($(planeId)) + Contances.PLANE_HEIGHT;
					
				$('container').innerHTML += "<img class = 'bullet' style=\"position: absolute;left: "+bulletX+"px;bottom: "+bulletY+"px;\"  src=\"img/bullet1.png\"/>"
					
			},200);
			
			
			//敌机的移动方法
			
			var enemymoveTimer = setInterval(function(){
				
				var enemies = document.getElementsByClassName('enemy');
				
				for (var i = 0 ; i < enemies.length ; i++) {
					var y = getY(enemies[i]) - Contances.ENEMY_STEP;
					setY(enemies[i],y);
					
					if(y < 0){
						$('container').removeChild(enemies[i]);
						return;
					}
					
						
						var x = getX(enemies[i]);
					
						//x = x + 10 * Math.sin(time);
					
						setX(enemies[i],x);

				}
				
				
				
			},50);
			
			
			//敌机爆炸并消失
			 var enemyboomTimer = setInterval(function(){
				
				var enemies = document.getElementsByClassName('enemy');
				
				for (var i = 0 ; i < enemies.length ; i++) {
					
					if(enemies[i].src.indexOf("img/smallboom.gif") != -1){
						$('container').removeChild(enemies[i]);
					
					}
				}
				
			},300);
			
			//我方飞机爆炸游戏结束
			var planeboomTimer = setInterval(function(){
				if($(planeId).src.indexOf("img/myplaneboom.gif") != -1){
					$('background_1').src = "img/bg_2.png";
					clearInterval();
				}
			},500);
		
		
			//自动刷新敌机方法
			var enemyreTimer = setInterval(function(){
				
				var x = Math.floor(300 * Math.random());
				
				var roll = true;
				$('container').innerHTML += "<img roll = \""+roll+"\"  class=\"enemy\" style=\"left: "+x+"px;bottom: 500px\"  src=\"img/enemy1_fly_1.png\"/>";

			},Contances.ENEMY_APPEAR_FREQ);
			