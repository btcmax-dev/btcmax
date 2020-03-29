/**
 * url
 */
public class Config {
    //得到单一市场对
    static String GetMarketPairUrl = "https://api.btcmax.com/openapi1/pair?pair=";
    //获取所有市场
    static String GetAllMarketPair = "https://api.btcmax.com/openapi1/pairs";
    //公钥
    static String access_key = "";
    //私钥
    static String secret_key = "";
    //获取委托列表
    static String GetOrderBook = "https://api.btcmax.com/openapi1/orderbook?";
    //获取我的委托列表
    static String GetMyOrderBook = "https://api.btcmax.com/openapi1/auth_api";
    //取消我的委托列表
    static String CancelMyOrderBook = "https://api.btcmax.com/openapi1/auth_api";
    //获取我的余额
    static String GetMyMoney = "https://api.btcmax.com/openapi1/auth_api";
    //获取K线数据
    static String GetKLineData = "https://api.btcmax.com/openapi1/k_data/?";
    //获取市场成交历史
    static String GetTradeHistory = "https://api.btcmax.com/openapi1/trades?";
    //我的成交历史
    static String MyTradeHistory = "https://api.btcmax.com/openapi1/auth_api";
    //我的挖矿比例
    static String InviteDigReward = "https://api.btcmax.com/openapi1/get_dig_configs";
}
