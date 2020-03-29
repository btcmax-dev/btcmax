<?php
define("PUB_API_URL","https://api.btcmax.com/openapi1/");
define("AUTH_API_URL",PUB_API_URL."auth_api/");

class BtcmaxAPI{
        private $access_key = "Your public key";
        private $secret_key = "Your private key";
        
        public function __construct($access_key='',$secret_key=''){
            $this->access_key = $access_key;
            $this->secret_key = $secret_key;
        }
        
        // get all markets pairs
        function pairs(){
            $url = PUB_API_URL."pairs"; 
            $tResult = $this->httpRequest($url,'');
            return json_decode($tResult,1);
        } 
        
        // get single market pair
        function pair($pair){
            if(!$pair){
                return false;
            }
            $pair = strtoupper($pair);
            $url = PUB_API_URL."pair/?pair=".$pair; 
            echo $url;
            $tResult = $this->httpRequest($url,'');
            return json_decode($tResult,1);
        } 
        
        // get single market trade history
        function trades($pair){
            if(!$pair){
                return false;
            }
            $pair = strtoupper($pair);
            $url = PUB_API_URL."trades/?pair=".$pair; 
            $tResult = $this->httpRequest($url,'');
            return json_decode($tResult,1);
        } 
        
        // get single market depth
        function orderbook($pair){
            if(!$pair){
                return false;
            }
            $pair = strtoupper($pair);
            $url = PUB_API_URL."orderbook/?pair=".$pair; 
            $tResult = $this->httpRequest($url,'');
            return json_decode($tResult,1);
        }
        /**
        * buy coin 
        * @params   type: DOGE BC TIPS
        *           danwei: CNY 
        *           num: sell coin amount
        *           price: sell price
        **/
        function buy_coin($pair,$num,$price){
                if(!$pair){
                    return false;
                }
                if($num <= 0 || $price <= 0){
                    return false;
                }
                $tParams = array();
                $tParams['method'] = 'buy_coin';
                $tParams['access_key'] = $this->access_key;
                $tParams['created'] = time();

                $tParams['pair'] = trim(strtoupper($pair));
                $tParams['num'] = (float)abs($num);
                $tParams['price'] = (float)abs($price);
                $tParams['total'] = (float)abs($num * $price); 

                $tParams['sign'] = $this->createSign($tParams);
                $tResult = $this->httpRequest(AUTH_API_URL, $tParams);
                return json_decode($tResult,1);
        }

        /**
        * sell coin 
        * @params   type: DOGE BC TIPS
        *           danwei: CNY 
        *           num: sell coin amount
        *           price: sell price
        **/
        function sell_coin($pair,$num,$price){
                if(!$pair){
                    return false;
                }
                if($num <= 0 || $price <= 0){
                    return false;
                }
                $tParams = array();
                $tParams['method'] = 'sell_coin';
                $tParams['access_key'] = $this->access_key;
                $tParams['created'] = time();

                $tParams['pair'] = trim(strtoupper($pair));
                $tParams['num'] = (float)abs($num);
                $tParams['price'] = (float)abs($price);
                $tParams['total'] = (float)abs($num * $price); 

                $tParams['sign'] = $this->createSign($tParams);
                $tResult = $this->httpRequest(AUTH_API_URL, $tParams);
                return json_decode($tResult,1);
        }
        
        //cancel orders 
        function cancel_order($order_id){
                if(!is_numeric($order_id) || $order_id <=0){
                    return false;
                }
                $tParams = array();
                $tParams['method'] = 'cancel_order';
                $tParams['access_key'] = $this->access_key;
                $tParams['created'] = time();
                
                $tParams['order_id'] = (int)$order_id;
            
                $tParams['sign'] = $this->createSign($tParams);
                $tResult = $this->httpRequest(AUTH_API_URL, $tParams);
                return json_decode($tResult,1);
        }
        
        //get my balance and other basic info 
        function getinfo(){
                $tParams = array();
                $tParams['method'] = 'getinfo';
                $tParams['access_key'] = $this->access_key;
                $tParams['created'] = time();
            
                $tParams['sign'] = $this->createSign($tParams);
                $tResult = $this->httpRequest(AUTH_API_URL, $tParams);
                return json_decode($tResult,1);
        }


