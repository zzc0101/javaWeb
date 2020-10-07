window.onload = function() {
	var img_change = document.getElementById("img_btn");
	img_change.onclick = function() {
		img_change.src='createVerifyImage.do?i='+Math.random();
	}
	var show = document.getElementById("img_btn");
	var p_show = document.getElementById("p_show");
	show.onmousemove = function() {
		p_show.style.display='block';
	}
	show.onmouseout = function() {
		p_show.style.display='none';
	}
}