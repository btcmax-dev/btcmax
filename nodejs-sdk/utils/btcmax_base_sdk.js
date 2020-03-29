var config = require('../config/config');
var CryptoJS = require('crypto-js');
var Promise = require('bluebird');
var http = require('./httpReq');
var querystring = require('querystring');

const URL_BTCMAX_API = 'https://api.btcmax.com';
const DEFAULT_HEADERS = {
    "Content-Type": "application/json;charset=utf-8",
    "User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36"
}
var default_timeout = 6000;

// 格式化请求的参数
function objKeySort(obj) {
    var newkey = Object.keys(obj).sort();
    var newObj = {};
    for (var i = 0; i < newkey.length; i++) {
        newObj[newkey[i]] = obj[newkey[i]];
    }
    var newStr = querystring.stringify(newObj);
    return newStr;
}

// 参数签名
function createSign(obj) {
    obj.secret_key = config.btcmax.secret_key;
    obj.created = new Date().getTime();
    var obj_res = objKeySort(obj);
    var md5_sign = CryptoJS.MD5(obj_res).toString().toLowerCase();
    return md5_sign;
}

function call_api(method, path, body) {
    return new Promise(resolve => {
        var headers = DEFAULT_HEADERS;
        var url = URL_BTCMAX_API + path;
        if (method == 'GET') {
            var url = URL_BTCMAX_API + path;
            if( body ){
                url = url + '?' + querystring.stringify( body );
            }
            http.get(url, {
                timeout: default_timeout,
                headers: headers
            }).then(data => {
                let json = JSON.parse(data);
                if (json.code == '1') {
                    console.log(json.data);
                    resolve(json.data);
                } else {
                    console.log('GET URL='+url+' 调用错误1', json);
                    resolve(null);
                }
            }).catch(ex => {
                console.log(method, path, '异常', ex);
                resolve(null);
            });
        } else if (method == 'POST') {
            http.post(url, body, {
                timeout: default_timeout,
                headers: headers
            }).then(data => {
                let json = JSON.parse(data);
                if (json.code == '1') {
                    resolve(json.data);
                } else {
                    console.log('调用错误2', json);
                    resolve(null);
                }
            }).catch(ex => {
                console.log(method, path, '异常', ex);
                resolve(null);
            });
        }
    });
}

var btcmax_sdk = {
    // 获取所有市场
    get_pairs: function() {
        var path = '/openapi1/pairs';
        return call_api('GET', path);
    },
    // to get configs for mining
    get_dig_configs: function() {
	var path = '/openapi1/get_dig_configs';
	return call_api('GET', path);
    },
    // 获取单个市场
    get_single_pair: function(pair) {
        var path = '/openapi1/pair';
        var body = {};
        body.pair = pair; // 市场，格式：BTC_USDT
        return call_api('GET', path, body);
    },
    // 获取 K 线数据
    get_K_data: function(pair, k_type, k_amount) {
        var path = '/openapi1/k_data';
        var body = {};
        body.pair = pair; // 市场，格式：BTC_USDT
        body.k_type = k_type; // 5m，15m，30m，1h，2h，4h，8h，1d； m:分钟，h:小时，d:天数
        body.k_amount = k_amount; // 可选，获取数据的条数，默认最新80条
        body.rand_key = Math.random() * (1e8 - 1e7) + 1e7; // 10000000 - 100000000之间的随机数
        return call_api('GET', path, body);
    },
    // 获取市场委托深度
    get_depth: function(pair, depth) {
        var path = '/openapi1/orderbook';
        var body = {};
        body.pair = pair; // 市场，格式：BTC_USDT
        body.depth = depth; // 委托档数，默认10条。    值：5，10，15，20，30
        return call_api('GET', path, body);
    },
    // 获取市场成交历史
    get_trades: function(pair, depth) {
        var path = '/openapi1/trades';
        var body = {};
        body.pair = pair; // 市场，格式：BTC_USDT
        body.depth = depth;  // 委托档数，默认10条。    值：5，10，15，20，30
        return call_api('GET', path, body);
    },
    // 获取我的余额
    get_balances: function() {
        var path = '/openapi1/auth_api';
        var body = {};
        body.method = 'getinfo'; // 请求类型
        body.access_key = config.btcmax.access_key; // 公钥，用户申请即可
        var sign = createSign(body);
        body.sign = sign;
        return call_api('POST', path, body);
    },
    // 获取我的委托列表
    get_my_orders: function(pair, amount) {
        var path = '/openapi1/auth_api';
        var body = {};
        body.method = 'myorders';
        body.pair = pair; // 市场，格式：BTC_USDT
        body.amount = amount; // 获取数据量，如：50
        body.access_key = config.btcmax.access_key; // 公钥，用户申请即可
        var sign = createSign(body);
        body.sign = sign;
        return call_api('POST', path, body);
    },
    // 获取我的成交历史
    get_my_trades: function(pair, page, page_size) {
        var path = '/openapi1/auth_api';
        var body = {};
        body.method = 'mytrades';
        body.pair = pair; // 市场，格式：BTC_USDT
        body.page = page; // 页码，默认1
        body.page_size = page_size; // 单页数据量，默认20
        body.access_key = config.btcmax.access_key; // 公钥，用户申请即可
        var sign = createSign(body);
        body.sign = sign;
        return call_api('POST', path, body);
    },
    // 取消委托订单
    cancel_order: function(order_id) {
        var path = '/openapi1/auth_api';
        var body = {};
        body.method = 'cancel_order';
        body.order_id = order_id; // 市场，格式：BTC_USDT
        body.access_key = config.btcmax.access_key; // 公钥，用户申请即可
        var sign = createSign(body);
        body.sign = sign;
        return call_api('POST', path, body);
    },
    // 买入
    buy_coin: function(pair, price, num) {
        var path = '/openapi1/auth_api';
        var body = {};
        body.method = 'buy_coin';
        body.pair = pair; // 市场，格式：BTC_USDT
        body.price = price; // 下单价格
        body.num = num; // 下单数量
        body.access_key = config.btcmax.access_key; // 公钥，用户申请即可
        var sign = createSign(body);
        body.sign = sign;
        return call_api('POST', path, body);
    },
    // 卖出
    sell_coin: function(pair, price, num) {
        var path = '/openapi1/auth_api';
        var body = {};
        body.method = 'sell_coin';
        body.pair = pair; // 市场，格式：BTC_USDT
        body.price = price; // 下单价格
        body.num = num; // 下单数量
        body.access_key = config.btcmax.access_key; // 公钥，用户申请即可
        var sign = createSign(body);
        body.sign = sign;
        return call_api('POST', path, body);
    }
}

module.exports = btcmax_sdk;
