// dependencies
var path = require('path');
var express = require('express');
var app = express();
var ejs = require('ejs');


// set port
var port = process.env.PORT || 8080

app.use('/static', express.static(path.join(__dirname, 'static')))
app.set('view engine', 'ejs');
// routes

app.get('/', function(req, res) {
	res.render('index');
})

app.listen(port, function() {
	console.log('app running');
})