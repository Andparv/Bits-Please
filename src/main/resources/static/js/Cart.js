$(document).ready(function () {
    console.log("Store");

});

$(".addbutton").click(function (e) {
    console.log('add to cart clicked');
    var id = $("#id").val();
    var model = $("#model").val();
    var amount = $("#amount").val();
    var price = $("#price").val();
    var data = {
        'id': id,
        'model': model,
        'amount': amount,
        'price': price
    };
    //$.post("/cart", data, showDone);
});
var showDone = function () {
    console.log('add to cart clicked');
    texttoshow = "!FGJDGJODFLASFAKFPWKFPAPMVLMV";
    document.getElementById("thetext").innerHTML = texttoshow;
}
