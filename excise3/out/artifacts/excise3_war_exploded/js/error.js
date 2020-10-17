window.onload = function() {
		countDown(10); 
		var jk = document.getElementById("jump");
		var url_info = document.getElementById("url_info");
		jk.onclick = function() {
		window.location.href=url_info.innerText;
		}
	}
		
	function countDown(secs) {
		var now = document.getElementById("secs_Now");
		now.innerText=secs;
		secs--;
		if(secs>0) {
			setTimeout("countDown("+secs+")",1000);
		}
	}