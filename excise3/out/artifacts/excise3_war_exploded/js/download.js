window.onload = function(){
	var count = document.getElementsByClassName("star");
	var star_show = document.getElementsByTagName("ul");
	for(var i=0;i<count.length;i++) {
		var len = eval(count[i].innerHTML);
		var show = star_show[i].getElementsByTagName('li');
		for(var k=0;k<len;k++) {
			show[k].setAttribute("class","fa fa-star");
			show[k].style.color='#ffff00';
		}
	}
}