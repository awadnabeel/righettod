/**
 * .:NodeJS javascript script:.<br>
 * <br>
 * 
 * Simulate a "tail" unix command line but using an HTTP remote file as input
 * source.<br>
 * <br>
 * 
 * Author: Dominique RIGHETTO <dominique.righetto@gmail.com> <br>
 * Date : May 2012<br>
 */

// Module imports
var http = require('http');

// Check input parameter
if (process.argv.length != 5) {
	console.info("Usage: node rtail.js [HOST] [PATH] [REFRESH_DELAY]");
	console.info("");
	console.info("Parameters:");
	console.info("[HOST]          : Host part of the target url.");
	console.info("[PATH]          : Path part of the target url.");
	console.info("[REFRESH_DELAY] : Refresh delay in seconds.");
	console.info("");
	console.info("Example:");
	console.info("node rtail.js localhost /test.txt 10");
	process.exit(-1);
}

// Execution parameters
var targetHost = process.argv[2];
var targetPath = process.argv[3];
var refreshDelay = Number(process.argv[4]);
console.info(".:Execution context:.");
console.info("[HOST]          : " + targetHost);
console.info("[PATH]          : " + targetPath);
console.info("[REFRESH_DELAY] : " + refreshDelay);
console.info("");
console.info(".:Note:.");
console
		.info("The script print data only if new data are available then it's normal if no data appear.");
console
		.info("(Script has received, in this case, an HTTP 304 response from the server)");
console.info("");

//Working global variable
var firstLoad = true;

// Define timer with an anonymous function to handle the data retrieving
var timer = setInterval(function() {
	var modificationDate = new Date();
	var intrvl = modificationDate.getSeconds() - refreshDelay;
	if(intrvl < 0){
		modificationDate.setSeconds(0, 0);
	}else{
		modificationDate.setSeconds(intrvl, 0);
	}
	
	//If it's the first load then we force the content download
	if(firstLoad){
		firstLoad = false;
		modificationDate.setFullYear(1979, 11, 04);
	}
	

	// Define HTTP request options
	var options = {
		host : targetHost,
		port : 80,
		path : targetPath,
		method : 'GET',
		headers : {
			'if-modified-since' : modificationDate.toUTCString()
		}
	};

	// Create HTTP request
	var req = http.request(options, function(res) {
		res.setEncoding('utf8');
		res.on('data', function(chunk) {
			console.info(chunk);
		});
	});

	// Manage HTTP request error
	req.on('error', function(e) {
		console.error('==> Problem with request: ' + e.message);
	});

	// Send request
	req.end();
}, refreshDelay * 1000);
