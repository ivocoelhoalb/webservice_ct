var token;


function postLogin(event) {
	var auth = {
		username : $("#login").val(),
		password : $("#pass").val()
	}

	console.log(JSON.stringify(auth));
	
	$.ajax({
		url : 'http://localhost:8082/ntrack/api/autenticacao',
		type : 'post',
		dataType : 'json',
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			token = response.token;
			window.location.href = "http://localhost:8082/ntrack/App.html";
		},
		error : function(response) {
			alert(response.responseJSON.message);
		},
		data :JSON.stringify(auth)
	});
}

$(document).ready(function() {
	$("#login_btn").on('click', postLogin);
});