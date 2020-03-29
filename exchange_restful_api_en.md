# Exchange API details

### Response data format

All response datas are in json format, consists of three parts, `code`, `info`, `data`.

| Key | Data type | Description |
| --- | --- | --- |
| code | int | error code, code > 0 means success, <0 mean fail |
| info | string | infomation for response |
| data | Array | return data |


### get tickers

#### url

 `https://api.btcmax.com/openapi1/pairs`
 
 
#### method

`GET`

#### params

`null`

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": {
		"BTC_USDT": {
			"price_float_num": "2",
			"amount_float_num": "4",
			"max_trade_num": "1.00000000",
			"min_trade_num": "0.00100000",
			"buy_fee": "0.002000",
			"sell_fee": "0.002000",
			"status": "2",
			"ticker": {
				"min_24h_price": "3892.07", //lowest in 24h
				"min_24h_time": "1553540670", //
				"max_24h_price": "3939.28", // highest in 24h
				"max_24h_time": "1553590634",
				"trade_price": "3930.84", // last trade price
				"price_24h": "3948.4250545775", 
				"change_24h": "-0.0045", //change 24h
				"amount": "2868.72459864", // 24h trade amount, BTC
				"total": "11326903.943824" // 24h trade total, USDT
			}
		}
	}
}
```

### get single market ticker

#### url

 `https://api.btcmax.com/openapi1/pair`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| pair | COIN_ANCHOR |  | yes |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": {
		"ETH_USDT": {
			"price_float_num": "2",
			"amount_float_num": "4",
			"max_trade_num": "20.00000000",
			"min_trade_num": "0.00100000",
			"buy_fee": "0.002000",
			"sell_fee": "0.002000",
			"limit_up": "0.00",
			"limit_down": "0.00",
			"status": "2",
			"ticker": {
				"min_24h_price": "132.00", // 24h low
				"min_24h_time": "1553540679",
				"max_24h_price": "136.15", // 24h high
				"max_24h_time": "1553508472",
				"trade_price": "134.11", // last trade price
				"price_24h": "134.34363040473",
				"change_24h": "-0.0017", // change 24h
				"amount": "23123.52402027", //24h trade amount, ETH
				"total": "3106497.2654139" // 24h trade total, BTC
			}
		}
	}
}
```

### get Kline data

#### url

 `https://api.btcmax.com/openapi1/k_data/`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| pair | COIN_ANCHOR |  | yes |
| k_type | Any of ['5m', '15m', '30m', '1h', '2h', '4h', '8h', '1d'] | time interval. | yes |
| k_amount | int | number of response items, latest 80 default | no |
| rand_key | int | rand num between `10000000` and `100000000` | no |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": [
	//timestamp, open price, highest price, lowest price, close price, amount, volumn 
		[1553569500000, 134.69999999, 134.71, 134.62, 134.65, 26.48262421, 3566.5570679]
		...
	]
}
```

### get orderbook

#### url

 `https://api.btcmax.com/openapi1/orderbook`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| pair | COIN_ANCHOR |  | yes |
| depth | int, any of [5, 10, 15, 20, 30]| number of response items, latest 10 default | no |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": {
		"buy": [{
			"price": "132.86", // price 
			"amount": "0.8363", // amount
			"total": "111.11", // total
			"sum": "111.11" // sum amount
		}
		...
		],
		"sell": [{
			"price": "135.25",
			"amount": "1.0893",
			"total": "147.32",
			"sum": "147.32"
		}
		...
		],
		"hash": "06fa5fb9af37e42d6d7f4bfcc20eefe7"
	}
}
```


### get trade history

#### url

 `https://api.btcmax.com/openapi1/trades`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| pair | COIN_ANCHOR |  | yes |
| depth | int, any of [5, 10, 15, 20, 30]| number of response items, latest 10 default | no |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": {
		"list": [{
			"time": "1553594058",
			"price": "133.92000000",
			"num": "8.99430000", // trade amount
			"total": "1204.51665600", // trade total
			"trade_info": "sell" // buy or sell
		}
		...
		],
		"hash": "f977e502b0757de89f22796ecdd57d1a"
	}
}
```



### get my info

#### url

 `https://api.btcmax.com/openapi1/auth_api`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| method | 'getinfo' | specific value | yes |
| access_key | string | your access key | yes |
| sign | string | signature | yes |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": {
		"balances_available": {
			"BTC": 0.001,
			"ETH": 1.23,
			...
		},
		"balance_hold": {
			"BTC": 0.001,
			"ETH": 1.23,
			...
		},
		"servertimestamp": 1553595062
	}
}
```


### buy and sell
#### url

 `https://api.btcmax.com/openapi1/auth_api`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| method | string | 'buy_coin' for buy, 'sell_coin' for sell | yes |
| pair | string | COIN_ANCHOR, markets | yes |
| price | float | price | yes |
| num | float | number | yes |
| access_key | string | your access key | yes |
| sign | string | signature | yes |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": {
		"order_id": 11111111
	}
}
```

### get my orders
#### url

 `https://api.btcmax.com/openapi1/auth_api`

#### method

`GET`

#### params

| Key | Value | Description  |  Required |
| --- | --- | --- | --- |
| method | 'myorders' | specific value | yes |
| pair | string | COIN_ANCHOR, markets | yes |
| amount | int | number of response items | yes |
| access_key | string | your access key | yes |
| sign | string | signature | yes |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": [{
		"order_id": "22222",
		"user_id": "111111",
		"type": "BTC",  // coin
		"danwei": "USDT", // anchor
		"ctime": "1553594748",
		"price": "3000.12",
		"num": "0.18",
		"total": "540.0216",
		"rest_num": "0.18", // available numbers of this order
		"rest_total": "540.0216", // available total value of this order
		"fee": "0.00000000",
		"order_type": "sell"
	}
	...
	]
}
```

### cancel order
#### url

 `https://api.btcmax.com/openapi1/auth_api`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| method | 'cancel_order' | specific value | yes |
| order_id | int | id of order to be canceled | yes |
| access_key | string | your access key | yes |
| sign | string | signature | yes |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": {
	}
}
```

### get my trade history 

#### url

 `https://api.btcmax.com/openapi1/auth_api`

#### method

`GET`

#### params

| Key | Value | Description  | Required |
| --- | --- | --- | --- |
| method | 'mytrades' | specific value | yes |
| pair | string | COIN_ANCHOR, markets | yes |
| page | int | page number for pager, default 1 | no |
| page_size | int | number of item for each page, default 20 | no |
| access_key | string | your access key | yes |
| sign | string | signature | yes |

#### response

```
{
	"code": 1,
	"info": "ok",
	"data": [{
		"trade_id": "8725708",
		"pair": "BTC_USDT",
		"ctime": "1553594740",
		"price": "3000.12",
		"num": "0.1",
		"total": "300.012",
		"fee": "0.300012",
		"order_id": "8725451",
		"trade_type": "sell"
	}
	...
	]
}
```
