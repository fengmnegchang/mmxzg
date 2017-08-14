//package com.open.mmxzg.activity.wxapi;
//
//import com.open.mmxzg.R;
//import com.tencent.mm.opensdk.constants.ConstantsAPI;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
//import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
// 
//
//public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
//	
//	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
//	
//	private Button gotoBtn, regBtn, launchBtn, checkBtn;
//	
//	// IWXAPI 锟角碉拷锟斤拷锟斤拷app锟斤拷微锟斤拷通锟脚碉拷openapi锟接匡拷
//    private IWXAPI api;
//	
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wx_entry);
//        
//        // 通锟斤拷WXAPIFactory锟斤拷锟斤拷锟斤拷锟斤拷取IWXAPI锟斤拷实锟斤拷
//    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
//
//    	regBtn = (Button) findViewById(R.id.reg_btn);
//    	regBtn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// 锟斤拷锟斤拷app注锟结到微锟斤拷
//			    api.registerApp(Constants.APP_ID);    	
//			}
//		});
//    	
//        gotoBtn = (Button) findViewById(R.id.goto_send_btn);
//        gotoBtn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//		        startActivity(new Intent(WXEntryActivity.this, SendToWXActivity.class));
//		        finish();
//			}
//		});
//        
//        launchBtn = (Button) findViewById(R.id.launch_wx_btn);
//        launchBtn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(WXEntryActivity.this, "launch result = " + api.openWXApp(), Toast.LENGTH_LONG).show();
//			}
//		});
//        
//        checkBtn = (Button) findViewById(R.id.check_timeline_supported_btn);
//        checkBtn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				int wxSdkVersion = api.getWXAppSupportAPI();
//				if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
//					Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline supported", Toast.LENGTH_LONG).show();
//				} else {
//					Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline not supported", Toast.LENGTH_LONG).show();
//				}
//			}
//		});
//        
////        scanBtn = (Button) findViewById(R.id.scan_qrcode_login_btn);
////        scanBtn.setOnClickListener(new View.OnClickListener() {
////
////			@Override
////			public void onClick(View v) {
////		        startActivity(new Intent(WXEntryActivity.this, ScanQRCodeLoginActivity.class));
////		        finish();
////			}
////        });
//        
//		//注锟解：
//		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞癸拷锟酵革拷锟斤拷锟斤拷锟斤拷锟绞碉拷锟絎XEntryActivity锟斤拷锟斤拷要锟叫讹拷handleIntent锟侥凤拷锟斤拷值锟斤拷锟斤拷锟斤拷锟斤拷锟街滴猣alse锟斤拷锟斤拷说锟斤拷锟斤拷尾锟斤拷戏锟轿达拷锟絊DK锟斤拷锟斤拷应finish锟斤拷前透锟斤拷锟斤拷锟芥，锟斤拷锟斤拷锟解部通锟斤拷锟斤拷锟捷非凤拷锟斤拷锟斤拷锟斤拷Intent锟斤拷锟斤拷停锟斤拷锟斤拷透锟斤拷锟斤拷锟芥，锟斤拷锟斤拷锟矫伙拷锟斤拷锟缴伙拷
//        try {
//        	api.handleIntent(getIntent(), this);
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//    }
//
//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//		
//		setIntent(intent);
//        api.handleIntent(intent, this);
//	}
//
//	// 微锟脚凤拷锟斤拷锟斤拷锟襟到碉拷锟斤拷锟斤拷应锟斤拷时锟斤拷锟斤拷氐锟斤拷锟斤拷梅锟斤拷锟�
//	@Override
//	public void onReq(BaseReq req) {
//		switch (req.getType()) {
//		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
//			goToGetMsg();		
//			break;
//		case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
//			goToShowMsg((ShowMessageFromWX.Req) req);
//			break;
//		default:
//			break;
//		}
//	}
//
//	// 锟斤拷锟斤拷锟斤拷应锟矫凤拷锟酵碉拷微锟脚碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷应锟斤拷锟斤拷锟斤拷锟截碉拷锟斤拷锟矫凤拷锟斤拷
//	@Override
//	public void onResp(BaseResp resp) {
//		int result = 0;
//		
//		Toast.makeText(this, "baseresp.getType = " + resp.getType(), Toast.LENGTH_SHORT).show();
//		
//		switch (resp.errCode) {
//		case BaseResp.ErrCode.ERR_OK:
//			result = R.string.errcode_success;
//			break;
//		case BaseResp.ErrCode.ERR_USER_CANCEL:
//			result = R.string.errcode_cancel;
//			break;
//		case BaseResp.ErrCode.ERR_AUTH_DENIED:
//			result = R.string.errcode_deny;
//			break;
//		case BaseResp.ErrCode.ERR_UNSUPPORT:
//			result = R.string.errcode_unsupported;
//			break;
//		default:
//			result = R.string.errcode_unknown;
//			break;
//		}
//		
//		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//	}
//	
//	private void goToGetMsg() {
////		Intent intent = new Intent(this, GetFromWXActivity.class);
////		intent.putExtras(getIntent());
////		startActivity(intent);
////		finish();
//	}
//	
//	private void goToShowMsg(ShowMessageFromWX.Req showReq) {
//		WXMediaMessage wxMsg = showReq.message;		
//		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
//		
//		StringBuffer msg = new StringBuffer(); // 锟斤拷织一锟斤拷锟斤拷锟斤拷示锟斤拷锟斤拷息锟斤拷锟斤拷
//		msg.append("description: ");
//		msg.append(wxMsg.description);
//		msg.append("\n");
//		msg.append("extInfo: ");
//		msg.append(obj.extInfo);
//		msg.append("\n");
//		msg.append("filePath: ");
//		msg.append(obj.filePath);
//		
////		Intent intent = new Intent(this, ShowFromWXActivity.class);
////		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
////		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
////		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
////		startActivity(intent);
////		finish();
//	}
//}