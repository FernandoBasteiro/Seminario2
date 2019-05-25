 function loadDiv(divName, url, postInfo, titulo) {
	 	var w = window.innerWidth;
		if (w < 768) {
			document.getElementById("accordionSidebar").className += " toggled";
			document.getElementById("page-top").className += " sidebar-toggled";
			document.getElementById("collapse1").className = "collapse";
		}
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById(divName).innerHTML = this.responseText;
			document.getElementById("titulo-pagina").innerHTML  = titulo;
			document.getElementById("titulo-pagina-chica").innerHTML  = titulo;
			}};
	    xmlhttp.open("POST", url, true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xmlhttp.send(postInfo);
}