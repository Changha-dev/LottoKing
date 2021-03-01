//package com.mentenseoul.lottoking;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.EditorInfo;
//import android.view.inputmethod.InputMethodManager;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//public class Fragment4 extends Fragment {
//
//    EditText editText;
//    Button button;
//    WebView webView;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment4, container, false);
//
//        editText = rootView.findViewById(R.id.editText);
//        button = rootView.findViewById(R.id.button);
//        webView = rootView.findViewById(R.id.webView);
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                Toast.makeText(rootView.getContext(),"로딩 끝", Toast.LENGTH_LONG).show();
//            }
//        });
//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { // 클릭을 다른 곳에서 함?
//                if(actionId == EditorInfo.IME_ACTION_SEARCH){
//                    button.callOnClick();
//                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE); //키보드 가리기
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        new IntentIntegrator(getActivity()).initiateScan();
//
//        return rootView;
//    }
//
//    public void onClick(View view){
//        String address = editText.getText().toString();
//
//        if(!address.startsWith("http://")){
//            address = "http://" + address;
//        }
//        // webView 실행
//        webView.loadUrl(address);
//    }
//
//
//}
//

