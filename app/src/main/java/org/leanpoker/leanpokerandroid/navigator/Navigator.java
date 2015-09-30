package org.leanpoker.leanpokerandroid.navigator;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import org.leanpoker.leanpokerandroid.model.PhotoModel;
import org.leanpoker.leanpokerandroid.view.activity.EventActivity;
import org.leanpoker.leanpokerandroid.view.activity.EventListActivity;
import org.leanpoker.leanpokerandroid.view.activity.FullScreenPhotoActivity;
import org.leanpoker.leanpokerandroid.view.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmolnar on 07/09/15.
 */
public class Navigator {

    private static Navigator mInstance = new Navigator();

    private Navigator() {}

    public static Navigator getInstance() {
        return mInstance;
    }

    public void naviateToEventListActivity(Activity from) {
        if (from != null) {
            from.startActivity(EventListActivity.createIntent(from));
            from.finish();
        }
    }

	public void navigateToEventActivity(final Context context, final String eventId) {
		if (context == null || eventId == null) {
			return;
		}
		context.startActivity(EventActivity.createIntent(context, eventId));
	}

	public void navigateToLoginActivity(final Context context) {
		if (context == null) {
			return;
		}
		context.startActivity(LoginActivity.createIntent(context));
	}

	public void navigateToFullScreenPhotoActivity(final Context context,
												  final ArrayList<PhotoModel> photoModels,
												  final int clickedPhotoIndex) {
		if (context == null
				||  photoModels == null
				|| photoModels.isEmpty()
				|| clickedPhotoIndex < 0) {
			return;
		}
		context.startActivity(FullScreenPhotoActivity.createIntent(
						context,
						photoModels,
						clickedPhotoIndex
				)
		);
	}
}
