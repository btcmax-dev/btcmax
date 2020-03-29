import java.util.List;
import java.util.Map;

/**
 * Created by administrator on 2018/7/8.
 */
public class MD5Utils {

    public MD5Utils(String s) {


    }

    public static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }

    public static String getHashMapStrting(List<Map.Entry<String, String>> list) {
        String values = "";
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i).getKey();
            String value = list.get(i).getValue();
            if (i == list.size() - 1) {
                values += key + "=" + value;
            } else {
                values += key + "=" + value + "&";
            }
        }

        return values;

    }

    //    /**
//     * 创建HttpURLConnection连接
//     *
//     * @param url
//     * @throws IOException
//     */
//    public static HttpURLConnection SetHttpUrlConnection(String url) throws IOException {
//
//
//        URL serverUrl = new URL(url);
//        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//        conn.setConnectTimeout(100000); //连接超时 单位毫秒
//        conn.setDoOutput(true);
//        conn.setUseCaches(false);
//        conn.setReadTimeout(100000);//读取超时 单位毫秒
//        conn.setInstanceFollowRedirects(false);
//        return conn;
//    }

//
//    /**
//     * 请求url获取返回的内容
//     *
//     * @param connection
//     * @return
//     * @throws IOException
//     */
//    public static String getReturn(HttpURLConnection connection) throws IOException {
//        StringBuffer buffer = new StringBuffer();
//        InputStream inputStream = connection.getInputStream();
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String str = null;
//        while ((str = bufferedReader.readLine()) != null) {
//            buffer.append(str);
//        }
//        bufferedReader.close();
//        inputStreamReader.close();
//        inputStream.close();
//        connection.disconnect();
//        return buffer.toString();
//    }


//    /**
//     * 获取Response
//     *
//     * @param conn
//     * @return
//     * @throws IOException
//     */
//    public static String getString(HttpURLConnection conn) throws IOException {
//        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//            String result = getReturn(conn);
//            Gson gson = new Gson();
//            String s = gson.toJson(result);
//            return s;
//        } else {
//            String result = getReturn(conn);
//            Gson gson = new Gson();
//            String s = gson.toJson(result);
//            return s;
//        }
//    }
}
