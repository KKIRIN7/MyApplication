package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;

public class AWSconnectSelect extends AsyncTask<String, Void, String> {
    private TextView textView;
    private int Select;
    public AWSconnectSelect(TextView textView, int select) {//textviewを設定する。複数をしたいなら複数分引数などの設定をして
        super();
        this.textView = textView;//textviewにidをいれる
        this.Select = select;
    }
    @Override
    protected void onPreExecute() {
        //バックグラウンド処理開始前にUIスレッドで実行される。
        //ダイアログの生成などを行う。
    }

    @Override
    protected String doInBackground(String... params) {
        //バックグラウンドで処理させる内容をここで記述。
        //AsyncTaskを使うにあたって、このメソッドの中身は必ず記述しなければいけない。
        //String url = "http://13.113.228.107/ShowTrainMET.php";
        HashMap<String, String> hikisu = new HashMap<String, String>();
        //String hikisu2 = "a=三角筋";
        String a = execute1(params[0], params[1]);//params[0]にURL、params[1]以降にphpに渡す値が入っている
        return a;
    }
    @Override
    protected void onPostExecute(String result) {//すぐ上のaがresultに入る
        //doInBackgroundが終了するとUIスレッドで実行される。
        //ダイアログの消去などを行う。
        //doInBackgroundの結果を画面表示に反映させる処理もここに記述。
        String[] test = result.split("," , 0);//phpから受け取ったものを要素ごとに分ける
        textView.setText(test[Select]);//表示
        //textView.setText("result");
    }
    public static String execute1(String argStrApiUrl, //HashMap<String,String> formItems) {
                                  String formParam){
        String ret = "";
        HttpURLConnection urlConnection = null;
        try {
            //ステップ1.接続URLを決める。
            URL url = new URL(argStrApiUrl);
            //ステップ2.URLへのコネクションを取得する。
            urlConnection = (HttpURLConnection) url.openConnection();

            //ステップ3.接続設定(メソッドの決定,タイムアウト値,ヘッダー値等)を行う。
            //接続タイムアウトを設定する。
            urlConnection.setConnectTimeout(100000);
            //レスポンスデータ読み取りタイムアウトを設定する。
            urlConnection.setReadTimeout(100000);
            //ヘッダーにUser-Agentを設定する。
            urlConnection.setRequestProperty("User-Agent", "Android");
            //ヘッダーにAccept-Languageを設定する。
            urlConnection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            //ヘッダーにContent-Typeを設定する
            urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //HTTPのメソッドをPOSTに設定する。
            urlConnection.setRequestMethod("POST");
            //リクエストのボディ送信を許可する
            urlConnection.setDoOutput(true);
            //レスポンスのボディ受信を許可する
            urlConnection.setDoInput(true);

            //ステップ4.コネクションを開く
            urlConnection.connect();

            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(formParam);//phpに値を送る
            out.close();
            //ステップ5:リクエストボディの書き出しを行う。
        /*OutputStream outputStream = urlConnection.getOutputStream();
        if (formItems.size() > 0) {
            Uri.Builder builder = new Uri.Builder();
            for (String key : formItems.keySet()) {
                //[key=value&key=value…]形式の文字列に変換する。
                builder.appendQueryParameter(key , formItems.get(key));
            }
            String join = builder.build().getEncodedQuery();
            PrintStream ps = new PrintStream(outputStream);
            ps.print(join);
            ps.close();
        }
        outputStream.close();*/
            //ステップ6.レスポンスボディの読み出しを行う。
            int responseCode = urlConnection.getResponseCode();
            InputStream stream = urlConnection.getInputStream();
            ret = convertToString(stream);//phpから値を受け取る
            Log.d("execute", "URL:" + argStrApiUrl);
            Log.d("execute", "HttpStatusCode:" + responseCode);
            Log.d("execute", "ResponseData:" + ret);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                //7.コネクションを閉じる。
                urlConnection.disconnect();
            }
        }
        return ret;
    }
    public static String convertToString(InputStream stream) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(",");
        }
        try {
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

