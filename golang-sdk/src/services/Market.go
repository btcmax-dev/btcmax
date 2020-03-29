package services

import (
	"encoding/json"
	"fmt"
	"strconv"
	"config"
	"models"
	"untils"
	"time"
	"math/rand"
)

// 批量操作的API下个版本再封装

//------------------------------------------------------------------------------------------
// 交易API

/**
获取所有市场
 */

func Pairs() models.PairsReturn{
	pairs := models.PairsReturn{}
	strUlr :=config.MARKET_URL
	res :=untils.HttpGetRequest(strUlr,make(map[string]string))
	json.Unmarshal([]byte(res),&pairs)
	//fmt.Println(res)
	//fmt.Println(pairs)
	return pairs
}

/**
获取单个市场
 */
func SinglePair(pair string) models.PairsReturn{
	strUrl := config.BASE_URL + "/pair"
	param := make(map[string]string)
	param["pair"] = pair
	res := untils.HttpGetRequest(strUrl, param)
	//fmt.Println(res)
	var p = models.PairsReturn{}
	json.Unmarshal([]byte(res),&p)
	//fmt.Println(p)
	return p
}


/*
获取市场的深度
 */
func Orders(pair string, depth int) models.OrderBookReturn {
	strUrl := config.BASE_URL + "/orderbook"
	param := make(map[string]string)
	param["pair"] = pair
	param["depth"] = strconv.Itoa(depth)
	res := untils.HttpGetRequest(strUrl, param)
	var p = models.OrderBookReturn{}
	json.Unmarshal([]byte(res),&p)
	return p
}

/*
获取市场成交历史
 */
func Trades(pair string ,depth int) models.TradesReturn{
	strUrl := config.BASE_URL + "/trades"
	param := make(map[string]string)
	param["pair"] = pair
	param["depth"] = strconv.Itoa(depth)
	res := untils.HttpGetRequest(strUrl, param)
	//fmt.Println(res)
	var p = models.TradesReturn{}
	json.Unmarshal([]byte(res),&p)
	return p
}

func GetDigConfigs() models.DigConfigInfo{
	strUrl := config.BASE_URL + "/get_dig_configs"
	param := make(map[string]string)
	res := untils.HttpGetRequest(strUrl, param)
	digConfigInfo := models.DigConfigInfo{}
	json.Unmarshal([]byte(res),&digConfigInfo)
	return digConfigInfo
}

/**
获取我的信息
*/
func GetMyInfo() models.InfoRes  {
	strUrl := config.TRADE_URL
	method := "getinfo"
	param := make(map[string]string)
	param["method"] = method
	sign := untils.BtcmaxSign(param)
	param["sign"] = sign
	//fmt.Println(param)
	res := untils.HttpPostRequest(strUrl, param)
	//fmt.Println(res)
	infoReturn := models.InfoRes{}

	json.Unmarshal([]byte(res),&infoReturn)

	//fmt.Println(infoReturn)
	//fmt.Println(infoReturn.Data.BalancesAvailable.ETH)
	return infoReturn
}

/*
buy coin
*/
func BuyCoin(pair string, price float64, num float64) models.OrderRes{
	strUrl := config.TRADE_URL
	param := make(map[string]string)
	param["pair"] = pair
	param["method"] = "buy_coin"
	param["price"] = strconv.FormatFloat(price,'f',-1, 64)
	param["num"] = strconv.FormatFloat(num,'f',-1, 64)
	sign := untils.BtcmaxSign(param)
	param["sign"] = sign
	res := untils.HttpPostRequest(strUrl, param)
	var p = models.OrderRes{}
	json.Unmarshal([]byte(res),&p)
	return p
}

/*
sell coin
*/
func SellCoin(pair string, price float64, num float64) models.OrderRes{
	strUrl := config.TRADE_URL
	param := make(map[string]string)
	param["pair"] = pair
	param["method"] = "sell_coin"
	param["price"] = strconv.FormatFloat(price,'f',-1, 64)
	param["num"] = strconv.FormatFloat(num,'f',-1, 64)
	sign := untils.BtcmaxSign(param)
	param["sign"] = sign
	res := untils.HttpPostRequest(strUrl, param)
	var p = models.OrderRes{}
	json.Unmarshal([]byte(res),&p)
	return p
}

/*
cancel order
*/

func CancelOrder(orderId int) models.OrderRes {
	strUrl := config.TRADE_URL
	param := make(map[string]string)
	param["method"] = "cancel_order"
	param["order_id"] = strconv.Itoa(orderId)
	sign := untils.BtcmaxSign(param)
	param["sign"] = sign
	res := untils.HttpPostRequest(strUrl, param)
	var p= models.OrderRes{}
	json.Unmarshal([]byte(res),&p)
	return p
}

/*
my order
*/
func MyOrder(pair string, amount int) models.MyOrderRes{
	strUrl := config.TRADE_URL
	param := make(map[string]string)
	param["method"] = "myorders"
	param["amount"] = strconv.Itoa(amount)
	param["pair"] = pair
	param["sign"] = untils.BtcmaxSign(param)
	res := untils.HttpPostRequest(strUrl, param)
	//fmt.Println(res)
	var p = models.MyOrderRes{}
	json.Unmarshal([]byte(res),&p)
	return p
}

/*
my trade
*/
func MyTrade(pair string, page int, pageSize int) models.MyTradeRes{
	strUrl := config.TRADE_URL
	param := make(map[string]string)
	param["method"] = "mytrades"
	param["pair"] = pair
	param["page"] = strconv.Itoa(page)
	param["page_size"] = strconv.Itoa(pageSize)
	param["sign"] = untils.BtcmaxSign(param)
	res := untils.HttpPostRequest(strUrl, param)
	//fmt.Println(res)
	var p = models.MyTradeRes{}
	json.Unmarshal([]byte(res),&p)
	return p
}

/*
获取K线数据
*/
func GetKLine(pair string, k_type string, amount int){
	strUrl := config.BASE_URL + "/k_data"
	param := make(map[string]string)
	param["pair"] = pair
	param["k_type"] = k_type
	param["k_amount"] = strconv.Itoa(amount)
	rand.Seed(time.Now().UnixNano())
	param["rand_key"] = strconv.Itoa(rand.Intn(10000000) * 10)
	res := untils.HttpGetRequest(strUrl, param)
	fmt.Println(res)
}
