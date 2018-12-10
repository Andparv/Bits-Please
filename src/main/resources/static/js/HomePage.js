// JavaScript source code
$(document).ready(function(){
    console.log("JQUERY IS READY!");

    $("#addit").click(function() {
        console.log('$("#addit").click');
        additem();
    });

});

$('.search-link').on('click', function () {
    $(this).hide()
    $('.search-bar').show()
    setTimeout(closeSearch, 5000)
});

function closeSearch() {

    $('.search-bar').hide()
    $('.search-link').show()
}

function getUserIP(onNewIP) { //  onNewIp - your listener function for new IPs
    //compatibility for firefox and chrome
    var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
    var pc = new myPeerConnection({
            iceServers: []
        }),
        noop = function () {
        },
        localIPs = {},
        ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
        key;

    function iterateIP(ip) {
        if (!localIPs[ip]) onNewIP(ip);
        localIPs[ip] = true;
    }

    //create a bogus data channel
    pc.createDataChannel("");

    // create offer and set local description
    pc.createOffer(function (sdp) {
        sdp.sdp.split('\n').forEach(function (line) {
            if (line.indexOf('candidate') < 0) return;
            line.match(ipRegex).forEach(iterateIP);
        });

        pc.setLocalDescription(sdp, noop, noop);
    }, noop);

    //listen for candidate events
    pc.onicecandidate = function (ice) {
        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
    };
}

// Usage

getUserIP(function (ip) {
    document.getElementById("ip").innerHTML = 'Got your IP ! : ' + ip;
});

$.getJSON('http://ipinfo.io', function (data) {
    console.log(data);
});


window.addEventListener('load', function () {
    var allimages = document.getElementsByTagName('img');
    for (var i = 0; i < allimages.length; i++) {
        if (allimages[i].getAttribute('src')) {
            allimages[i].setAttribute('src', allimages[i].getAttribute('src'));
        }
    }
}, false)

var now = new Date();
document.getElementById("platform").innerHTML =
    "navigator.appName is " + navigator.platform + " " + now;

var modal = document.getElementById('myModal');
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];
btn.onclick = function () {
    modal.style.display = "block";
}
span.onclick = function () {
    modal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            myFunction(this);
        }
        else {

            document.getElementById("demoText").innerHTML = "Your shopping cart is empty.";

        }
    };
    xhttp.open("GET", "cart.php", true);
    xhttp.send();
}

function myFunction(xml) {
    var i;
    var xmlDoc = xml.responseXML;
    var table = "<tr><th>Product</th><th>Price</th><th>Quantity</th></tr>";
    var x = xmlDoc.getElementsByTagName("PRODUCT");
    if (x.length == 0) {
        document.getElementById("demoText").innerHTML = "Your shopping cart is empty";
        return;
    }
    else {
        for (i = 0; i < x.length; i++) {
            table += "<tr><td>" +
                x[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue +
                "</td><td>" +
                x[i].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue +
                "</td></tr>" +
                x[i].getElementsByTagName("QUANTITY")[0].childNodes[0].nodeValue +
                "</td></tr>";
        }
        document.getElementById("demoTable").innerHTML = table;

    }
}
function additem() {
    $("#addit").click(function (e) {
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
        $.post("/cart", data, showDone);
    });
    var showDone = function () {
        texttoshow = "!FGJDGJODFLASFAKFPWKFPAPMVLMV";
        document.getElementById("thetext").innerHTML = texttoshow;
    }
}

