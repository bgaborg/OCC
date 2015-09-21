package com.bg.thsb.openstack.model.impl;

import com.bg.thsb.openstack.model.ifaces.Image;

import java.util.Date;
import java.util.Map;

public class ImageImpl implements Image {
	private String id;
	private String name;
	private long size;
	private int minDisk;
	private int minRam;
	private Status status;
	private Date created;
	private Map<String, Object> metaData;
	private boolean isSnapsot;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public long getSize() {
		return size;
	}

	@Override
	public int getMinDisk() {
		return minDisk;
	}

	@Override
	public int getMinRam() {
		return minRam;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public Map<String, Object> getMetaData() {
		return metaData;
	}

	@Override
	public boolean isSnapshot() {
		return isSnapsot;
	}
}