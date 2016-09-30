package com.nyx_itech.k13.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.nyx_itech.k13.R;
import com.nyx_itech.k13.adapters.Contacts;
import com.nyx_itech.k13.adapters.GroupContactsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewBroadCastFragment extends Fragment {
	/* Home contacts array
	 * Class: Contacts
	 * Package: com.atouchsimo.whatsup.adapters
	 * */
	public List<Contacts> contacts = new ArrayList<Contacts>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_new_broadcast, container,false);
		ListView lv = (ListView) v.findViewById(R.id.contactList);
		GroupContactsListAdapter adapter = new GroupContactsListAdapter(getActivity(), contacts);
		lv.setAdapter(adapter);
		/* we load chats data from Resources
         * res/values/strings.xml
         * */
		String[] names = {"rifal","dion"};
		String[] images = {"user_image_6","user_image_6"};

		for (int i = 0; i < names.length; i++) {
			Contacts contact = new Contacts();
			/* we use here Contacts Class to set data
			 * then we add it to contacts array
			 * */
			contact.setImage(images[i]);
			contact.setName(names[i]);
			contacts.add(contact);
		}
		adapter.notifyDataSetChanged();
		return v;
	}
}
