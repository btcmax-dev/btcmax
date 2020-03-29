import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HttpConnect {


    /**
     * 得到所有市场对
     *
     * @ return
     * @ throws IOException
     */
    public static String GetAllMarketPair() throws IOException {

        String GetAllMarketPair = asyncGet(Config.GetAllMarketPair, null, "get");
        return GetAllMarketPair;
    }


    /**
     * 得到单一市场对
     *
     * @param pair (交易市场)
     */
    public static String GetMarketPair(String pair) throws IOException {

        String url = Config.GetMarketPairUrl + pair;
        String s = asyncGet(url, null, "get");
        return s;
    }


    /**
     * 获取K线数据
     *
     * @param pair     （交易市场）
     * @param k_type   （K线时间类型）
     * @param rand_key （随机数）
     * @ return
     * @ throws IOException
     */

    public static String GetKLineData(String pair, String k_type, String rand_key) throws IOException {

        String url = Config.GetKLineData + "pair=" + pair + "&" + "k_type=" + k_type + "&" + "rand_key=" + rand_key;
        String s = asyncGet(url, null, "get");
        return s;
    }


    /**
     * 获取委托列表
     *
     * @param pair  （交易市场）
     * @param depth （委托档数）
     * @ return
     * @ throws IOException
     */

    public static String GetOrderBook(String pair, String depth) throws IOException {
        String url = Config.GetOrderBook + "pair=" + pair + "&" + "depth=" + depth;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String get = asyncGet(url, null, "get");
        return get;
    }


    /**
     * 获取市场成交历史
     *
     * @param pair （交易市场）
     * @return （委托档数）
     * @ throws IOException
     */

    public static String GetTradeHistory(String pair, String depth) throws IOException {
        String url = Config.GetTradeHistory + "pair=" + pair + "&" + "depth=" + depth;
        String get = asyncGet(url, null, "get");
        return get;
    }

    /**
     * 获取我的余额信息
     *
     * @ return
     * @ throws IOException
     */

    public static String GetMyInfo() throws IOException {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("method", "getinfo");
        hashMap.put("access_key", Config.access_key);
        hashMap.put("secret_key", Config.secret_key);
        String s = asyncGet(Config.GetMyMoney, hashMap, "post");
        return s;
    }


    /**
     * 限价委托卖出
     *
     * @param pair  （交易市场）
     * @param price （下单价格）
     * @param num   （下单数量）
     * @ return
     * @ throws IOException
     */
    public static String SellCoin(String pair, String price, String num) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "sell_coin");
        map.put("price", price);
        map.put("num", num);
        map.put("pair", pair);
        map.put("access_key", Config.access_key);
        map.put("secret_key", Config.secret_key);

        String post = asyncGet(Config.MyTradeHistory, map, "post");
        return post;

    }

    /**
     * 限价委托买入
     *
     * @param pair  （交易市场）
     * @param price （下单价格）
     * @param num   （下单数量）
     * @ return
     */
    public static String buyCoin(String pair, String price, String num) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "buy_coin");
        map.put("price", price.trim());
        map.put("num", num.trim());
        map.put("pair", pair.trim());
        map.put("access_key", Config.access_key);
        map.put("secret_key", Config.secret_key);

        String post = asyncGet(Config.MyTradeHistory, map, "post");
        return post;

    }


    /**
     * 获取我的委托列表
     *
     * @param pair   （交易市场）
     * @param amount （获取数据量）
     * @ return
     * @ throws IOException
     */

    public static String GetMyOrder(String pair, String amount) throws IOException {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("pair", pair);
        hashMap.put("method", "myorders");
        hashMap.put("amount", amount);
        hashMap.put("access_key", Config.access_key);
        hashMap.put("secret_key", Config.secret_key);
        String post = asyncGet(Config.GetMyOrderBook, hashMap, "post");
        return post;
    }

    /**
     * 取消我的委托
     *
     * @param order_id (订单ID)
     * @ return
     * @ throws IOException
     */

    public static String CancelMyOrder(String order_id) throws IOException {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("method", "cancel_order");
        hashMap.put("order_id", order_id);
        hashMap.put("access_key", Config.access_key);
        hashMap.put("secret_key", Config.secret_key);

        String s = asyncGet(Config.CancelMyOrderBook, hashMap, "post");
        return s;
    }

    /**
     * 邀请挖矿奖励比例信息
     * @return
     * @throws IOException
     *
     */

    public static String getInviteDigReward() throws IOException {
	    String s = asyncGet(Config.InviteDigReward, null, "get");
	    return s;
    }

    /**
     * 我的成交历史
     *
     * @param pair      （交易市场）
     * @param page      （页码）
     * @param page_size （单页多少）
     * @ return
     * @ throws IOException
     */

    public static String MyTradeHistory(String pair, String page, String page_size) throws IOException {

	    HashMap<String, String> hashMap = new HashMap<String, String>();
	    hashMap.put("method", "mytrades");
	    hashMap.put("pair", pair);
	    hashMap.put("page", page);
	    hashMap.put("page_size", page_size);
	    hashMap.put("access_key", Config.access_key);
	    hashMap.put("secret_key", Config.secret_key);

	    String s = asyncGet(Config.MyTradeHistory, hashMap, "post");
	    return s;

    }

    /**
     * 异步请求
     *
     * @param url     （请求地址）
     * @param hashMap （请求参数）
     * @param method  （请求方法： post get ）
     * @ return
     * @ throws IOException
     */
    public static String asyncGet(String url, Map<String, String> hashMap, String method) throws IOException {


	    if (method.isEmpty()) {
		    return "";
	    }

	    String s1 = "";
	    try {
		    s1 = GetMd5s(hashMap);
	    } catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
	    }

	    if (hashMap != null) {

		    //获取map集合的所有"映射"的Set集合,这里规范每个映射的类型为Map.Entry<K, V>（于Set集合中无序存放）
		    Set<Map.Entry<String, String>> entrySet = hashMap.entrySet();
		    //新建List集合获取Set集合的所有元素（"映射"对象）（顺序与Set集合一样）
		    List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(entrySet);
		    //获取List集合的迭代器,Map.Entry<K, V>为迭代元素的类型
		    Iterator<Map.Entry<String, String>> iter = list.iterator();
		    FormBody.Builder builder = new FormBody.Builder();
		    for (int i = 0; i < list.size(); i++) {
			    builder.add(list.get(i).getKey(), list.get(i).getValue());
		    }
		    builder.add("sign", s1);
		    FormBody formBody = builder.build();
		    // 创建OkHttpClient对象
		    OkHttpClient okHttpClient = new OkHttpClient();
		    // 创建一个请求
		    if (method.equals("post")) {
			    Request request = new Request.Builder().url(url).post(formBody).build();
			    Response response = okHttpClient.newCall(request).execute();
			    String result = response.body().string();
			    return result;
		    } else {
			    Request request = new Request.Builder().url(url).get().build();
			    Response response = okHttpClient.newCall(request).execute();
			    String result = response.body().string();
			    return result;
		    }


	    } else {
		    // 创建OkHttpClient对象
		    OkHttpClient okHttpClient = new OkHttpClient();
		    // 创建一个请求
		    if (method.equals("post")) {
			    FormBody.Builder builder = new FormBody.Builder();
			    FormBody build = builder.build();
			    Request request = new Request.Builder().url(url).post(build).build();
			    Response response = okHttpClient.newCall(request).execute();
			    String result = response.body().string();
			    return result;
		    } else {
			    Request request = new Request.Builder().url(url).get().build();
			    Response response = okHttpClient.newCall(request).execute();
			    String result = response.body().string();
			    return result;
		    }

	    }

    }


    /**
     * 获取签名后的字符串(加密按照Key的首字母排序完的拼接字符串进行MD5加密)
     *
     * @param stringStringHashMap （请求参数的hashMap）
     * @ return
     * @ throws NoSuchAlgorithmException
     */
    public static String GetMd5s(Map<String, String> stringStringHashMap) throws NoSuchAlgorithmException {


	    if (stringStringHashMap == null) {
		    return "";
	    }
	    //获取map集合的所有"映射"的Set集合,这里规范每个映射的类型为Map.Entry<K, V>（于Set集合中无序存放）
	    Set<Map.Entry<String, String>> entrySet = stringStringHashMap.entrySet();
	    //新建List集合获取Set集合的所有元素（"映射"对象）（顺序与Set集合一样）
	    List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(entrySet);

	    Collections.sort(list, new AscKeyComparator());

	    Iterator<Map.Entry<String, String>> iter = list.iterator();    //获取List集合的迭代器,Map.Entry<K, V>为迭代元素的类型
	    //        String str = getHashMapStrting(list);

	    String str = MD5Utils.getHashMapStrting(list);


	    // 生成一个MD5加密计算摘要
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    // 计算md5函数
	    md.update(str.getBytes());
	    // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	    // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	    String md5 = new BigInteger(1, md.digest()).toString(16);
	    //BigInteger会把0省略掉，需补全至32位
	    return fillMD5(md5);

    }

    /**
     * MD5加密
     *
     * @param md5 (MD5加密后的字符串)
     * @ return
     */
    public static String fillMD5(String md5) {
	    return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }


}
