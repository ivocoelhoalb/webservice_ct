function exibeNota(data){
    if ($.isArray(data)) {
        $("#retorno").empty();
        $.each(data, function(index, value) {
            $("#retorno").prepend("ID: "+value.id+"<br>Titulo: "+value.nome+"<br>Conteudo: "+value.nome+"<br><br>");
        });
    } else {
        $("#retorno").html("ID: "+data.id+"<br>Titulo: "+data.nome+"<br>Conteudo: "+data.nome+"<br><br>");
    }
    $("#loading").empty();
}

function exibeErroNota(){
    $("#retorno").html("Ops, algo de errado aconteceu.");
    $("#loading").empty();
}

function aguardaNota(){
    $("#loading").html("<img style='width: 15px; margin-left: 10px;' src='http://www.devmedia.com.br/cursos/img/loading.gif' alt='loading'>");
}

function cliqueBotao(event) {
    event.preventDefault();
    var id = $("#id_nota").val();
   
   jQuery.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:8082/ntrack/api/motorista",
        success: exibeNota,
        beforeSend: aguardaNota,
        error: exibeErroNota
    });
}

$(document).ready(function() {
    $("#enviar").on('click', cliqueBotao);
});