$(function()
{$("#smartLogin").click(function(e){
    e.preventDefault();

    $("#main-text").text("");
    $.ajax({url:'/smart-id/authentication/start',type:'post',dataType:'json',contentType:'application/json',data:JSON.stringify(getFormData()),success:function(data){
        toggleContainerView(data.code);console.log("Success");console.log(data);pollResult()}, error:function(data){
        console.log("Failure");console.log(data);toggleContainerView("XXXX")}
    })
})});
function pollResult(){
    $.ajax({url:'/smart-id/authentication/poll',type:'post',dataType:'json',contentType:'application/json',success:function(data){
        console.log("Success");console.log(data);console.log("redirect here");
        window.location.href="/"}, error:function(data){
        console.log("Failure");console.log(data.responseJSON);
        $("#main-text").text(data.responseJSON.errorMessage);
        toggleContainerView("XXXX")}

    })}
function getFormData()
{var formDataAsJSON={};
$.each($("form").serializeArray(),function(index,value){
    formDataAsJSON[value.name]=value.value});return formDataAsJSON
}
function sleep(miliseconds){
    var currentTime=new Date().getTime();
    while(currentTime+miliseconds>=new Date().getTime()){

    }}
function toggleContainerView(code){
    $("#verification-code-text").text(code);
    $("#smart-id-verification-code-container").toggle();
    $("#smart-id-login-container").toggle()
}
