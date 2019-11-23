package com.akitektuo.buscard;

import android.app.Activity;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.TextView;

class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {

    private Activity context;

    public FingerPrintHandler(Activity context) {
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        context.startActivity(new Intent(context, MainActivity.class));
        context.finish();
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        ((TextView) context.findViewById(R.id.textFingerError)).setText("Try again or use PIN");
    }
}
