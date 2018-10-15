$(document).ready(function(){
	console.log("JQUERY IS READY!");
	
	$("#login-button").click(function(e) {
		console.log('$("#login-button").click');
		openPopup();
	});
	$("#register-button").click(function (e) {
		console.log('$("#register-button").click');
		window.opener.location.href = "/loginsystem/register.php";
    });
	$("#modal-close-button").click(function(e) {
		console.log('$("#modal-close-button").click');
		closePopup();
	});
	$("#overlay-bg").click(function(e) {
		console.log('$("#overlay-bg").click');
		closePopup();
	});
	$(window).resize(function(){
		updatePopup();
	});
});
function openPopup(){
	$("#login-button").prop("disabled", true);
	$("#popup-content").fadeIn();
	$("#overlay-bg").fadeIn();
	updatePopup();
}
function closePopup(){
    $("#login-button").prop("disabled", false);
    $("#login-button").fadeIn();
	$("#popup-content").fadeOut();
	$("#overlay-bg").fadeOut();
}
function updatePopup(){
	$popupContent = $("#popup-content");
	// http://api.jquery.com/height/
	// http://api.jquery.com/outerheight/
	//var top = $(window).height() / 2 - $popupContent.outerHeight() / 2; // Center vertical
	var top = "50px"; // Fixed offset
	// http://api.jquery.com/width/
	//  http://api.jquery.com/outerWidth/
	var left = ($(window).width() - $popupContent.outerWidth()) / 2; // Center horizontal
	$popupContent.css({
		'top' : top,
		'left' : left
	});
}
