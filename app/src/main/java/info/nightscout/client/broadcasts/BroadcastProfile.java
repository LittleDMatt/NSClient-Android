package info.nightscout.client.broadcasts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;


import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import info.nightscout.client.data.NSProfile;


/**
 * Created by mike on 20.02.2016.
 */
public class BroadcastProfile {
    private static Logger log = LoggerFactory.getLogger(BroadcastProfile.class);

    public void handleNewTreatment(NSProfile profile, Context context, boolean isDelta) {
        Bundle bundle = new Bundle();
        bundle.putString("profile", profile.getData().toString());
        bundle.putString("activeprofile", profile.getActiveProfile());
        bundle.putBoolean("delta", isDelta);
        Intent intent = new Intent(Intents.ACTION_NEW_PROFILE);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        context.sendBroadcast(intent);
        List<ResolveInfo> x = context.getPackageManager().queryBroadcastReceivers(intent, 0);

        log.debug("PROFILE " + x.size() + " receivers");
    }

}
