package com.example.myapplication;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;

public class AWSconnectHikitugi extends AsyncTask<String, Void, String> {
    private TextView textView1;
    private String user_name;
    private String mail_address;
    private String password;

    public AWSconnectHikitugi(TextView textView1, String user_name, String mail_address, String password) {//textviewを設定する。複数をしたいなら複数分引数などの設定をして
        super();
        this.textView1 = textView1;//textviewにidをいれる
        this.user_name = user_name;
        this.mail_address = mail_address;
        this.password = password;
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
        //HashMap<String, String> hikisu = new HashMap<String, String>();
        //String hikisu2 = "a=三角筋";
        String a = execute1(params[0], params[1]);//params[0]にURL、params[1]以降にphpに渡す値が入っている
        return a;
    }
    @Override
    protected void onPostExecute(String result) {//すぐ上のaがresultに入る
        //doInBackgroundが終了するとUIスレッドで実行される。
        //ダイアログの消去などを行う。
        //doInBackgroundの結果を画面表示に反映させる処理もここに記述。
        String[] test = result.split("\n" , 0);//phpから受け取ったものを要素ごとに分ける
        int testlength = test.length;
        int i = 2;
        String text = "";
        if(i < testlength) {
            if (!test[1].equals(user_name)) {
                text += "・ユーザー名が違います\n";
            }
            if (!test[2].equals(password)) {
                text += "・パスワードが違います\n";
            }
            if (test[0].equals(mail_address) & test[1].equals(user_name) & test[2].equals(password)) {
                text += "引き継ぎ完了";
            }
        } else if(testlength == 1) {
                text += "・メールアドレスが違います\n";
        }
        textView1.setText(text);
        //textView.setText("result");
    }
    public static String execute1(String argStrApiUrl, //HashMap<String,String> formItems) {
                                  String param1) {
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
            out.write(param1);//phpに値を送る
           out.close();
            //ステップ5:リクエストボディの書き出しを行う。
//        OutputStream outputStream = urlConnection.getOutputStream();
////        if (formItems.size() > 0) {
////            Uri.Builder builder = new Uri.Builder();
////            //[key=value&key=value…]形式の文字列に変換する。
////            for (String key : formItems.keySet())
////                builder.appendQueryParameter(key, formItems.get(key));
////            String join = builder.build().getEncodedQuery();
//            PrintStream ps = new PrintStream(outputStream);
//            ps.print(join);
//            ps.close();
//        //}
//        outputStream.close();
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
            sb.append("\n");
        }
        try {
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