        //get my active orders 
        function myorders($pair='',$amount=50){
                $tParams = array();
                $tParams['method'] = 'myorders';
                $tParams['access_key'] = $this->access_key;
                $tParams['created'] = time();

                $tParams['pair'] = $pair;
                $tParams['amount'] = $amount;
            
                $tParams['sign'] = $this->createSign($tParams);
                $tResult = $this->httpRequest(AUTH_API_URL, $tParams);
                return json_decode($tResult,1);
        }


	function get_dig_configs(){
		$tResult = $this->httpRequest(PUB_API_URL."get_dig_configs","");
		return json_decode($tResult,1);
	}

        /**
        * get my trade history, if the pair is empty, will return all trade history
        * @params 
        *        pair : ETH_BTC ETH_USDT
        *        page: page
        *        page_size: how many records in a page.
        **/
        function mytrades($pair='',$page=1,$page_size=20){
                $tParams = array();
                $tParams['method'] = 'mytrades';
                $tParams['access_key'] = $this->access_key;
                $tParams['created'] = time();
                
                $tParams['pair'] = $pair;
                $tParams['page'] = (int)abs($page);
                $tParams['page_size'] = (int)abs($page_size);
                
                $tParams['sign'] = $this->createSign($tParams);
                $tResult = $this->httpRequest(AUTH_API_URL, $tParams);
                return json_decode($tResult,1);
        }
        

        function httpRequest($pUrl, $pData){
                $tCh = curl_init();
                if($pData){
                        is_array($pData) && $pData = http_build_query($pData);
                        curl_setopt($tCh, CURLOPT_POST, true);
                        curl_setopt($tCh, CURLOPT_POSTFIELDS, $pData);
                }
                curl_setopt($tCh, CURLOPT_HTTPHEADER, array("Content-type: application/x-www-form-urlencoded"));
                curl_setopt($tCh, CURLOPT_URL, $pUrl);
                curl_setopt($tCh, CURLOPT_RETURNTRANSFER, true);
                curl_setopt($tCh, CURLOPT_SSL_VERIFYPEER, false);
                curl_setopt($tCh, CURLOPT_TIMEOUT, 10);
                $tResult = curl_exec($tCh);
                curl_close($tCh);
                return $tResult;
        }
        
        //sign the data
        function createSign($pParams = array()){
                $pParams['secret_key'] = $this->secret_key;
                ksort($pParams);
                $tPreSign = http_build_query($pParams);
                $tSign = md5($tPreSign);
                return strtolower($tSign);
        }
}

/**
* Test Demo
*
*/
   // $access_key = '';
   // $secret_key = '';
   // $sdk = new BtcmaxAPI($access_key,$secret_key);

    //get account info
    //$info = $sdk->getinfo();
    //var_dump($info);
	//$pair = 'ETH_BTC';
        
    //get all markets
    //$pairs = $sdk->pairs();
    //var_dump($pairs);
    
    //get single market
    //$pair_info = $sdk->pair($pair);
    //var_dump($pair_info);
    
    //get market depth 
    //$depth = $sdk->orderbook($pair);
    //var_dump($depth);
    
    //trade history
    //$trade = $sdk->trades($pair);
    //var_dump($trade);
    //exit();
    
    //buy coin
    //$res = $sdk->buy_coin($pair,1,0.07);
    //var_dump($res);
    //exit();

    //sell coin 
    //$res = $sdk->sell_coin($pair,1,0.078);
    //var_dump($res);
    
    //cancel order 
    //sleep(1);
    //$res1 = $sdk -> cancel_order($res['data']['order_id']);
    //var_dump($res1);
   
    //get my open orders, at most 100 records 
    //$res = $sdk->myorders();
    //var_dump($res);
    //exit();
    
    //get my trade history
    //$res = $sdk->mytrades(); //get all trade history
    //$res = $sdk->mytrades($pair,1,50); //single market
    //var_dump($res);
    
    //$res = $sdk->mydeposits();
    //var_dump($res);
    //exit();

    //$res = $sdk->deposit_address('DOGE');
    //var_dump($res);
    ?>
    
