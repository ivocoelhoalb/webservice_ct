var xlf = document.getElementById('xlf');
if (xlf.addEventListener)
	xlf.addEventListener('change', handleFile, false);

var map;

function myMap() {
	var mapProp = {
		center : new google.maps.LatLng(-5.2447052, -36.7909283),
		zoom : 7,
		zoomControl : true,
		mapTypeControl : true,
		scaleControl : true,
		streetViewControl : true,
		rotateControl : true,
		fullscreenControl : true
	};
	map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
	map.setMapTypeId(google.maps.MapTypeId.SATELLITE);
}
F