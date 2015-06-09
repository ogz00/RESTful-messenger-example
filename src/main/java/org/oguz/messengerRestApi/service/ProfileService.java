package org.oguz.messengerRestApi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.oguz.messengerRestApi.database.DatabaseClass;
import org.oguz.messengerRestApi.model.Message;
import org.oguz.messengerRestApi.model.Profile;

public class ProfileService
{


	public ProfileService()
	{
		profiles.put("oguzhan", new Profile(1, "oguzhan", "OGUZHAN", "KARACULLU"));
		profiles.put("naz", new Profile(2, "naz", "NAZ", "BEHNAM"));

	}

	private Map<String, Profile> profiles = new DatabaseClass().getProfiles();

	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName)
	{
		return profiles.get(profileName);
	}


	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size() + 1);
		profile.setCreated(new Date());
		profiles.put(profile.getProfileName(), profile);
		return profile;

	}

	public Profile updateProfile(Profile profile)
	{
		if (profile.getProfileName().isEmpty())
			return null;
		profile.setCreated(new Date());
		profiles.put(profile.getProfileName(), profile);
		return profile;

	}

	public Profile removeProfile(String profileName)
	{
		return profiles.remove(profileName);

	}

}
