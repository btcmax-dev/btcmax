package models

type Order struct {
	Amount string `json:"amount"`
	Price  string `json:"price"`
	Sum    string `json:"sum"`
	Total  string `json:"total"`
}

type OrderBookReturn struct {
	Code int `json:"code"`
	Data struct {
		Buy  []Order `json:"buy"`
		Hash string  `json:"hash"`
		Sell []Order `json:"sell"`
	} `json:"data"`
	Info string `json:"info"`
}

type Trade struct {
	Num       string `json:"num"`
	Price     string `json:"price"`
	Time      string `json:"time"`
	Total     string `json:"total"`
	TradeInfo string `json:"trade_info"`
}

type TradesReturn struct {
	Code int `json:"code"`
	Data struct {
		Hash string  `json:"hash"`
		List []Trade `json:"list"`
	} `json:"data"`
	Info string `json:"info"`
}

type OrderRes struct {
	Code int `json:"code"`
	Data struct {
		OrderID int `json:"order_id"`
	} `json:"data"`
	Info string `json:"info"`
}

type MyOrderInfo struct {
	Ctime     string `json:"ctime"`
	Danwei    string `json:"danwei"`
	Fee       string `json:"fee"`
	Num       string `json:"num"`
	OrderID   string `json:"order_id"`
	OrderType string `json:"order_type"`
	Price     string `json:"price"`
	RestNum   string `json:"rest_num"`
	RestTotal string `json:"rest_total"`
	Total     string `json:"total"`
	Type      string `json:"type"`
	UserID    string `json:"user_id"`
}

type MyOrderRes struct {
	Code int           `json:"code"`
	Data []MyOrderInfo `json:"data"`
	Info string        `json:"info"`
}

type MyTradeInfo struct {
	Ctime     string `json:"ctime"`
	Fee       string `json:"fee"`
	Num       string `json:"num"`
	OrderID   string `json:"order_id"`
	Pair      string `json:"pair"`
	Price     string `json:"price"`
	Total     string `json:"total"`
	TradeID   string `json:"trade_id"`
	TradeType string `json:"trade_type"`
}

type MyTradeRes struct {
	Code int           `json:"code"`
	Data []MyTradeInfo `json:"data"`
	Info string        `json:"info"`
}
