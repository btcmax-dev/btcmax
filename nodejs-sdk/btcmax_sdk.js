const btcmaxsdk = require('./utils/btcmax_base_sdk');

function run() {
    // 请在运行之前填写config/default.json中的信息
    // access_key 及 secretkey 需要去 btcmax.com上申请

    // 获取市场
    btcmaxsdk.get_pairs();

    // 获取单个市场
    // btcmaxsdk.get_single_pair('BT_ETH');

    // 获取 K 线数据
    // btcmaxsdk.get_K_data('BT_ETH');

    // 获取市场委托列表
    // btcmaxsdk.get_depth('BT_ETH', 20);

    // 获取市场成交历史
    // btcmaxsdk.get_trades('BT_ETH', 20);

    // 获取我的余额
    // btcmaxsdk.get_balances().then(console.log);

    // 获取我的委托列表
    // btcmaxsdk.get_my_orders('BT_ETH', 20).then(console.log);

    // 获取我的成交历史
    // btcmaxsdk.get_my_trades('BT_ETH', 1, 20).then(console.log);

    // 买入
    // btcmaxsdk.buy_coin('BT_ETH', 0.00010893, 10).then(console.log);
    // 卖出
    // btcmaxsdk.sell_coin('BT_ETH', 0.01, 10).then(console.log);

    // 取消订单
    // btcmaxsdk.cancel_order(4909790).then(console.log);

}
run();