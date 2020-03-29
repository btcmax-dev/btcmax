package untils

import (
	//"encoding/json"
	"io/ioutil"
	"net/http"
	"sort"
	"strings"
	"time"
	"config"
	"strconv"
	"crypto/md5"
	"encoding/hex"
)

// Http Get请求基础函数, 通过封装Go语言Http请求, 支持火币网REST API的HTTP Get请求
// strUrl: 请求的URL
// strParams: string类型的请求参数, user=lxz&pwd=lxz
// return: 请求结果
func HttpGetRequest(strUrl string, mapParams map[string]string) string {
	httpClient := &http.Client{}

	var strRequestUrl string
	if nil == mapParams {
		strRequestUrl = strUrl
	} else {
		strParams := Map2UrlQuery(mapParams)
		strRequestUrl = strUrl + "?" + strParams
	}

	//fmt.Println(strRequestUrl)
	// 构建Request, 并且按官方要求添加Http Header
	request, err := http.NewRequest("GET", strRequestUrl, nil)
	if nil != err {
		return err.Error()
	}
	request.Header.Add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")

	// 发出请求
	response, err := httpClient.Do(request)
	defer response.Body.Close()
	if nil != err {
		return err.Error()
	}

	// 解析响应内容
	body, err := ioutil.ReadAll(response.Body)
	if nil != err {
		return err.Error()
	}

	return string(body)
}

// Http POST请求基础函数, 通过封装Go语言Http请求, 支持火币网REST API的HTTP POST请求
// strUrl: 请求的URL
// mapParams: map类型的请求参数
// return: 请求结果
func HttpPostRequest(strUrl string, mapParams map[string]string) string {
	httpClient := &http.Client{}

	////jsonParams := ""
	//if nil != mapParams {
	//	bytesParams, _ := json.Marshal(mapParams)
	//	jsonParams = string(bytesParams)
	//}

	//request, err := http.NewRequest("POST", strUrl, strings.NewReader(jsonParams))

	var queryStr string

	for key, val := range mapParams {
		queryStr += key + "=" + val + "&"

	}

	if 0 < len(queryStr) {
		queryStr = string([]rune(queryStr)[:len(queryStr)-1])
	}else {
		queryStr = ""
	}

	request, err := http.NewRequest("POST", strUrl, strings.NewReader(queryStr))

	if nil != err {
		return err.Error()
	}
	request.Header.Add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
	//request.Header.Add("Content-Type", "application/json")
	request.Header.Add("Content-Type", "application/x-www-form-urlencoded")
	request.Header.Add("Accept-Language", "zh-cn")



	response, err := httpClient.Do(request)
	defer response.Body.Close()
	if nil != err {
		return err.Error()
	}

	body, err := ioutil.ReadAll(response.Body)
	if nil != err {
		return err.Error()
	}

	return string(body)
}

func BtcmaxSign(mapParams map[string]string) string {
	//append(mapParams, 2)
	mapParams["access_key"] = config.ACCESS_KEY
	mapParams["created"] = strconv.FormatInt(time.Now().Unix(), 10)
	mapParams["secret_key"] = config.SECRET_KEY

	var keys []string
	for key := range mapParams {
		keys = append(keys, key)
	}
	sort.Strings(keys)

	var queryStr string
	for _,k := range keys {
		queryStr += k + "=" +mapParams[k] + "&"
	}

	if 0 < len(queryStr) {
		queryStr = string([]rune(queryStr)[:len(queryStr)-1])
	}else {
		queryStr = ""
	}

	md5F := md5.New()
	md5F.Write([]byte(queryStr))
	cipherStr := md5F.Sum(nil)
	delete(mapParams, "secret_key")
	return hex.EncodeToString(cipherStr)
}



// 将map格式的请求参数转换为字符串格式的
// mapParams: map格式的参数键值对
// return: 查询字符串
func Map2UrlQuery(mapParams map[string]string) string {
	var strParams string
	for key, value := range mapParams {
		strParams += (key + "=" + value + "&")
	}

	if 0 < len(strParams) {
		strParams = string([]rune(strParams)[:len(strParams)-1])
	}

	return strParams
}

