package altimetrik.hceapp;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class CardReaderActivity extends AppCompatActivity implements CardReader.AccountCallback{

	// Recommend NfcAdapter flags for reading from other Android devices. Indicates that this
	// activity is interested in NFC-A devices (including other Android devices), and that the
	// system should not check for the presence of NDEF-formatted data (e.g. Android Beam).
	public static int READER_FLAGS =
			NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;

	private CardReader mCardReader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_reader);
		mCardReader = new CardReader(this);
	}


	@Override
	public void onPause() {
		super.onPause();
		disableReaderMode();
	}

	@Override
	public void onResume() {
		super.onResume();
		enableReaderMode();
	}

	private void enableReaderMode() {
		System.out.println("-----enableReaderMode");
		NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
		if (nfc != null) {
			nfc.enableReaderMode(this, mCardReader, READER_FLAGS, null);
		}
	}

	private void disableReaderMode() {
//		Log.i(TAG, "Disabling reader mode");
		System.out.println("-----disableReaderMode");
		NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
		if (nfc != null) {
			nfc.disableReaderMode(this);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_card_reader, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onAccountReceived(final String account) {

		System.out.println("---Data Recieved :"+account);
	}
}
