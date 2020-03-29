import java.io.IOException;

/**
 * Created by administrator on 2018/7/9.
 */
public class TestHttpDemo {
    public static void main(String[] args) throws Exception {


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取所有市场
                String GetAllMarketPair = null;
                try {
                    GetAllMarketPair = Btcmax.GetAllMarketPair();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("获取所有市场1=====" + GetAllMarketPair);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                //得到单一市场对
                String tco_eth = null;
                try {
                    tco_eth = Btcmax.GetMarketPair("TCO_ETH");
                } catch (IOException e) {
                    e.printStackTrace();

                }
                System.out.println("得到单一市场对2=====" + tco_eth);
            }
        }).start();


        //获取K线数据
        String tco = Btcmax.GetKLineData("TCO_ETH", "5m", "32370339");
        System.out.println("获取K线数据=====" + tco);

        //获取委托列表
        String tco_eth1 = Btcmax.GetOrderBook("TCO_ETH", "100");
        System.out.println("获取委托列表=====" + tco_eth1);

        //获取市场成交历史
        String tco_eth2 = Btcmax.GetTradeHistory("TCO_ETH", "100");
        System.out.println("获取市场成交历史=====" + tco_eth2);

        //获取我的余额
        String s = Btcmax.GetMyInfo();
        System.out.println(s);

        //限价委托卖出
        String tco_eth3 = Btcmax.SellCoin("TCO_ETH", "0.000068", "100");
        System.out.println(tco_eth3);

        //限价委托买入
        String tco_eth4 = Btcmax.buyCoin("TCO_ETH", "0.0000001", "100");
        System.out.println(tco_eth4);
//
        //获取我的委托列表
        String tco_eth5 = Btcmax.GetMyOrder("TCO_ETH", "50");
        System.out.println("获取我的委托列表=====" + tco_eth5);

        //取消我的委托
        String s1 = Btcmax.CancelMyOrder("4809166");
        System.out.println(s1);

        //我的成交历史
        String tco_eth6 = Btcmax.MyTradeHistory("TCO_ETH", "1", "20");
        System.out.println("我的成交历史=====" + tco_eth6);


    }
}
