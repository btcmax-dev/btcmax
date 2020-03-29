package models

type PairsReturn struct {
	Code int `json:"code"`
	Data struct {
		AISIUSDT MarketInfo `json:"AISI_USDT"`
		BTCUSDT MarketInfo `json:"BTC_USDT"`
		BTETH MarketInfo	`json:"BT_ETH"`
		ETHUSDT MarketInfo	`json:"ETH_USDT"`
		BTUSDT MarketInfo	`json:"BT_USDT"`
		TCOETH MarketInfo	`json:"TCO_ETH"`
		XMXETH MarketInfo	`json:"XMX_ETH"`
		VCTETH MarketInfo	`json:"VCT_ETH"`
		/*
		SCH_USDT
		TTB_ETH
		LTC_USDT
		BCH_USDT
		MT_ETH
		BQT_ETH
		XMX_USDT
		//---*/
	}
}


type MarketInfo struct {
	BuyFee      string `json:"buy_fee"`
	MinTradeNum string `json:"min_trade_num"`
	SellFee     string `json:"sell_fee"`
	Status      string `json:"status"`
	Ticker      struct {
		Amount      string `json:"amount"`
		Change24h   string `json:"change_24h"`
		Max24hPrice string `json:"max_24h_price"`
		Max24hTime  string `json:"max_24h_time"`
		Min24hPrice string `json:"min_24h_price"`
		Min24hTime  string `json:"min_24h_time"`
		Price24h    string `json:"price_24h"`
		Total       string `json:"total"`
		TradePrice  string `json:"trade_price"`
	} `json:"ticker"`
}
