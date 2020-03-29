const http = require('https');
const request = require('request');

var default_post_headers = {'content-type': 'application/json;charset=utf-8' }
var default_agentOptions = {keepAlive: true }
var default_timeout = 3000;

exports.get = function(url, options) {
    return new Promise((resolve, reject) => {
        options = options || {};
        var httpOptions = {
            url: url,
            method: 'GET',
            timeout: options.timeout || default_timeout,
            headers: options.headers || default_post_headers,
            proxy: options.proxy || '',
            agentOptions: default_agentOptions
        }
        request.get(httpOptions, function(err, res, body) {
            if (err) {
                reject(err);
            } else {
                if (res.statusCode == 200) {
                    resolve(body);
                } else {
                    reject(res.statusCode);
                }
            }
        }).on('error', function(e){
            console.log(e);
        });
    });
}

exports.post = function(url, postdata, options) {
    return new Promise((resolve, reject) => {
        options = options || {};
        var httpOptions = {
            url: url,
            form: postdata,
            method: 'POST',
            timeout: options.timeout || default_timeout,
            headers: options.headers || default_post_headers,
            proxy: options.proxy || '',
            agentOptions: default_agentOptions
        };
        request(httpOptions, function(err, res, body) {
            if (err) {
                reject(err);
            } else {
                if (res.statusCode == 200) {
                    resolve(body);
                } else {
                    reject(res.statusCode);
                }
            }
        }).on('error', console.error);
    });
};
