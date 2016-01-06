package com.example.assistant_3;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	ImageView goRoomSetView, goSeatSetView, goReSetView;
	View roomSetView, seatSetView, reSetView;
	private static final String TAG = "stickynotes";
	private boolean mResumed = false;
	private boolean mWriteMode = false;
	NfcAdapter mNfcAdapter;
	EditText mNote;
	EditText mHeight;
	EditText mWidth;
	EditText mLecroom_id;
	
	/////////////////////
	EditText mNote2;
	EditText mHeight2;
	EditText mWidth2;
	EditText mLecroom_id2;
	Intent mIntent;
	PendingIntent mNfcPendingIntent;
	IntentFilter[] mWriteTagFilters;
	IntentFilter[] mNdefExchangeFilters;
	String width = "";
	String height = "";
	String lecroom_id = "";
	String tag_id = "";
	EditText mTag_id;
	int index=0;
	boolean first = true;
	boolean second = false;
	boolean isroomset = false;
	ArrayList<String> dataList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 상단 버튼 등록

		goRoomSetView = (ImageView) findViewById(R.id.goRoomView_btn);
		goSeatSetView = (ImageView) findViewById(R.id.goSeatView_btn);
		goReSetView = (ImageView) findViewById(R.id.goResetView_btn);
		goRoomSetView.setOnClickListener(this);
		goSeatSetView.setOnClickListener(this);
		goReSetView.setOnClickListener(this);
		// 각 그룹 뷰 등록
		roomSetView = (View) findViewById(R.id.roomSetXml);
		seatSetView = (View) findViewById(R.id.seatSetXml);
		reSetView = (View) findViewById(R.id.reSetXml);

		initRoomView();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		mNfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());
		findViewById(R.id.inputBtn).setOnClickListener(mTagWriter);
		findViewById(R.id.inputBtn2).setOnClickListener(mTagWriter);
		mNote = (EditText) findViewById(R.id.tag_id);
		//mWidth = (EditText) findViewById(R.id.widthEdit);
		//mHeight = (EditText) findViewById(R.id.heightEdit);
		mLecroom_id = (EditText) findViewById(R.id.lecture_id_ed);
		mTag_id = (EditText) findViewById(R.id.tag_id);
		////////////
		mNote2 = (EditText) findViewById(R.id.tag_id2);
		mWidth2 = (EditText) findViewById(R.id.widthEdit2);
		mHeight2 = (EditText) findViewById(R.id.heightEdit2);
		mLecroom_id2 = (EditText) findViewById(R.id.lecture_id_ed2);
		mNote.addTextChangedListener(mTextWatcher);
		mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		IntentFilter ndefDetected = new IntentFilter(
				NfcAdapter.ACTION_NDEF_DISCOVERED);

		try {
			ndefDetected.addDataType("text/plain");
		} catch (MalformedMimeTypeException e) {
			mNdefExchangeFilters = new IntentFilter[] { ndefDetected };
		}

		IntentFilter tagDetected = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED);
		mWriteTagFilters = new IntentFilter[] { tagDetected };
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mResumed = true;
		/*
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.contentEquals(getIntent()
				.getAction())) {
			NdefMessage[] messages = getNdefMessages(getIntent());
			byte[] payload = messages[0].getRecords()[0].getPayload();
			// setNotBody(new String(payload));
			setIntent(new Intent());
		}
		*/
		enableNdefExchageMode();
	}

	private NdefMessage[] getNdefMessages(Intent intent) {
		NdefMessage[] msgs = null;
		String action = intent.getAction();

		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
				|| NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			Parcelable[] rawMsgs = intent
					.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

			if (rawMsgs != null) {
				msgs = new NdefMessage[rawMsgs.length];
				for (int i = 0; i < rawMsgs.length; i++) {
					msgs[i] = (NdefMessage) rawMsgs[i];
				}
			} else {
				byte[] empty = new byte[] {};
				NdefRecord record = new NdefRecord(NdefRecord.TNF_UNCHANGED,
						empty, empty, empty);
				NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
				msgs = new NdefMessage[] { msg };
			}
		} else {
			Log.d(TAG, "알려지지 않은 인텐트.");
			finish();
		}
		return msgs;
	}

	private void setWidBody(String string) {
		Editable text = mWidth.getText();
		text.clear();
		text.append(string);
	}

	private void setHeiBody(String string) {
		Editable text = mHeight.getText();
		text.clear();
		text.append(string);
	}
	private void setTagIdBody(String string) {
		Editable text = mTag_id.getText();
		text.clear();
		text.append(string);
	}

	private void enableNdefExchageMode() {
		mNfcAdapter
				.enableForegroundNdefPush(MainActivity.this, getNoteAsNdef());
		mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent,
				mNdefExchangeFilters, null);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mResumed = false;
		mNfcAdapter.disableForegroundNdefPush(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		if (!mWriteMode
				&& NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
			NdefMessage[] msgs = getNdefMessages(intent);
			PromptForContent(msgs[0]);
		}

		if (mWriteMode
				&& NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
			Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			writeTag(getNoteAsNdef(), detectedTag);
		}
	}

	boolean writeTag(NdefMessage message, Tag detectedTag) {
		int size = message.toByteArray().length;
		try {
			Ndef ndef = Ndef.get(detectedTag);
			if (ndef != null) {
				ndef.connect();
				if (!ndef.isWritable()) {
					toast("읽기 전용 태그.");
					return false;
				}
				if (ndef.getMaxSize() < size) {
					toast("태그 용량은 " + ndef.getMaxSize() + " 바이트이고, 메시지 크기는 "
							+ size + " 바이트이다.");
					return false;
				}
				ndef.writeNdefMessage(message);

				toast("태그에 기록했음.");
				if(isroomset){
					index++;
				}
				return true;
			} else {
				NdefFormatable format = NdefFormatable.get(detectedTag);
				if (format != null) {
					try {
						format.connect();
						format.format(message);
						toast("태그를 포맷을 했고 메시지를 썼음.");
						if(isroomset){
							index++;
						}
						return true;
					} catch (IOException e) {
						toast("태그 포맷을 실패했음.");
						return false;
					}
				} else {
					toast("태그가 NDEF를 지원하지 않음.");
					return false;
				}
			}
		} catch (Exception e) {
			toast("태그에 쓰기 실패.");
		}
		return false;
	}

	private void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	private void PromptForContent(final NdefMessage msg) {
		/*
		new AlertDialog.Builder(this)
				.setTitle("내용을 변경하겠습니까?")
				.setPositiveButton("예", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String body = new String(msg.getRecords()[0]
								.getPayload());
						// setNotBody(body);
					}
				})
				.setNegativeButton("아니오",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						}).show();
						*/
	}

	private View.OnClickListener mTagWriter = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			disableNdefExchangeMode();
			enableTagWriteMode();
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("태그에 터치하세요")
					.setOnCancelListener(
							new DialogInterface.OnCancelListener() {
								@Override
								public void onCancel(DialogInterface dialog) {
									disableTagWriteMode();
									enableNdefExchangeMode();
								}
							}).create().show();
		}
	};

	private TextWatcher mTextWatcher = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable arg0) {
			if (mResumed) {
				mNfcAdapter.enableForegroundNdefPush(MainActivity.this,
						getNoteAsNdef());
			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}
	};

	protected NdefMessage getNoteAsNdef() {
		String data = "";
		if(roomSetView.getVisibility() == 0){
			//강의실 단위일때
			if(first){
				second = true;
			}else if(second){
				lecroom_id = mLecroom_id.getText().toString();
				//toast(lecroom_id);
				Communication comm = new Communication();
				dataList = comm.getTagId(lecroom_id);
				isroomset = true;
				second = false;
			}
			if(!first){
				data = dataList.get(index);
				setTagIdBody(dataList.get(index));
				//toast(dataList.get(index));
			}
			//setWidBody(dataList.get(index));
		}else if(seatSetView.getVisibility() == 0){
			// 좌석 단위일때
			lecroom_id = mLecroom_id2.getText().toString();
			width = mWidth2.getText().toString();
			height = mHeight2.getText().toString();
			Communication comm = new Communication();
			
			data = comm.getTagId(lecroom_id, width, height);
			setTagIdBody(data);
		}else{
			toast("아직 이 기능은 구현되지 않았습니다.\n어플을 종료했다가 다시 실행해주세요.");
		}
		
		byte[] textBytes;
		// byte[] textBytes = mNote.getText().toString().getBytes();
		if(first){
			textBytes = "Hello".getBytes();
		}else{
			textBytes = data.getBytes();
		}
		NdefRecord textRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
				"text/plain".getBytes(), new byte[] {}, textBytes);
		first = false;
		return new NdefMessage(new NdefRecord[] { textRecord });
		
	}

	protected void enableTagWriteMode() {
		mWriteMode = true;
		IntentFilter tagDetected = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED);
		mWriteTagFilters = new IntentFilter[] { tagDetected };
		mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent,
				mWriteTagFilters, null);
	}

	private void disableNdefExchangeMode() {
		mNfcAdapter.disableForegroundNdefPush(this);
		mNfcAdapter.disableForegroundDispatch(this);
	}

	private void disableTagWriteMode() {
		mWriteMode = false;
		mNfcAdapter.disableForegroundDispatch(this);
	}

	private void enableNdefExchangeMode() {
		mNfcAdapter
				.enableForegroundNdefPush(MainActivity.this, getNoteAsNdef());
		mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent,
				mWriteTagFilters, null);
	}

	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.goRoomView_btn:
			initRoomView();
			break;
		case R.id.goSeatView_btn:
			initSeatView();
			break;
		case R.id.goResetView_btn:
			initResetView();
			break;
		}

	}

	private void initSeatView() {
		roomSetView.setVisibility(View.GONE);
		seatSetView.setVisibility(View.VISIBLE);
		reSetView.setVisibility(View.GONE);
		goRoomSetView.setImageResource(R.drawable.roomset_btn_open);
		goSeatSetView.setImageResource(R.drawable.seat_btn_over);
		goReSetView.setImageResource(R.drawable.reset_btn_open);
	}

	private void initRoomView() {
		roomSetView.setVisibility(View.VISIBLE);
		seatSetView.setVisibility(View.GONE);
		reSetView.setVisibility(View.GONE);
		goRoomSetView.setImageResource(R.drawable.roomset_btn_over);
		goSeatSetView.setImageResource(R.drawable.seat_btn_open);
		goReSetView.setImageResource(R.drawable.reset_btn);
	}

	private void initResetView() {
		roomSetView.setVisibility(View.GONE);
		seatSetView.setVisibility(View.GONE);
		reSetView.setVisibility(View.VISIBLE);
		goRoomSetView.setImageResource(R.drawable.roomset_btn);
		goSeatSetView.setImageResource(R.drawable.seat_btn);
		goReSetView.setImageResource(R.drawable.reset_btn_over);
	}

}
