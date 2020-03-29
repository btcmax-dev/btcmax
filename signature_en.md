## Signature Algorithm

1.Prepare all parameters as well as your SECRET_KEY, sort them by the key dictionary orders. Such as:
 
```
access_key=abcdefgh
method=buy_coin
num=0.001
pair=BTC_USDT
price=2000
secret_key=abcdefgh
ts=1544400608
```

2.Splice the parameters as a string in a http requests format such as `access_key=abcdefgh&method=buy_coin&num=0.001&pair=BTC_USDT&price=2000&secret_key=abcdefgh&ts=1544400608` 

3.Calculate the md5 checksum of the string, which is the signature needed.

**PHP daemon code**
```
//php daemon of signature algorithm
function createSign($pParams = array()){
	$pParams['secret_key'] = $this->secret_key; 
	ksort($pParams); 
	$tPreSign = http_build_query($pParams);
	$tSign = md5($tPreSign);
	return strtolower($tSign);
}
```
