import requests
import hashlib
import datetime
import operator

PRIVATE_KEY = ''
ACCESS_KEY = ''
BASE_URL = 'https://api.btcmax.com/'


# 获取所有市场
def get_all_pairs_tickers():
    response = requests.get(BASE_URL + 'openapi1/pairs')
    return response.json()


# 获取单个市场
def get_single_pair_tickers(pair):
    param = {'pair': pair}

    response = requests.get(BASE_URL + 'openapi1/pair', param)
    return response.json()


# 获取K线
def get_kline_data(pair, k_type, k_amount, rand_key):
    param = {'pair': pair,
             'k_type': k_type,
             'k_amount': k_amount,
             'rand_key': rand_key}

    response = requests.get(BASE_URL + 'openapi1/k_data/', param)
    return response.json()


# 获取邀请挖矿奖励比例等信息
def get_dig_configs():
    response = requests.get(BASE_URL + 'get_dig_configs')
    return response.json()


# 获取某市场的委托列表
def get_orderbook(pair, depth):
    param = {'pair': pair,
             'depth': depth}

    response = requests.get(BASE_URL + 'openapi1/orderbook', param)
    return response.json()


# 获取某市场成交历史
def get_trade_history(pair, depth):
    param = {'pair': pair,
             'depth': depth}

    response = requests.get(BASE_URL + 'openapi1/trades', param)
    return response.json()


# 获取我的余额
def get_my_balance():
    param = {'access_key': ACCESS_KEY,
             'method': 'getinfo'}

    param['sign'] = build_sign(param, PRIVATE_KEY)
    response = requests.post(BASE_URL + 'openapi1/auth_api', param)
    return response.json()


# 限价委托买入
def buy_order(pair, price, num):
    param = {'access_key': ACCESS_KEY,
             'method': 'buy_coin',
             'num': num,
             'pair': pair,
             'price': price}

    param['sign'] = build_sign(param, PRIVATE_KEY)
    response = requests.post(BASE_URL + 'openapi1/auth_api', param)
    return response.json()


# 限价委托卖出
def sell_order(pair, price, num):
    param = {'access_key': ACCESS_KEY,
             'method': 'sell_coin',
             'num': num,
             'pair': pair,
             'price': price}

    param['sign'] = build_sign(param, PRIVATE_KEY)
    response = requests.post(BASE_URL + 'openapi1/auth_api', param)
    return response.json()


# 获取我的委托列表
def get_my_order(pair, amount):
    param = {'access_key': ACCESS_KEY,
             'amount': amount,
             'method': 'myorders',
             'pair': pair}

    param['sign'] = build_sign(param, PRIVATE_KEY)
    response = requests.post(BASE_URL + 'openapi1/auth_api', param)
    return response.json()


# 取消委托
def cancel_my_order(order_id):
    param = {'access_key': ACCESS_KEY,
             'method': 'cancel_order',
             'order_id': order_id}

    param['sign'] = build_sign(param, PRIVATE_KEY)
    response = requests.post(BASE_URL + 'openapi1/auth_api', param)
    return response.json()


# 我的成交历史
def get_my_trade_history(pair, page, page_size):
    param = {'access_key': ACCESS_KEY,
             'method': 'mytrades',
             'pair': pair,
             'page': page,
             'page_size': page_size}

    param['sign'] = build_sign(param, PRIVATE_KEY)
    response = requests.post(BASE_URL + 'openapi1/auth_api', param)
    return response.json()


# 参数排序
def build_sign(param, secretKey):
    sign = ''
    for key in sorted(param.keys()):
        sign += key + '=' + str(param[key]) + '&'
    data = sign + 'secret_key=' + secretKey
    return hashlib.md5(data.encode("utf8")).hexdigest()

def get_dig_configs():
    response = requests.get(BASE_URL + 'openapi1/get_dig_configs')
    return response.json()


if __name__ == '__main__':
    print(get_all_pairs_tickers('openapi1/pairs'))
