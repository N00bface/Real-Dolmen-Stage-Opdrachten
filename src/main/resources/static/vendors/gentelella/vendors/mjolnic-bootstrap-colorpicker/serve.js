/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

/*
 Script for serving index.html and other static content with Node.
 Run it using `node serve` from your terminal and navigate to http://localhost:5000
 in order to test your changes in the browser.
 */

var http = require('http'), fs = require('fs'), mimeTypes = {
  'html': 'text/html',
  'css': 'text/css',
  'js': 'text/javascript',
  'json': 'application/json',
  'png': 'image/png',
  'jpg': 'image/jpg'
};

http.createServer(function (req, res) {
  var file = (req.url === '/') ? 'index.html' : "." + req.url;
  var ext = require('path').extname(file),
    type = (mimeTypes[ext] ? mimeTypes[ext] : '');

  fs.exists(file, function (exists) {
    if (exists) {
      res.writeHead(200, {'Content-Type': type});
      fs.createReadStream(file).pipe(res);
    } else {
      console.warn(file, ' does not exit');
    }
  });
}).listen(5000);
