package config

import "math"

// API KEY
const (
	ACCESS_KEY string = ""
	SECRET_KEY string = ""
)

// API请求地址, 不要带最后的/
const (
	MARKET_URL string = "https://api.btcmax.com/openapi1/pairs"
	BASE_URL string = "https://api.btcmax.com/openapi1"
	TRADE_URL  string = "https://api.btcmax.com/openapi1/auth_api"
)

func Round(f float64, n int) float64 {
	n10 := math.Pow10(n)
	return math.Trunc((f+0.5/n10)*n10) / n10
}
