package org.leanpoker.leanpokerandroid.view.fragment;

import org.leanpoker.data.model.Event.EventStatus;
import org.leanpoker.leanpokerandroid.model.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 15/09/15.
 */
public class EventsLiveFragment extends EventsFragment {

	@Override
	public void renderEventList(final List<EventModel> eventModelList) {
		mEventsAdapter.setEventModels(filter(eventModelList));
	}

	private List<EventModel> filter(final List<EventModel> eventModelList) {
		final List<EventModel> filteredEventModelList = new ArrayList<>();
		for (final EventModel eventModel : eventModelList) {
			if (eventModel.getEventStatus().equals(EventStatus.RUNNING)) {
				filteredEventModelList.add(eventModel);
			}
		}
		return filteredEventModelList;
	}
}
