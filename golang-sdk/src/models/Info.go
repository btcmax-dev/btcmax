package models

//type InfoData struct {
//	BalanceAvailabe	 []map[string]interface{} `json:"balances_available"`
//	BalanceFreeze	[]map[string]interface{} `json:"balance_hold"`
//	ServerTime	int64	`json:"servertimestamp"`
//}
//
//
//type InfoRes struct{
//	Code float64	`json:"code"`
//	Info string 	`json:"info"`
//	Data	InfoData	`json:"data"`
//}


type InfoRes struct {
	Code int `json:"code"`
	Data struct {
		BalanceHold struct {
			BT  float64 `json:"BT"`
			ETH float64 `json:"ETH"`
		} `json:"balance_hold"`
		BalancesAvailable struct {
			BQT  string `json:"BQT"`
			BT   string `json:"BT"`
			BTC  string `json:"BTC"`
			DOGE string `json:"DOGE"`
			ETD  string `json:"ETD"`
			ETH  string `json:"ETH"`
			MT   string `json:"MT"`
			TC   string `json:"TC"`
			TCO  string `json:"TCO"`
			USDT string `json:"USDT"`
			VCT  string `json:"VCT"`
			XMX  string `json:"XMX"`
		} `json:"balances_available"`
		Servertimestamp int `json:"servertimestamp"`
	} `json:"data"`
	Info string `json:"info"`
}

type DigConfigInfo struct {
	Code int `json:"code"`
	Data struct {
		CurrentDigPercent string `json:"current_dig_percent"`
		CurrentDigTotal   int    `json:"current_dig_total"`
		ExpectDigTotal    int    `json:"expect_dig_total"`
		InviteDigReward   string `json:"invite_dig_reward"`
	} `json:"data"`
	Info string `json:"info"`
}
